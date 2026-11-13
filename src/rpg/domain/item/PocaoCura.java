package rpg.domain.item;

import rpg.domain.event.Notificador;
import rpg.domain.model.Personagem;

/** Consumível que recupera vida. */
public class PocaoCura implements Consumivel {

    private final int pontosDeCura;

    public PocaoCura(int pontosDeCura) {
        this.pontosDeCura = pontosDeCura;
    }

    @Override
    public String getChaveNome() {
        return "item_health_potion";
    }

    @Override
    public boolean podeSerUsadoEm(Personagem alvo) {
        return alvo.getVidaAtual() < alvo.getVidaMax();
    }

    @Override
    public void usar(Personagem alvo, Notificador notificador) {
        notificador.notificar("item_used", alvo.getNome(), notificador.traduzir(getChaveNome()));
        alvo.curar(pontosDeCura, notificador);
    }
}
