package rpg.domain.item;

/** Equipamento que aumenta o ataque. */
public class Arma implements Equipavel {

    private final String chaveNome;
    private final int bonusAtaque;

    public Arma(String chaveNome, int bonusAtaque) {
        this.chaveNome = chaveNome;
        this.bonusAtaque = bonusAtaque;
    }

    @Override
    public String getChaveNome() { return chaveNome; }

    @Override
    public Slot getSlot() { return Slot.ARMA; }

    @Override
    public int getBonusAtaque() { return bonusAtaque; }

    @Override
    public int getBonusDefesa() { return 0; }
}
