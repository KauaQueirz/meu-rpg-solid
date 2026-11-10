package rpg.presentation;

/**
 * Destino das mensagens do jogo. Hoje o console; amanhã poderia ser uma janela
 * gráfica ou um arquivo de log, sem mexer nas regras do jogo (DIP + OCP).
 */
public interface Saida {

    void exibir(String mensagem);
}
