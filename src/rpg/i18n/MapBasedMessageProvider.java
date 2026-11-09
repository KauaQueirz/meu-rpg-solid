package rpg.i18n;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/** Base comum: guarda os textos em um mapa e formata os argumentos ({0}, {1}...). */
public abstract class MapBasedMessageProvider implements MessageProvider {

    protected final Map<String, String> messages = new HashMap<>();

    @Override
    public String getMessage(String key, Object... args) {
        String template = messages.getOrDefault(key, key);
        return MessageFormat.format(template, args);
    }
}
