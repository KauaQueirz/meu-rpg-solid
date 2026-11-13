package rpg.domain.model;

/** Chefe final (Sweetheart). Habilidade: Grito Doce. */
public class Sweetheart extends Chefe {

    public Sweetheart(String nome) {
        super(nome, 320, 52, 5, 4, 18, "skill_sweet_scream", "sweet_scream");
    }

    @Override
    public String getChaveIntroducao() {
        return "intro_sweetheart";
    }

    @Override
    protected String getChaveAtaque() {
        return "attack_sweetheart";
    }
}
