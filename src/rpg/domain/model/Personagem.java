package rpg.domain.model;

import java.util.ArrayList;
import java.util.List;
import rpg.domain.acao.Acao;
import rpg.domain.acao.AtaqueBasico;
import rpg.domain.contract.Destrutivel;
import rpg.domain.event.Notificador;

/**
 * Base abstrata de todos os combatentes (heróis e inimigos).
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
        int dano = getAtaqueTotal();
        notificador.notificar("attack", nome, alvo.getNome());
        alvo.receberDano(dano, notificador);
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

    /** Ponto de extensão: Heroi soma aqui os bônus dos equipamentos. */
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
        notificador.notificar("damage_received", nome, danoEfetivo, vidaAtual);
        if (estaDestruido()) {
            notificador.notificar("defeated", nome);
        }
    }

    public void curar(int pontos, Notificador notificador) {
        int antes = vidaAtual;
        vidaAtual = Math.min(vidaMax, vidaAtual + Math.max(0, pontos));
        notificador.notificar("healed", nome, vidaAtual - antes, vidaAtual);
    }

    public void mostrarStatus(Notificador notificador) {
        notificador.notificar("status", nome, vidaAtual, vidaMax);
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
