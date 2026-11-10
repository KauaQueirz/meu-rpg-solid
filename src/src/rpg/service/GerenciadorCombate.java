package rpg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import rpg.domain.acao.Acao;
import rpg.domain.event.Notificador;
import rpg.domain.model.Inimigo;
import rpg.domain.model.Personagem;
import rpg.presentation.Entrada;

/**
 * Conduz o combate em turnos.
 *
 * SRP: cuida só da ordem dos turnos; texto vai para o Notificador e leitura para a Entrada.
 * OCP: o menu é montado a partir de personagem.getAcoes(); novas ações ou novos personagens
 *      não exigem alterar esta classe.
 * DIP: depende de abstrações (Entrada, Notificador, Personagem, Inimigo, Acao), nunca de
 *      Goblin, Mago, Scanner ou System.out.
 */
public class GerenciadorCombate {

    private final Entrada entrada;
    private final Notificador notificador;
    private final Random sorteio;

    public GerenciadorCombate(Entrada entrada, Notificador notificador, Random sorteio) {
        this.entrada = entrada;
        this.notificador = notificador;
        this.sorteio = sorteio;
    }

    /** Enfrenta os inimigos em sequência. Retorna true se os heróis venceram todos. */
    public boolean iniciarAventura(List<Personagem> herois, List<Inimigo> inimigos) {
        for (Inimigo inimigo : inimigos) {
            if (!batalhar(herois, inimigo)) {
                notificador.notificar("defeat");
                return false;
            }
            notificador.notificar("battle_won", inimigo.getNome());
        }
        notificador.notificar("victory_game");
        return true;
    }

    private boolean batalhar(List<Personagem> herois, Inimigo inimigo) {
        notificador.notificar("battle_start", inimigo.getNome());
        int rodada = 1;
        while (!inimigo.estaDestruido() && existeVivo(herois)) {
            notificador.notificar("round", rodada++);
            mostrarSituacao(herois, inimigo);
            for (Personagem heroi : herois) {
                if (!heroi.estaDestruido() && !inimigo.estaDestruido()) {
                    turnoDoHeroi(heroi, inimigo);
                }
            }
            if (!inimigo.estaDestruido() && existeVivo(herois)) {
                turnoDoInimigo(inimigo, herois);
            }
        }
        return inimigo.estaDestruido();
    }

    private void mostrarSituacao(List<Personagem> herois, Inimigo inimigo) {
        for (Personagem heroi : herois) {
            if (!heroi.estaDestruido()) {
                heroi.mostrarStatus(notificador);
            }
        }
        inimigo.mostrarStatus(notificador);
    }

    private void turnoDoHeroi(Personagem heroi, Inimigo inimigo) {
        notificador.notificar("turn_header", heroi.getNome());
        List<Acao> acoes = heroi.getAcoes();
        while (true) {
            for (int i = 0; i < acoes.size(); i++) {
                Acao acao = acoes.get(i);
                String chave = acao.estaDisponivel() ? "menu_option" : "menu_option_off";
                notificador.notificar(chave, i + 1, notificador.traduzir(acao.getChaveRotulo()));
            }
            notificador.notificar("menu_prompt");
            int opcao = entrada.lerNumero();
            if (opcao < 1 || opcao > acoes.size()) {
                notificador.notificar("invalid_option", acoes.size());
                continue;
            }
            Acao escolhida = acoes.get(opcao - 1);
            if (!escolhida.estaDisponivel()) {
                notificador.notificar("action_unavailable", notificador.traduzir(escolhida.getChaveRotulo()));
                continue;
            }
            escolhida.executar(inimigo, notificador);
            return;
        }
    }

    private void turnoDoInimigo(Inimigo inimigo, List<Personagem> herois) {
        List<Personagem> vivos = new ArrayList<>();
        for (Personagem heroi : herois) {
            if (!heroi.estaDestruido()) {
                vivos.add(heroi);
            }
        }
        inimigo.agir(vivos.get(sorteio.nextInt(vivos.size())), notificador);
    }

    private boolean existeVivo(List<Personagem> herois) {
        for (Personagem heroi : herois) {
            if (!heroi.estaDestruido()) {
                return true;
            }
        }
        return false;
    }
}
