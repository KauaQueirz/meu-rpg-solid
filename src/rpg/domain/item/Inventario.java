package rpg.domain.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Mochila de um herói.
 *
 * Relação com Item: AGREGAÇÃO. Um item existe independentemente do inventário:
 * pode ser trocado, equipado, largado ou encontrado como butim de um inimigo.
 * O inventário em si pertence ao herói (COMPOSIÇÃO): nasce e morre com ele.
 */
public class Inventario {

    private final List<Item> itens = new ArrayList<>();

    public void adicionar(Item item) {
        itens.add(item);
    }

    public void remover(Item item) {
        itens.remove(item);
    }

    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public List<Consumivel> getConsumiveis() {
        List<Consumivel> resultado = new ArrayList<>();
        for (Item item : itens) {
            if (item instanceof Consumivel consumivel) {
                resultado.add(consumivel);
            }
        }
        return resultado;
    }

    public List<Equipavel> getEquipaveis() {
        List<Equipavel> resultado = new ArrayList<>();
        for (Item item : itens) {
            if (item instanceof Equipavel equipavel) {
                resultado.add(equipavel);
            }
        }
        return resultado;
    }
}
