package rpg.domain.item;

/** Item que fica equipado e concede bônus de combate (armas, armaduras...). */
public interface Equipavel extends Item {

    Slot getSlot();

    int getBonusAtaque();

    int getBonusDefesa();
}
