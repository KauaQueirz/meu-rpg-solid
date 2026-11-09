package rpg.domain.contract;

import rpg.domain.event.CombatNotifier;

/**
 * Contrato segregado (ISP): só quem possui habilidade especial o implementa.
 * Um Inimigo simples não é obrigado a ter métodos que não usa (o que também protege o LSP).
 */
public interface ComHabilidadeEspecial {

    String getNomeHabilidade();

    void usarHabilidade(Destrutivel alvo, CombatNotifier notifier);
}
