package rpg.domain.model;

import rpg.domain.contract.HabilidadeEspecial;
import rpg.domain.contract.Destrutivel;
import rpg.domain.event.Notificador;

/** Chefão. Habilidade: Sopro de Fogo (poucas cargas, dano alto). */
public class Dragao extends Inimigo implements HabilidadeEspecial {

    private static final int CARGAS_MAX = 3;
    private static final int BONUS_SOPRO = 8;

    private int cargas = CARGAS_MAX;

    public Dragao(String nome) {
        super(nome, 80, 14, 3);
    }

    @Override
    public String getNomeHabilidade() {
        return "Sopro de Fogo";
    }

    @Override
    public boolean podeUsarHabilidade() {
        return cargas > 0;
    }

    @Override
    public void usarHabilidade(Destrutivel alvo, Notificador notificador) {
        if (!podeUsarHabilidade()) {
            notificador.notificar("no_breath", getNome());
            return;
        }
        cargas--;
        notificador.notificar("dragon_breath", getNome(), alvo.getNome());
        alvo.receberDano(getAtaqueTotal() + BONUS_SOPRO, notificador);
    }
}
