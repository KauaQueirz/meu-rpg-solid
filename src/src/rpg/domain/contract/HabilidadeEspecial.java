package rpg.domain.contract;

import rpg.domain.event.Notificador;

/**
 * Contrato segregado (ISP): só quem possui habilidade especial o implementa.
 * Um Inimigo simples (Goblin) não é obrigado a ter métodos que não usa, o que também protege o LSP.
 */
public interface HabilidadeEspecial {

    String getNomeHabilidade();

    /** Indica se há recurso suficiente (mana, vigor, flechas...) para usar a habilidade agora. */
    boolean podeUsarHabilidade();

    void usarHabilidade(Destrutivel alvo, Notificador notificador);
}
