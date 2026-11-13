package rpg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import rpg.domain.acao.Acao;
import rpg.domain.event.Notificador;
import rpg.domain.item.Item;
import rpg.domain.model.Aliado;
import rpg.domain.model.Inimigo;
import rpg.presentation.Entrada;

/**
 * Conduz o combate em turnos.
 *
 * SRP: cuida só da ordem dos turnos; texto vai para o Notificador e leitura para a Entrada.
 * OCP: o menu é montado a partir de aliado.getAcoes(); novas ações, itens ou personagens
 *      não exigem alterar esta classe.
 * DIP: depende de abstrações (Entrada, Notificador, Aliado, Inimigo, Acao, Item), nunca de
 *      ToupeiraBroto, Omori, Scanner ou System.out.
 */
public class GerenciadorCombate {

    private final Entrada entrada;
    private final Notificador notificador;
    private final Random sorteio;
    private int proximoBeneficiado = 0;

    public GerenciadorCombate(Entrada entrada, Notificador notificador, Random sorteio) {
        this.entrada = entrada;
        this.notificador = notificador;
        this.sorteio = sorteio;
    }

    /** Enfrenta os inimigos em sequência. Retorna true se os aliados venceram todos. */
    public boolean iniciarAventura(List<Aliado> aliados, List<Inimigo> inimigos) {
        for (int i = 0; i < inimigos.size(); i++) {
            Inimigo inimigo = inimigos.get(i);
            if (!batalhar(aliados, inimigo)) {
                notificador.notificar("defeat");
                return false;
            }
            notificador.notificar("battle_won", inimigo.getNome());
            distribuirButim(aliados, inimigo);
            if (i < inimigos.size() - 1) {
                descansar(aliados);
            }
        }
        notificador.notificar("victory_game");
        return true;
    }

    private boolean batalhar(List<Aliado> aliados, Inimigo inimigo) {
        notificador.notificar("battle_start", inimigo.getNome());
        notificador.notificar(inimigo.getChaveIntroducao());
        int rodada = 1;
        while (!inimigo.estaDestruido() && existeVivo(aliados)) {
            notificador.notificar("round", rodada++);
            mostrarSituacao(aliados, inimigo);
            for (Aliado aliado : aliados) {
                if (!aliado.estaDestruido() && !inimigo.estaDestruido()) {
                    turnoDoAliado(aliado, inimigo);
                }
            }
            if (!inimigo.estaDestruido() && existeVivo(aliados)) {
                notificador.notificar("enemy_turn", inimigo.getNome());
                turnoDoInimigo(inimigo, aliados);
            }
        }
        return inimigo.estaDestruido();
    }

    private void mostrarSituacao(List<Aliado> aliados, Inimigo inimigo) {
        for (Aliado aliado : aliados) {
            if (!aliado.estaDestruido()) {
                aliado.mostrarResumo(notificador);
            }
        }
        inimigo.mostrarResumo(notificador);
    }

    private void turnoDoAliado(Aliado aliado, Inimigo inimigo) {
        notificador.notificar("turn_header", aliado.getNome());
        aliado.mostrarFicha(notificador);
        List<Acao> acoes = aliado.getAcoes();
        while (true) {
            for (int i = 0; i < acoes.size(); i++) {
                Acao acao = acoes.get(i);
                String chave = acao.estaDisponivel() ? "menu_option" : "menu_option_off";
                notificador.notificar(chave, i + 1, acao.getRotulo(notificador));
            }
            notificador.notificar("menu_prompt");
            int opcao = entrada.lerNumero();
            if (opcao < 1 || opcao > acoes.size()) {
                notificador.notificar("invalid_option", acoes.size());
                continue;
            }
            Acao escolhida = acoes.get(opcao - 1);
            if (!escolhida.estaDisponivel()) {
                notificador.notificar("action_unavailable", escolhida.getRotulo(notificador));
                continue;
            }
            escolhida.executar(inimigo, notificador);
            return;
        }
    }

    private void turnoDoInimigo(Inimigo inimigo, List<Aliado> aliados) {
        List<Aliado> vivos = new ArrayList<>();
        for (Aliado aliado : aliados) {
            if (!aliado.estaDestruido()) {
                vivos.add(aliado);
            }
        }
        inimigo.agir(vivos.get(sorteio.nextInt(vivos.size())), notificador);
    }

    /** Entre as batalhas, todos recuperam parte da vida (quem desmaiou se levanta) e o recurso. */
    private void descansar(List<Aliado> aliados) {
        notificador.notificar("rest");
        for (Aliado aliado : aliados) {
            aliado.descansar(notificador);
        }
    }

    /** O butim é repartido entre os aliados vivos, um de cada vez. O item sobrevive ao inimigo (agregação). */
    private void distribuirButim(List<Aliado> aliados, Inimigo inimigo) {
        for (Item item : inimigo.getButim()) {
            for (int tentativa = 0; tentativa < aliados.size(); tentativa++) {
                Aliado candidato = aliados.get(proximoBeneficiado % aliados.size());
                proximoBeneficiado++;
                if (!candidato.estaDestruido()) {
                    candidato.getInventario().adicionar(item);
                    notificador.notificar("loot_found", candidato.getNome(),
                            notificador.traduzir(item.getChaveNome()));
                    break;
                }
            }
        }
    }

    private boolean existeVivo(List<Aliado> aliados) {
        for (Aliado aliado : aliados) {
            if (!aliado.estaDestruido()) {
                return true;
            }
        }
        return false;
    }
}
