package rpg.i18n;

/**
 * Abstração de tradução: transforma uma chave ("attack") em texto no idioma escolhido.
 * OCP: um novo idioma é uma nova classe, sem alterar nenhuma existente.
 */
public interface MessageProvider {

    String getMessage(String key, Object... args);
}
