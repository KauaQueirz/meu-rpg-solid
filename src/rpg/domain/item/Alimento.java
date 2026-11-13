package rpg.domain.item;

import rpg.domain.event.Notificador;
import rpg.domain.model.Personagem;

/** Consumível que recupera vida (Tofu, Doce, Geleia da Vida...). Só muda o nome e a quantidade. */
public class Alimento implements Consumivel {

    private final String chaveNome;
    private final int pontosDeCura;

    public Alimento(String chaveNome, int pontosDeCura) {
        this.chaveNome = chaveNome;
        this.pontosDeCura = pontosDeCura;
    }

    @Override
    public String getChaveNome() {
        return chaveNome;
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
