package rpg.domain.model;

import java.util.List;
import rpg.domain.acao.Acao;
import rpg.domain.acao.UsarHabilidade;
import rpg.domain.contract.Destrutivel;
import rpg.domain.contract.HabilidadeEspecial;
import rpg.domain.event.Notificador;

/** Amiga forte e resistente, de taco na mão. Habilidade: Tacada Furiosa (consome coragem). */
public class Aubrey extends Aliado implements HabilidadeEspecial {

    private static final int CORAGEM_MAX = 15;
    private static final int CUSTO_HABILIDADE = 5;
    private static final int BONUS_HABILIDADE = 10;

    private int coragem = CORAGEM_MAX;

    public Aubrey(String nome) {
        super(nome, 140, 13, 5);
    }

    @Override
    public String getChaveHabilidade() {
        return "skill_aubrey";
    }

    @Override
    public boolean podeUsarHabilidade() {
        return coragem >= CUSTO_HABILIDADE;
    }

    @Override
    public void usarHabilidade(Destrutivel alvo, Notificador notificador) {
        if (!podeUsarHabilidade()) {
            notificador.notificar("no_courage", getNome());
            return;
        }
        coragem -= CUSTO_HABILIDADE;
        notificador.notificar("aubrey_skill", getNome(), alvo.getNome());
        alvo.receberDano(getAtaqueTotal() + BONUS_HABILIDADE, notificador);
    }

    @Override
    protected List<Acao> acoesEspecificas() {
        return List.of(new UsarHabilidade(this));
    }

    @Override
    protected void restaurarRecurso() {
        coragem = CORAGEM_MAX;
    }

    @Override
    protected String getChaveAtaque() {
        return "attack_aubrey";
    }

    @Override
    public void mostrarStatus(Notificador notificador) {
        super.mostrarStatus(notificador);
        notificador.notificar("status_courage", getNome(), coragem, CORAGEM_MAX);
    }
}
