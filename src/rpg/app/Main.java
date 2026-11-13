package rpg.app;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import rpg.domain.event.Notificador;
import rpg.domain.item.Arma;
import rpg.domain.item.PocaoCura;
import rpg.domain.model.Arqueiro;
import rpg.domain.model.Dragao;
import rpg.domain.model.Goblin;
import rpg.domain.model.Guerreiro;
import rpg.domain.model.Heroi;
import rpg.domain.model.Inimigo;
import rpg.domain.model.Mago;
import rpg.i18n.Mensagens;
import rpg.i18n.MensagensIngles;
import rpg.i18n.MensagensPortugues;
import rpg.presentation.Entrada;
import rpg.presentation.EntradaConsole;
import rpg.presentation.Saida;
import rpg.presentation.SaidaConsole;
import rpg.service.GerenciadorCombate;

/**
 * Ponto de entrada e "Composition Root": é aqui, e somente aqui, que as classes concretas
 * são escolhidas e injetadas nas demais.
 */
public class Main {

    public static void main(String[] args) {
        Saida saida = new SaidaConsole();
        Entrada entrada = new EntradaConsole(new Scanner(System.in, StandardCharsets.UTF_8));

        Notificador notificador = new Notificador(saida, escolherIdioma(saida, entrada));
        notificador.notificar("welcome");

        Heroi aragorn = new Guerreiro("Aragorn");
        aragorn.getInventario().adicionar(new Arma("item_iron_sword", 4));
        List<Heroi> herois = List.of(aragorn, new Mago("Luna"), new Arqueiro("Legolas"));
        for (Heroi heroi : herois) {
            heroi.getInventario().adicionar(new PocaoCura(25));
            heroi.getInventario().adicionar(new PocaoCura(25));
        }

        List<Inimigo> inimigos = List.of(new Goblin("Goblin"), new Dragao("Smaug"));

        new GerenciadorCombate(entrada, notificador, new Random()).iniciarAventura(herois, inimigos);
    }

    private static Mensagens escolherIdioma(Saida saida, Entrada entrada) {
        saida.exibir("Idioma / Language:  1) Português   2) English");
        int opcao = entrada.lerNumero();
        while (opcao < 1 || opcao > 2) {
            opcao = entrada.lerNumero();
        }
        return opcao == 1 ? new MensagensPortugues() : new MensagensIngles();
    }
}
