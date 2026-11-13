package rpg.i18n;

public class MensagensIngles extends MensagensBase {

    public MensagensIngles() {
        textos.put("attack", "{0} attacks {1}!");
        textos.put("damage_received", "{0} took {1} damage (health: {2}).");
        textos.put("defeated", "{0} was defeated!");
        textos.put("healed", "{0} recovered {1} health (health: {2}).");
        textos.put("status", "{0} - Health: {1}/{2}");
        textos.put("brutal_strike", "{0} uses Brutal Strike on {1}!");
        textos.put("fireball", "{0} casts Fireball on {1}!");
        textos.put("arrow_rain", "{0} fires Arrow Rain at {1}!");
        textos.put("dragon_breath", "{0} breathes fire on {1}!");
        textos.put("no_stamina", "{0} is out of stamina!");
        textos.put("no_mana", "{0} is out of mana!");
        textos.put("no_arrows", "{0} does not have enough arrows!");
        textos.put("no_breath", "{0} is out of breath!");
        textos.put("status_stamina", "{0} - Stamina: {1}/{2}");
        textos.put("status_mana", "{0} - Mana: {1}/{2}");
        textos.put("status_arrows", "{0} - Arrows: {1}/{2}");
        textos.put("welcome", "=== Projeto RPG - SOLID ===");
        textos.put("battle_start", "\n>>> A battle begins: {0} appears! <<<");
        textos.put("round", "\n=== Round {0} ===");
        textos.put("turn_header", "\n--- {0}''s turn ---");
        textos.put("menu_option", "  {0}) {1}");
        textos.put("menu_option_off", "  {0}) {1} (unavailable)");
        textos.put("menu_prompt", "Choose an action:");
        textos.put("invalid_option", "Invalid option. Enter a number from 1 to {0}.");
        textos.put("action_unavailable", "{0} cannot be used right now. Choose another action.");
        textos.put("battle_won", "Battle won!");
        textos.put("victory_game", "\nCongratulations! The heroes won the adventure!");
        textos.put("defeat", "\nAll heroes have fallen. Game over.");
        textos.put("action_attack", "Basic attack");
        textos.put("skill_brutal_strike", "Brutal Strike");
        textos.put("skill_fireball", "Fireball");
        textos.put("skill_arrow_rain", "Arrow Rain");
        textos.put("skill_dragon_breath", "Dragon Breath");
        textos.put("item_health_potion", "Health Potion");
        textos.put("item_iron_sword", "Iron Sword");
        textos.put("item_leather_armor", "Leather Armor");
        textos.put("action_use_item", "Use {0} (x{1})");
        textos.put("action_equip", "Equip {0}");
        textos.put("item_used", "{0} uses {1}.");
        textos.put("equipped", "{0} equipped {1} (+{2} attack, +{3} defense).");
        textos.put("loot_found", "{0} found: {1}!");
    }
}
