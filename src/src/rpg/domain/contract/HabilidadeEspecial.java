package rpg.domain.contract;

import rpg.domain.event.Notificador;

/**
 * Contrato segregado (ISP): só quem possui habilidade especial o implementa.
 * Um Inimigo simples (Goblin) não é obrigado a ter métodos que não usa, o que também protege o LSP.
 */
public interface HabilidadeEspecial {

    /** Chave de tradução do nome da habilidade (ex.: skill_fireball). */
    String getChaveHabilidade();

    /** Indica se há recurso suficiente (mana, vigor, flechas...) para usar a habilidade agora. */
    boolean podeUsarHabilidade();

    void usarHabilidade(Destrutivel alvo, Notificador notificador);
}
