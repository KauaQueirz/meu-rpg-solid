package rpg.app;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import rpg.domain.event.Notificador;
import rpg.domain.item.Alimento;
import rpg.domain.item.Arma;
import rpg.domain.model.Aliado;
import rpg.domain.model.Aubrey;
import rpg.domain.model.CoelhoDaFloresta;
import rpg.domain.model.ExNamoradoEspacial;
import rpg.domain.model.Hero;
import rpg.domain.model.Inimigo;
import rpg.domain.model.Kel;
import rpg.domain.model.Omori;
import rpg.domain.model.Sweetheart;
import rpg.domain.model.ToupeiraBroto;
import rpg.i18n.Mensagens;
import rpg.i18n.MensagensIngles;
import rpg.i18n.MensagensPortugues;
import rpg.presentation.Entrada;
import rpg.presentation.EntradaConsole;
import rpg.presentation.Saida;
import rpg.presentation.SaidaConsole;
import rpg.service.GerenciadorCombate;


public class Main {

    public static void main(String[] args) {
        Saida saida = new SaidaConsole();
        Entrada entrada = new EntradaConsole(new Scanner(System.in, StandardCharsets.UTF_8));

        Notificador notificador = new Notificador(saida, escolherIdioma(saida, entrada));
        notificador.notificar("welcome");
        notificador.notificar("prologue");

        Aliado omori = new Omori("Omori");
        omori.getInventario().adicionar(new Arma("item_shiny_knife", 6));
        Aliado aubrey = new Aubrey("Aubrey");
        aubrey.getInventario().adicionar(new Arma("item_baseball_bat", 5));
        Aliado kel = new Kel("Kel");
        Hero hero = new Hero("Hero");

        List<Aliado> aliados = List.of(omori, aubrey, kel, hero);
        hero.setAmigos(aliados);
        for (Aliado aliado : aliados) {
            aliado.getInventario().adicionar(new Alimento("item_tofu", 35));
            aliado.getInventario().adicionar(new Alimento("item_tofu", 35));
        }
        hero.getInventario().adicionar(new Alimento("item_candy", 70));

        List<Inimigo> inimigos = List.of(
                new ToupeiraBroto(notificador.traduzir("enemy_sprout_mole")),
                new CoelhoDaFloresta(notificador.traduzir("enemy_forest_bunny")),
                new ExNamoradoEspacial(notificador.traduzir("enemy_space_ex")),
                new Sweetheart(notificador.traduzir("enemy_sweetheart")));

        new GerenciadorCombate(entrada, notificador, new Random()).iniciarAventura(aliados, inimigos);
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
