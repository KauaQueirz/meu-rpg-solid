package rpg.domain.model;

import java.util.List;
import rpg.domain.acao.Acao;
import rpg.domain.acao.UsarHabilidade;
import rpg.domain.contract.Destrutivel;
import rpg.domain.contract.HabilidadeEspecial;
import rpg.domain.event.Notificador;

/** Amigo ágil e animado. Habilidade: Chuva de Bolas (dois arremessos seguidos, consome energia). */
public class Kel extends Aliado implements HabilidadeEspecial {

    private static final int ENERGIA_MAX = 18;
    private static final int CUSTO_HABILIDADE = 3;
    private static final int BONUS_POR_ARREMESSO = 4;

    private int energia = ENERGIA_MAX;

    public Kel(String nome) {
        super(nome, 120, 11, 3);
    }

    @Override
    public String getChaveHabilidade() {
        return "skill_kel";
    }

    @Override
    public boolean podeUsarHabilidade() {
        return energia >= CUSTO_HABILIDADE;
    }

    @Override
    public void usarHabilidade(Destrutivel alvo, Notificador notificador) {
        if (!podeUsarHabilidade()) {
            notificador.notificar("no_energy", getNome());
            return;
        }
        energia -= CUSTO_HABILIDADE;
        notificador.notificar("kel_skill", getNome(), alvo.getNome());
        alvo.receberDano(getAtaqueTotal() + BONUS_POR_ARREMESSO, notificador);
        if (!alvo.estaDestruido()) {
            alvo.receberDano(getAtaqueTotal() + BONUS_POR_ARREMESSO, notificador);
        }
    }

    @Override
    protected List<Acao> acoesEspecificas() {
        return List.of(new UsarHabilidade(this));
    }

    @Override
    protected void restaurarRecurso() {
        energia = ENERGIA_MAX;
    }

    @Override
    protected String getChaveAtaque() {
        return "attack_kel";
    }

    @Override
    public void mostrarStatus(Notificador notificador) {
        super.mostrarStatus(notificador);
        notificador.notificar("status_energy", getNome(), energia, ENERGIA_MAX);
    }
}
