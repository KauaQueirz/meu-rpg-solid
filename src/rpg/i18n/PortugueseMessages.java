package rpg.i18n;

public class PortugueseMessages extends MapBasedMessageProvider {

    public PortugueseMessages() {
        // Atenção: evite apóstrofo (') nos textos, pois o MessageFormat o trata como símbolo especial.
        messages.put("attack", "{0} ataca {1}!");
        messages.put("damage_received", "{0} recebeu {1} de dano (vida: {2}).");
        messages.put("defeated", "{0} foi derrotado!");
        messages.put("healed", "{0} recuperou {1} de vida (vida: {2}).");
        messages.put("status", "{0} - Vida: {1}/{2}");
    }
}
