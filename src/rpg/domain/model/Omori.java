package rpg.domain.model;

import java.util.List;
import rpg.domain.acao.Acao;
import rpg.domain.acao.UsarHabilidade;
import rpg.domain.contract.Destrutivel;
import rpg.domain.contract.HabilidadeEspecial;
import rpg.domain.event.Notificador;

/** Protagonista equilibrado, de faca na mão. Habilidade: Facada Certeira (consome foco). */
public class Omori extends Aliado implements HabilidadeEspecial {

    private static final int FOCO_MAX = 20;
    private static final int CUSTO_HABILIDADE = 6;
    private static final int BONUS_HABILIDADE = 9;

    private int foco = FOCO_MAX;

    public Omori(String nome) {
        super(nome, 110, 12, 4);
    }

    @Override
    public String getChaveHabilidade() {
        return "skill_omori";
    }

    @Override
    public boolean podeUsarHabilidade() {
        return foco >= CUSTO_HABILIDADE;
    }

    @Override
    public void usarHabilidade(Destrutivel alvo, Notificador notificador) {
        if (!podeUsarHabilidade()) {
            notificador.notificar("no_focus", getNome());
            return;
        }
        foco -= CUSTO_HABILIDADE;
        notificador.notificar("omori_skill", getNome(), alvo.getNome());
        alvo.receberDano(getAtaqueTotal() + BONUS_HABILIDADE, notificador);
    }

    @Override
    protected List<Acao> acoesEspecificas() {
        return List.of(new UsarHabilidade(this));
    }

    @Override
    protected void restaurarRecurso() {
        foco = FOCO_MAX;
    }

    @Override
    protected String getChaveAtaque() {
        return "attack_omori";
    }

    @Override
    public void mostrarStatus(Notificador notificador) {
        super.mostrarStatus(notificador);
        notificador.notificar("status_focus", getNome(), foco, FOCO_MAX);
    }
}
