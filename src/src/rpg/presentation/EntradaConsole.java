package rpg.presentation;

import java.util.Scanner;

/** Única classe que conhece o Scanner e o System.in (SRP). */
public class EntradaConsole implements Entrada {

    private final Scanner scanner;

    public EntradaConsole(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int lerNumero() {
        if (!scanner.hasNextLine()) {
            throw new IllegalStateException("A entrada de dados foi encerrada.");
        }
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
