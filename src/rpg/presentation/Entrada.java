package rpg.presentation;

/**
 * Origem dos dados digitados pelo jogador. Par da interface Saida.
 * ISP: entrada e saída são contratos separados (quem só exibe não precisa saber ler).
 * DIP: o combate depende desta abstração, e não do Scanner.
 */
public interface Entrada {

    /** Lê um número digitado pelo jogador. Retorna -1 se o que foi digitado não for um número. */
    int lerNumero();
}
