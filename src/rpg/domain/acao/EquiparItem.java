package rpg.domain.acao;

import rpg.domain.contract.Destrutivel;
import rpg.domain.event.Notificador;
import rpg.domain.item.Equipavel;
import rpg.domain.model.Aliado;

/** Equipa um item da mochila. Gasta o turno, como qualquer outra ação. */
public class EquiparItem implements Acao {

    private final Aliado dono;
    private final Equipavel item;

    public EquiparItem(Aliado dono, Equipavel item) {
        this.dono = dono;
        this.item = item;
    }

    @Override
    public String getRotulo(Notificador notificador) {
        return notificador.traduzir("action_equip", notificador.traduzir(item.getChaveNome()));
    }

    @Override
    public boolean estaDisponivel() {
        return true;
    }

    @Override
    public void executar(Destrutivel alvo, Notificador notificador) {
        dono.equipar(item, notificador);
    }
}
