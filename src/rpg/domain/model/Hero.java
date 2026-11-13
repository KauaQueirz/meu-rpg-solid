package rpg.domain.model;

import java.util.List;
import rpg.domain.acao.Acao;
import rpg.domain.acao.UsarHabilidade;
import rpg.domain.contract.Destrutivel;
import rpg.domain.contract.HabilidadeEspecial;
import rpg.domain.event.Notificador;

/**
 * Amigo de apoio. Habilidade: Banquete de Panquecas, que cura todos os amigos vivos (consome suco).
 * É a prova do OCP: um personagem novo, com mecânica própria, sem alterar o combate.
 */
public class Hero extends Aliado implements HabilidadeEspecial {

    private static final int SUCO_MAX = 25;
    private static final int CUSTO_HABILIDADE = 8;
    private static final int CURA_DO_BANQUETE = 30;

    private int suco = SUCO_MAX;
    private List<Aliado> amigos = List.of();

    public Hero(String nome) {
        super(nome, 100, 8, 3);
    }

    /** Define quem recebe a cura do banquete (o grupo é montado no Main). */
    public void setAmigos(List<Aliado> amigos) {
        this.amigos = amigos;
    }

    @Override
    public String getChaveHabilidade() {
        return "skill_hero";
    }

    /** Só vale a pena servir o banquete se houver suco e algum amigo vivo e ferido. */
    @Override
    public boolean podeUsarHabilidade() {
        if (suco < CUSTO_HABILIDADE) {
            return false;
        }
        for (Aliado amigo : amigos) {
            if (!amigo.estaDestruido() && amigo.getVidaAtual() < amigo.getVidaMax()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void usarHabilidade(Destrutivel alvo, Notificador notificador) {
        if (!podeUsarHabilidade()) {
            notificador.notificar("no_juice", getNome());
            return;
        }
        suco -= CUSTO_HABILIDADE;
        notificador.notificar("hero_skill", getNome());
        for (Aliado amigo : amigos) {
            if (!amigo.estaDestruido() && amigo.getVidaAtual() < amigo.getVidaMax()) {
                amigo.curar(CURA_DO_BANQUETE, notificador);
            }
        }
    }

    @Override
    protected List<Acao> acoesEspecificas() {
        return List.of(new UsarHabilidade(this));
    }

    @Override
    protected void restaurarRecurso() {
        suco = SUCO_MAX;
    }

    @Override
    protected String getChaveAtaque() {
        return "attack_hero";
    }

    @Override
    public void mostrarStatus(Notificador notificador) {
        super.mostrarStatus(notificador);
        notificador.notificar("status_juice", getNome(), suco, SUCO_MAX);
    }
}
