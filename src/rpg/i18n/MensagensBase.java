package rpg.i18n;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/** Base comum: guarda os textos em um mapa e formata os argumentos ({0}, {1}...). */
public abstract class MensagensBase implements Mensagens {

    protected final Map<String, String> textos = new HashMap<>();

    @Override
    public String getMensagem(String chave, Object... args) {
        String modelo = textos.getOrDefault(chave, chave);
        return MessageFormat.format(modelo, args);
    }
}
