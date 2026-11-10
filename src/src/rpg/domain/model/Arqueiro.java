package rpg.domain.model;

import rpg.domain.contract.HabilidadeEspecial;
import rpg.domain.contract.Destrutivel;
import rpg.domain.event.Notificador;

/** Herói ágil. Habilidade: Chuva de Flechas (dois disparos seguidos, consome flechas). */
public class Arqueiro extends Personagem implements HabilidadeEspecial {

    private static final int FLECHAS_MAX = 12;
    private static final int CUSTO_CHUVA = 3;
    private static final int BONUS_CHUVA = 3;

    private int flechas = FLECHAS_MAX;

    public Arqueiro(String nome) {
        super(nome, 35, 8, 2);
    }

    @Override
    public String getNomeHabilidade() {
        return "Chuva de Flechas";
    }

    @Override
    public boolean podeUsarHabilidade() {
        return flechas >= CUSTO_CHUVA;
    }

    @Override
    public void usarHabilidade(Destrutivel alvo, Notificador notificador) {
        if (!podeUsarHabilidade()) {
            notificador.notificar("no_arrows", getNome());
            return;
        }
        flechas -= CUSTO_CHUVA;
        notificador.notificar("arrow_rain", getNome(), alvo.getNome());
        alvo.receberDano(getAtaqueTotal() + BONUS_CHUVA, notificador);
        if (!alvo.estaDestruido()) {
            alvo.receberDano(getAtaqueTotal() + BONUS_CHUVA, notificador);
        }
    }

    @Override
    public void mostrarStatus(Notificador notificador) {
        super.mostrarStatus(notificador);
        notificador.notificar("status_arrows", getNome(), flechas, FLECHAS_MAX);
    }
}
