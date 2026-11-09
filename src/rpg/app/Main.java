package rpg.app;

import rpg.domain.event.CombatNotifier;
import rpg.i18n.PortugueseMessages;
import rpg.presentation.ConsoleOutputChannel;

/** Ponto de entrada e "Composition Root": é aqui que as peças são montadas e injetadas. */
public class Main {

    public static void main(String[] args) {
        CombatNotifier notifier = new CombatNotifier(new ConsoleOutputChannel(), new PortugueseMessages());
        notifier.notify("status", "RPG SOLID (em construção)", 1, 1);
    }
}
