package rpg.domain.model;

import java.util.List;
import rpg.domain.item.Alimento;
import rpg.domain.item.Item;

/** Primeiro chefe (Space Ex-Boyfriend). Habilidade: Raio Espacial. */
public class ExNamoradoEspacial extends Chefe {

    public ExNamoradoEspacial(String nome) {
        super(nome, 220, 44, 4, 3, 16, "skill_space_beam", "space_beam");
    }

    @Override
    public String getChaveIntroducao() {
        return "intro_space_ex";
    }

    @Override
    protected String getChaveAtaque() {
        return "attack_space_ex";
    }

    @Override
    public List<Item> getButim() {
        return List.of(new Alimento("item_life_jam", 150));
    }
}
