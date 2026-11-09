package rpg.domain.event;

import rpg.i18n.MessageProvider;
import rpg.presentation.OutputChannel;

/**
 * Liga o domínio à apresentação sem acoplá-los.
 *
 * SRP: o domínio apenas informa "o que aconteceu" (uma chave + argumentos);
 * o texto (idioma) vem do MessageProvider e o destino (console, janela...) do OutputChannel.
 * DIP: as duas dependências são interfaces, injetadas pelo construtor.
 */
public class CombatNotifier {

    private final OutputChannel output;
    private final MessageProvider messages;

    public CombatNotifier(OutputChannel output, MessageProvider messages) {
        this.output = output;
        this.messages = messages;
    }

    public void notify(String key, Object... args) {
        output.print(messages.getMessage(key, args));
    }
}
