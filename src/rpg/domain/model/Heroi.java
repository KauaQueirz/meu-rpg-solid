package rpg.domain.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import rpg.domain.acao.Acao;
import rpg.domain.acao.EquiparItem;
import rpg.domain.acao.UsarConsumivel;
import rpg.domain.event.Notificador;
import rpg.domain.item.Consumivel;
import rpg.domain.item.Equipavel;
import rpg.domain.item.Inventario;
import rpg.domain.item.Slot;

/**
 * Base dos personagens controlados pelo jogador. Acrescenta ao Personagem o inventário e os equipamentos.
 * Guerreiro, Mago e Arqueiro herdam isso sem repetir código (reuso por herança).
 */
public abstract class Heroi extends Personagem {

    private final Inventario inventario = new Inventario();
    private final Map<Slot, Equipavel> equipados = new EnumMap<>(Slot.class);

    protected Heroi(String nome, int vidaMax, int ataque, int defesa) {
        super(nome, vidaMax, ataque, defesa);
    }

    public Inventario getInventario() {
        return inventario;
    }

    /** Equipa o item; o que estava no mesmo slot volta para o inventário (agregação). */
    public void equipar(Equipavel novo, Notificador notificador) {
        inventario.remover(novo);
        Equipavel anterior = equipados.put(novo.getSlot(), novo);
        if (anterior != null) {
            inventario.adicionar(anterior);
        }
        notificador.notificar("equipped", getNome(), notificador.traduzir(novo.getChaveNome()),
                novo.getBonusAtaque(), novo.getBonusDefesa());
    }

    @Override
    public int getAtaqueTotal() {
        int total = super.getAtaqueTotal();
        for (Equipavel equipamento : equipados.values()) {
            total += equipamento.getBonusAtaque();
        }
        return total;
    }

    @Override
    public int getDefesaTotal() {
        int total = super.getDefesaTotal();
        for (Equipavel equipamento : equipados.values()) {
            total += equipamento.getBonusDefesa();
        }
        return total;
    }

    /** Ações próprias de cada classe de herói (habilidade especial etc.). Por padrão, nenhuma. */
    protected List<Acao> acoesEspecificas() {
        return List.of();
    }

    /** Ataque + ações específicas + uma ação por tipo de consumível + uma por equipamento na mochila. */
    @Override
    public List<Acao> getAcoes() {
        List<Acao> acoes = super.getAcoes();
        acoes.addAll(acoesEspecificas());

        Map<String, List<Consumivel>> grupos = new LinkedHashMap<>();
        for (Consumivel consumivel : inventario.getConsumiveis()) {
            grupos.computeIfAbsent(consumivel.getChaveNome(), chave -> new ArrayList<>()).add(consumivel);
        }
        for (List<Consumivel> grupo : grupos.values()) {
            acoes.add(new UsarConsumivel(this, grupo.get(0), grupo.size()));
        }
        for (Equipavel equipavel : inventario.getEquipaveis()) {
            acoes.add(new EquiparItem(this, equipavel));
        }
        return acoes;
    }
}
