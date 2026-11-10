package rpg.app;

import java.util.List;
import rpg.domain.contract.HabilidadeEspecial;
import rpg.domain.event.Notificador;
import rpg.domain.model.Arqueiro;
import rpg.domain.model.Dragao;
import rpg.domain.model.Goblin;
import rpg.domain.model.Guerreiro;
import rpg.domain.model.Inimigo;
import rpg.domain.model.Mago;
import rpg.domain.model.Personagem;
import rpg.i18n.MensagensPortugues;
import rpg.presentation.SaidaConsole;

/**
 * Ponto de entrada e "Composition Root": aqui as peças são montadas e injetadas.
 * ATENÇÃO: esta é uma demonstração provisória do Dia 2; o combate em turnos vem no Dia 3.
 */
public class Main {

    public static void main(String[] args) {
        Notificador notificador = new Notificador(new SaidaConsole(), new MensagensPortugues());

        List<Personagem> herois = List.of(new Guerreiro("Aragorn"), new Mago("Luna"), new Arqueiro("Legolas"));
        Inimigo goblin = new Goblin("Goblin");
        Dragao dragao = new Dragao("Smaug");

        System.out.println("=== Heróis ===");
        herois.forEach(h -> h.mostrarStatus(notificador));

        System.out.println("\n=== Rodada contra o Goblin ===");
        agirContra(herois, goblin, notificador);

        System.out.println("\n=== Rodada contra o Dragão ===");
        agirContra(herois, dragao, notificador);
        dragao.usarHabilidade(herois.get(0), notificador);

        System.out.println("\n=== Status ===");
        herois.forEach(h -> h.mostrarStatus(notificador));
        dragao.mostrarStatus(notificador);
    }

    /** Polimorfismo: a mesma lista trata Guerreiro, Mago e Arqueiro de forma uniforme. */
    private static void agirContra(List<Personagem> herois, Inimigo alvo, Notificador notificador) {
        for (Personagem heroi : herois) {
            if (alvo.estaDestruido()) {
                break;
            }
            if (heroi instanceof HabilidadeEspecial especial && especial.podeUsarHabilidade()) {
                especial.usarHabilidade(alvo, notificador);
            } else {
                heroi.atacar(alvo, notificador);
            }
        }
    }
}
