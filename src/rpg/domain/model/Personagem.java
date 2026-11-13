package rpg.domain.model;

import java.util.ArrayList;
import java.util.List;
import rpg.domain.acao.Acao;
import rpg.domain.acao.AtaqueBasico;
import rpg.domain.contract.Destrutivel;
import rpg.domain.event.Notificador;

/**
 * Base abstrata de todos os combatentes (amigos e inimigos).
 *
 * Encapsulamento: atributos privados; a vida só muda por receberDano() e curar(),
 * que garantem as regras (nunca abaixo de 0 nem acima do máximo).
 *
 * SRP: a classe não imprime nada; só avisa o Notificador.
 * LSP: qualquer subclasse pode ser usada onde se espera um Personagem.
 */
public abstract class Personagem implements Destrutivel {

    private final String nome;
    private final int vidaMax;
    private int vidaAtual;
    private final int ataqueBase;
    private final int defesa;

    protected Personagem(String nome, int vidaMax, int ataqueBase, int defesa) {
        if (vidaMax <= 0) {
            throw new IllegalArgumentException("A vida máxima deve ser maior que zero.");
        }
        this.nome = nome;
        this.vidaMax = vidaMax;
        this.vidaAtual = vidaMax;
        this.ataqueBase = ataqueBase;
        this.defesa = defesa;
    }

    /** Ataque comum. Aceita qualquer Destrutivel (monstro, baú, portão...). */
    public void atacar(Destrutivel alvo, Notificador notificador) {
        notificador.notificar(getChaveAtaque(), nome, alvo.getNome());
        alvo.receberDano(getAtaqueTotal(), notificador);
    }

    /** Cada personagem narra o seu ataque do seu jeito (polimorfismo). */
    protected String getChaveAtaque() {
        return "attack";
    }

    /** Mensagem exibida quando o personagem cai. */
    protected String getChaveDerrota() {
        return "defeated";
    }

    /**
     * Ações que o personagem pode escolher no turno. Todos podem atacar; as subclasses
     * acrescentam as suas (polimorfismo).
     */
    public List<Acao> getAcoes() {
        List<Acao> acoes = new ArrayList<>();
        acoes.add(new AtaqueBasico(this));
        return acoes;
    }

    /** Ponto de extensão: Aliado soma aqui os bônus dos equipamentos. */
    public int getAtaqueTotal() {
        return ataqueBase;
    }

    public int getDefesaTotal() {
        return defesa;
    }

    @Override
    public void receberDano(int valor, Notificador notificador) {
        int danoEfetivo = Math.max(1, valor - getDefesaTotal());
        vidaAtual = Math.max(0, vidaAtual - danoEfetivo);
        notificador.notificar("damage_received", nome, danoEfetivo, vidaAtual, vidaMax,
                notificador.barra(vidaAtual, vidaMax));
        if (estaDestruido()) {
            notificador.notificar(getChaveDerrota(), nome);
        }
    }

    public void curar(int pontos, Notificador notificador) {
        int antes = vidaAtual;
        vidaAtual = Math.min(vidaMax, vidaAtual + Math.max(0, pontos));
        notificador.notificar("healed", nome, vidaAtual - antes, vidaAtual, vidaMax,
                notificador.barra(vidaAtual, vidaMax));
    }

    /** Linha de vida com barra. As subclasses podem acrescentar o seu recurso (foco, suco...). */
    public void mostrarStatus(Notificador notificador) {
        notificador.notificar("status", nome, vidaAtual, vidaMax, notificador.barra(vidaAtual, vidaMax));
    }

    /** Resumo de uma linha, usado no início de cada rodada. */
    public void mostrarResumo(Notificador notificador) {
        notificador.notificar("summary", nome, vidaAtual, vidaMax, notificador.barra(vidaAtual, vidaMax));
    }

    @Override
    public boolean estaDestruido() {
        return vidaAtual == 0;
    }

    @Override
    public String getNome() { return nome; }

    public int getVidaMax() { return vidaMax; }

    public int getVidaAtual() { return vidaAtual; }

    public int getAtaqueBase() { return ataqueBase; }

    public int getDefesa() { return defesa; }
}
