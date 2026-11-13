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
import rpg.domain.item.Item;
import rpg.domain.item.Slot;

/**
 * Base dos personagens controlados pelo jogador (Omori, Aubrey, Kel, Hero).
 * Acrescenta ao Personagem o inventário, os equipamentos, a ficha de turno e o descanso.
 * As subclasses herdam isso sem repetir código (reuso por herança).
 */
public abstract class Aliado extends Personagem {

    private static final int PERCENTUAL_DESCANSO = 35;

    private final Inventario inventario = new Inventario();
    private final Map<Slot, Equipavel> equipados = new EnumMap<>(Slot.class);

    protected Aliado(String nome, int vidaMax, int ataque, int defesa) {
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

    @Override
    protected String getChaveDerrota() {
        return "fainted";
    }

    /** Recupera o recurso próprio da classe (foco, coragem, energia, suco). */
    protected abstract void restaurarRecurso();

    /** Descanso entre batalhas: recupera parte da vida (até quem desmaiou se levanta) e o recurso. */
    public void descansar(Notificador notificador) {
        if (estaDestruido()) {
            notificador.notificar("revived", getNome());
        }
        if (getVidaAtual() < getVidaMax()) {
            curar(getVidaMax() * PERCENTUAL_DESCANSO / 100, notificador);
        }
        restaurarRecurso();
    }

    /** Ficha completa exibida no turno: vida, recurso, bônus dos equipamentos e mochila. */
    public void mostrarFicha(Notificador notificador) {
        mostrarStatus(notificador);
        notificador.notificar("sheet_stats",
                getAtaqueTotal(), comSinal(getAtaqueTotal() - getAtaqueBase()),
                getDefesaTotal(), comSinal(getDefesaTotal() - getDefesa()));
        notificador.notificar("sheet_equipped", descreverEquipados(notificador));
        notificador.notificar("sheet_bag", descreverMochila(notificador));
    }

    private String comSinal(int valor) {
        return (valor >= 0 ? "+" : "") + valor;
    }

    private String descreverEquipados(Notificador notificador) {
        if (equipados.isEmpty()) {
            return notificador.traduzir("sheet_none");
        }
        List<String> nomes = new ArrayList<>();
        for (Equipavel equipamento : equipados.values()) {
            nomes.add(notificador.traduzir(equipamento.getChaveNome()));
        }
        return String.join(", ", nomes);
    }

    private String descreverMochila(Notificador notificador) {
        Map<String, Integer> contagem = new LinkedHashMap<>();
        for (Item item : inventario.getItens()) {
            contagem.merge(item.getChaveNome(), 1, Integer::sum);
        }
        if (contagem.isEmpty()) {
            return notificador.traduzir("bag_empty");
        }
        List<String> partes = new ArrayList<>();
        for (Map.Entry<String, Integer> entrada : contagem.entrySet()) {
            String nome = notificador.traduzir(entrada.getKey());
            partes.add(entrada.getValue() > 1 ? nome + " x" + entrada.getValue() : nome);
        }
        return String.join(", ", partes);
    }

    /** Ações próprias de cada personagem (habilidade especial etc.). Por padrão, nenhuma. */
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
