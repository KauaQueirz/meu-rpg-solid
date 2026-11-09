package rpg.presentation;

/** Única classe que conhece o System.out (SRP). */
public class ConsoleOutputChannel implements OutputChannel {

    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
