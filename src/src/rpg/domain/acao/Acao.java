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

    /** Chave de tradução do nome da ação (ex.: "action_attack"). */
    String getChaveRotulo();

    boolean estaDisponivel();

    void executar(Destrutivel alvo, Notificador notificador);
}
