package rpg.domain.model;

import rpg.domain.contract.HabilidadeEspecial;
import rpg.domain.contract.Destrutivel;
import rpg.domain.event.Notificador;

/** Herói frágil com dano elevado. Habilidade: Bola de Fogo (consome mana). */
public class Mago extends Personagem implements HabilidadeEspecial {

    private static final int MANA_MAX = 30;
    private static final int CUSTO_MAGIA = 8;
    private static final int BONUS_MAGIA = 10;

    private int mana = MANA_MAX;

    public Mago(String nome) {
        super(nome, 30, 7, 1);
    }

    @Override
    public String getNomeHabilidade() {
        return "Bola de Fogo";
    }

    @Override
    public boolean podeUsarHabilidade() {
        return mana >= CUSTO_MAGIA;
    }

    @Override
    public void usarHabilidade(Destrutivel alvo, Notificador notificador) {
        if (!podeUsarHabilidade()) {
            notificador.notificar("no_mana", getNome());
            return;
        }
        mana -= CUSTO_MAGIA;
        notificador.notificar("fireball", getNome(), alvo.getNome());
        alvo.receberDano(getAtaqueTotal() + BONUS_MAGIA, notificador);
    }

    @Override
    public void mostrarStatus(Notificador notificador) {
        super.mostrarStatus(notificador);
        notificador.notificar("status_mana", getNome(), mana, MANA_MAX);
    }
}
