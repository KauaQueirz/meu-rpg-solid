package rpg.domain.model;

import rpg.domain.contract.HabilidadeEspecial;
import rpg.domain.contract.Destrutivel;
import rpg.domain.event.Notificador;

/** Herói forte e resistente. Habilidade: Golpe Brutal (consome vigor). */
public class Guerreiro extends Personagem implements HabilidadeEspecial {

    private static final int VIGOR_MAX = 15;
    private static final int CUSTO_GOLPE = 5;
    private static final int BONUS_GOLPE = 6;

    private int vigor = VIGOR_MAX;

    public Guerreiro(String nome) {
        super(nome, 45, 9, 4);
    }

    @Override
    public String getNomeHabilidade() {
        return "Golpe Brutal";
    }

    @Override
    public boolean podeUsarHabilidade() {
        return vigor >= CUSTO_GOLPE;
    }

    @Override
    public void usarHabilidade(Destrutivel alvo, Notificador notificador) {
        if (!podeUsarHabilidade()) {
            notificador.notificar("no_stamina", getNome());
            return;
        }
        vigor -= CUSTO_GOLPE;
        notificador.notificar("brutal_strike", getNome(), alvo.getNome());
        alvo.receberDano(getAtaqueTotal() + BONUS_GOLPE, notificador);
    }

    @Override
    public void mostrarStatus(Notificador notificador) {
        super.mostrarStatus(notificador);
        notificador.notificar("status_stamina", getNome(), vigor, VIGOR_MAX);
    }
}
