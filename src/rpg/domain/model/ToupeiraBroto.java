package rpg.domain.model;

import java.util.List;
import rpg.domain.item.Alimento;
import rpg.domain.item.Item;

/** Inimigo comum e fraco (Sprout Mole). Sem habilidade especial. */
public class ToupeiraBroto extends Inimigo {

    public ToupeiraBroto(String nome) {
        super(nome, 90, 24, 2);
    }

    @Override
    public String getChaveIntroducao() {
        return "intro_sprout_mole";
    }

    @Override
    protected String getChaveAtaque() {
        return "attack_sprout_mole";
    }

    @Override
    public List<Item> getButim() {
        return List.of(new Alimento("item_tofu", 35));
    }
}
