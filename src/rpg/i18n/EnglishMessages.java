package rpg.i18n;

public class EnglishMessages extends MapBasedMessageProvider {

    public EnglishMessages() {
        messages.put("attack", "{0} attacks {1}!");
        messages.put("damage_received", "{0} took {1} damage (health: {2}).");
        messages.put("defeated", "{0} was defeated!");
        messages.put("healed", "{0} recovered {1} health (health: {2}).");
        messages.put("status", "{0} - Health: {1}/{2}");
    }
}
