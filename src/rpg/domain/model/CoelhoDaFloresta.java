package rpg.domain.model;

import java.util.List;
import rpg.domain.item.Armadura;
import rpg.domain.item.Item;

/** Inimigo intermediário (Forest Bunny). Sem habilidade especial. */
public class CoelhoDaFloresta extends Inimigo {

    public CoelhoDaFloresta(String nome) {
        super(nome, 130, 32, 3);
    }

    @Override
    public String getChaveIntroducao() {
        return "intro_forest_bunny";
    }

    @Override
    protected String getChaveAtaque() {
        return "attack_forest_bunny";
    }

    @Override
    public List<Item> getButim() {
        return List.of(new Armadura("item_comfy_sweater", 3));
    }
}
