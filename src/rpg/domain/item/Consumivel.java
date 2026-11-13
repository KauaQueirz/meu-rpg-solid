package rpg.domain.item;

import rpg.domain.event.Notificador;
import rpg.domain.model.Personagem;

/** Item que é gasto ao ser usado (poções, elixires...). */
public interface Consumivel extends Item {

    /** Evita desperdício: ex.: uma poção de cura não faz sentido para quem está com a vida cheia. */
    boolean podeSerUsadoEm(Personagem alvo);

    void usar(Personagem alvo, Notificador notificador);
}
