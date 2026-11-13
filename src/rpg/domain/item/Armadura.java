package rpg.domain.item;

/** Equipamento que aumenta a defesa. */
public class Armadura implements Equipavel {

    private final String chaveNome;
    private final int bonusDefesa;

    public Armadura(String chaveNome, int bonusDefesa) {
        this.chaveNome = chaveNome;
        this.bonusDefesa = bonusDefesa;
    }

    @Override
    public String getChaveNome() { return chaveNome; }

    @Override
    public Slot getSlot() { return Slot.ARMADURA; }

    @Override
    public int getBonusAtaque() { return 0; }

    @Override
    public int getBonusDefesa() { return bonusDefesa; }
}
