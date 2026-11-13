package rpg.domain.acao;

import rpg.domain.contract.Destrutivel;
import rpg.domain.event.Notificador;

/**
 * Algo que um combatente pode escolher fazer no seu turno (atacar, usar habilidade, usar item...).
 *
 * OCP: uma ação nova é uma classe nova; o GerenciadorCombate monta o menu a partir da lista
 * de ações do personagem e não precisa ser alterado.
 */
public interface Acao {

    /** Texto do menu, já traduzido (a própria ação sabe como se apresentar). */
    String getRotulo(Notificador notificador);

    boolean estaDisponivel();

    /** O alvo é o inimigo; ações que afetam o próprio herói (itens) simplesmente o ignoram. */
    void executar(Destrutivel alvo, Notificador notificador);
}
