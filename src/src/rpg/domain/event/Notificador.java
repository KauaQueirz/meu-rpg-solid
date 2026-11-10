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

    private final Saida saida;
    private final Mensagens mensagens;

    public Notificador(Saida saida, Mensagens mensagens) {
        this.saida = saida;
        this.mensagens = mensagens;
    }

    public void notificar(String chave, Object... args) {
        saida.exibir(mensagens.getMensagem(chave, args));
    }
}
