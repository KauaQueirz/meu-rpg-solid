package rpg.domain.item;

/**
 * Qualquer objeto que possa ser carregado no inventário.
 * Contrato mínimo (ISP): cada tipo de item acrescenta só o que lhe interessa,
 * em Consumivel ou Equipavel.
 */
public interface Item {

    /** Chave de tradução do nome do item (ex.: item_health_potion). */
    String getChaveNome();
}
