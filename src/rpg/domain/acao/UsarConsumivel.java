package rpg.domain.acao;

import rpg.domain.contract.Destrutivel;
import rpg.domain.event.Notificador;
import rpg.domain.item.Consumivel;
import rpg.domain.model.Aliado;

/** Usa um consumível do inventário (ex.: poção de cura) em si mesmo e gasta o item. */
public class UsarConsumivel implements Acao {

    private final Aliado dono;
    private final Consumivel item;
    private final int quantidade;

    public UsarConsumivel(Aliado dono, Consumivel item, int quantidade) {
        this.dono = dono;
        this.item = item;
        this.quantidade = quantidade;
    }

    @Override
    public String getRotulo(Notificador notificador) {
        return notificador.traduzir("action_use_item", notificador.traduzir(item.getChaveNome()), quantidade);
    }

    @Override
    public boolean estaDisponivel() {
        return item.podeSerUsadoEm(dono);
    }

    @Override
    public void executar(Destrutivel alvo, Notificador notificador) {
        item.usar(dono, notificador);
        dono.getInventario().remover(item);
    }
}
