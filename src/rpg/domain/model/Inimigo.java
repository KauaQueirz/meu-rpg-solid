package rpg.domain.model;

import java.util.List;
import rpg.domain.acao.Acao;
import rpg.domain.contract.Destrutivel;
import rpg.domain.event.Notificador;
import rpg.domain.item.Item;

/**
 * Base dos adversários (Goblin, Dragao...). É um Personagem como qualquer outro (LSP).
 * Quem não tem habilidade especial simplesmente não implementa HabilidadeEspecial (ISP).
 */
public abstract class Inimigo extends Personagem {

    protected Inimigo(String nome, int vidaMax, int ataque, int defesa) {
        super(nome, vidaMax, ataque, defesa);
    }

    /**
     * IA simples: usa a última ação disponível da lista, que é a mais forte
     * (o ataque básico vem primeiro; habilidades são acrescentadas depois).
     */
    public void agir(Destrutivel alvo, Notificador notificador) {
        List<Acao> acoes = getAcoes();
        for (int i = acoes.size() - 1; i >= 0; i--) {
            if (acoes.get(i).estaDisponivel()) {
                acoes.get(i).executar(alvo, notificador);
                return;
            }
        }
    }

    /** Itens deixados ao ser derrotado. Eles continuam existindo depois do inimigo (agregação). */
    public List<Item> getButim() {
        return List.of();
    }
}
