package rpg.domain.event;

import rpg.i18n.Mensagens;
import rpg.presentation.Saida;

/**
 * Liga o domínio à apresentação sem acoplá-los.
 *
 * SRP: o domínio apenas informa "o que aconteceu" (uma chave + argumentos);
 * o texto (idioma) vem de Mensagens e o destino (console, janela...) de Saida.
 * DIP: as duas dependências são interfaces, injetadas pelo construtor.
 */
public class Notificador {

    private static final int LARGURA_BARRA = 10;

    private final Saida saida;
    private final Mensagens mensagens;

    public Notificador(Saida saida, Mensagens mensagens) {
        this.saida = saida;
        this.mensagens = mensagens;
    }

    public void notificar(String chave, Object... args) {
        saida.exibir(mensagens.getMensagem(chave, args));
    }

    /** Devolve o texto traduzido sem exibi-lo (útil para montar menus e fichas). */
    public String traduzir(String chave, Object... args) {
        return mensagens.getMensagem(chave, args);
    }

    /** Barra de vida em texto, ex.: ######---- (usa só caracteres ASCII). */
    public String barra(int atual, int max) {
        int cheios = atual <= 0 ? 0 : Math.max(1, Math.round(atual * (float) LARGURA_BARRA / max));
        return "#".repeat(cheios) + "-".repeat(LARGURA_BARRA - cheios);
    }
}
