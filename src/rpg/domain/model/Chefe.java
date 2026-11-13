package rpg.domain.model;

import java.util.List;
import rpg.domain.acao.Acao;
import rpg.domain.acao.UsarHabilidade;
import rpg.domain.contract.Destrutivel;
import rpg.domain.contract.HabilidadeEspecial;
import rpg.domain.event.Notificador;

/**
 * Inimigo forte, com uma habilidade de poucas cargas. Os chefes concretos só informam
 * seus números e textos; a lógica da habilidade fica aqui, sem repetição.
 */
public abstract class Chefe extends Inimigo implements HabilidadeEspecial {

    private int cargas;
    private final int bonusHabilidade;
    private final String chaveHabilidade;
    private final String chaveEvento;

    protected Chefe(String nome, int vidaMax, int ataque, int defesa,
                    int cargas, int bonusHabilidade, String chaveHabilidade, String chaveEvento) {
        super(nome, vidaMax, ataque, defesa);
        this.cargas = cargas;
        this.bonusHabilidade = bonusHabilidade;
        this.chaveHabilidade = chaveHabilidade;
        this.chaveEvento = chaveEvento;
    }

    @Override
    public String getChaveHabilidade() {
        return chaveHabilidade;
    }

    @Override
    public boolean podeUsarHabilidade() {
        return cargas > 0;
    }

    @Override
    public void usarHabilidade(Destrutivel alvo, Notificador notificador) {
        if (!podeUsarHabilidade()) {
            notificador.notificar("no_charges", getNome());
            return;
        }
        cargas--;
        notificador.notificar(chaveEvento, getNome(), alvo.getNome());
        alvo.receberDano(getAtaqueTotal() + bonusHabilidade, notificador);
    }

    @Override
    public List<Acao> getAcoes() {
        List<Acao> acoes = super.getAcoes();
        acoes.add(new UsarHabilidade(this));
        return acoes;
    }
}
