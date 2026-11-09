package rpg.domain.model;

import rpg.domain.contract.Destrutivel;
import rpg.domain.event.CombatNotifier;

/**
 * Base abstrata de todos os combatentes (heróis e inimigos).
 *
 * Encapsulamento: atributos privados; a vida só muda por receberDano() e curar(),
 * que garantem as regras (nunca abaixo de 0 nem acima do máximo).
 *
 * SRP: a classe não imprime nada; só avisa o CombatNotifier.
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
    public void atacar(Destrutivel alvo, CombatNotifier notifier) {
        int dano = getAtaqueTotal();
        notifier.notify("attack", nome, alvo.getNome());
        alvo.receberDano(dano, notifier);
    }

    /** Ponto de extensão: nos próximos dias, equipamentos somarão bônus aqui. */
    public int getAtaqueTotal() {
        return ataqueBase;
    }

    @Override
    public void receberDano(int valor, CombatNotifier notifier) {
        int danoEfetivo = Math.max(1, valor - defesa);
        vidaAtual = Math.max(0, vidaAtual - danoEfetivo);
        notifier.notify("damage_received", nome, danoEfetivo, vidaAtual);
        if (estaDestruido()) {
            notifier.notify("defeated", nome);
        }
    }

    public void curar(int pontos, CombatNotifier notifier) {
        int antes = vidaAtual;
        vidaAtual = Math.min(vidaMax, vidaAtual + Math.max(0, pontos));
        notifier.notify("healed", nome, vidaAtual - antes, vidaAtual);
    }

    public void mostrarStatus(CombatNotifier notifier) {
        notifier.notify("status", nome, vidaAtual, vidaMax);
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
