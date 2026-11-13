package rpg.domain.model;

import rpg.domain.contract.HabilidadeEspecial;
import java.util.List;
import rpg.domain.acao.Acao;
import rpg.domain.acao.UsarHabilidade;
import rpg.domain.contract.Destrutivel;
import rpg.domain.event.Notificador;

/** Herói forte e resistente. Habilidade: Golpe Brutal (consome vigor). */
public class Guerreiro extends Heroi implements HabilidadeEspecial {

    private static final int VIGOR_MAX = 15;
    private static final int CUSTO_GOLPE = 5;
    private static final int BONUS_GOLPE = 6;

    private int vigor = VIGOR_MAX;

    public Guerreiro(String nome) {
        super(nome, 45, 9, 4);
    }

    @Override
    public String getChaveHabilidade() {
        return "skill_brutal_strike";
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
    protected List<Acao> acoesEspecificas() {
        return List.of(new UsarHabilidade(this));
    }

    @Override
    public void mostrarStatus(Notificador notificador) {
        super.mostrarStatus(notificador);
        notificador.notificar("status_stamina", getNome(), vigor, VIGOR_MAX);
    }
}
