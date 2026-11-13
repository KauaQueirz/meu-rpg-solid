package rpg.domain.model;

import java.util.List;
import rpg.domain.item.Armadura;
import rpg.domain.item.Item;

/**
 * Inimigo simples. OCP: um novo tipo de inimigo é uma nova classe;
 * nada do que já existe precisou ser alterado.
 */
public class Goblin extends Inimigo {

    public Goblin(String nome) {
        super(nome, 25, 6, 1);
    }

    @Override
    public List<Item> getButim() {
        return List.of(new Armadura("item_leather_armor", 2));
    }
}
