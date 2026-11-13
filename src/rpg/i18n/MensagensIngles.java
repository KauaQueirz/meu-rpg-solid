package rpg.i18n;

/** Textos do jogo em inglês. */
public class MensagensIngles extends MensagensBase {

    public MensagensIngles() {
        // Atenção: o MessageFormat trata o apóstrofo como símbolo especial; para usá-lo, escreva duas vezes ('').
        textos.put("welcome", "=========================================\n  Projeto RPG - SOLID: battles in White Space\n  (academic tribute to the game OMORI)\n=========================================");
        textos.put("prologue", "In a quiet, white space, four friends meet again: Omori, Aubrey, Kel and Hero.\nTo move on, they must face whatever shows up along the way.\nTogether, they are stronger!");
        textos.put("enemy_sprout_mole", "Sprout Mole");
        textos.put("enemy_forest_bunny", "Forest Bunny");
        textos.put("enemy_space_ex", "Space Ex-Boyfriend");
        textos.put("enemy_sweetheart", "Sweetheart");
        textos.put("battle_start", "\n>>> {0} joins the battle! <<<");
        textos.put("intro_sprout_mole", "A mole with a sprout on its head pops out of the ground and starts chasing the group.");
        textos.put("intro_forest_bunny", "A huge bunny leaps from behind the trees, eyes fixed on the friends.");
        textos.put("intro_space_ex", "The stars twinkle and a floating figure appears in the sky, very dramatic.");
        textos.put("intro_sweetheart", "A candy castle lights up and Sweetheart, full of herself, blocks the last passage.");
        textos.put("round", "\n=============== Round {0} ===============");
        textos.put("summary", "  {0}: [{3}] {1}/{2}");
        textos.put("turn_header", "\n--- {0}''s turn ---");
        textos.put("status", "{0} - Health: [{3}] {1}/{2}");
        textos.put("status_focus", "{0} - Focus: {1}/{2}");
        textos.put("status_courage", "{0} - Courage: {1}/{2}");
        textos.put("status_energy", "{0} - Energy: {1}/{2}");
        textos.put("status_juice", "{0} - Juice: {1}/{2}");
        textos.put("sheet_stats", "  Attack: {0} ({1})  |  Defense: {2} ({3})");
        textos.put("sheet_equipped", "  Equipped: {0}");
        textos.put("sheet_bag", "  Bag: {0}");
        textos.put("sheet_none", "nothing");
        textos.put("bag_empty", "empty");
        textos.put("menu_option", "  {0}) {1}");
        textos.put("menu_option_off", "  {0}) {1} (unavailable)");
        textos.put("menu_prompt", "Choose an action:");
        textos.put("invalid_option", "Invalid option. Enter a number from 1 to {0}.");
        textos.put("action_unavailable", "{0} cannot be used right now. Choose another action.");
        textos.put("enemy_turn", "\n* {0}''s turn *");
        textos.put("action_attack", "Basic attack");
        textos.put("action_use_item", "Use {0} (x{1})");
        textos.put("action_equip", "Equip {0}");
        textos.put("skill_omori", "Precise Stab");
        textos.put("skill_aubrey", "Furious Swing");
        textos.put("skill_kel", "Ball Barrage");
        textos.put("skill_hero", "Pancake Feast");
        textos.put("skill_space_beam", "Space Beam");
        textos.put("skill_sweet_scream", "Sweet Scream");
        textos.put("attack", "{0} attacks {1}!");
        textos.put("attack_omori", "{0} steps forward and slashes {1} with the knife!");
        textos.put("attack_aubrey", "{0} spins the bat and hits {1}!");
        textos.put("attack_kel", "{0} throws a ball at {1}!");
        textos.put("attack_hero", "{0} whacks {1} with a wooden spoon!");
        textos.put("attack_sprout_mole", "{0} charges at {1} with its roots!");
        textos.put("attack_forest_bunny", "{0} leaps and bites {1}!");
        textos.put("attack_space_ex", "{0} casts an icy glare at {1}!");
        textos.put("attack_sweetheart", "{0} hits {1} with a sweet, sticky blow!");
        textos.put("omori_skill", "{0} focuses and drives the blade into {1}: Precise Stab!");
        textos.put("aubrey_skill", "{0} yells and brings the bat down on {1} with all her strength: Furious Swing!");
        textos.put("kel_skill", "{0} throws several balls in a row at {1}: Ball Barrage!");
        textos.put("hero_skill", "{0} serves a feast of warm pancakes for everyone!");
        textos.put("space_beam", "{0} fires a Space Beam at {1}!");
        textos.put("sweet_scream", "{0} lets out a Sweet Scream that echoes at {1}!");
        textos.put("no_focus", "{0} has no focus left!");
        textos.put("no_courage", "{0} has no courage left!");
        textos.put("no_energy", "{0} has no energy left!");
        textos.put("no_juice", "{0} cannot serve the feast right now!");
        textos.put("no_charges", "{0} is out of breath!");
        textos.put("damage_received", "  {0} takes {1} damage.  [{4}] {2}/{3}");
        textos.put("healed", "  {0} recovers {1} health.  [{4}] {2}/{3}");
        textos.put("defeated", "  {0} was defeated!");
        textos.put("fainted", "  {0} fainted!");
        textos.put("revived", "  {0} gets up, still a little dizzy.");
        textos.put("rest", "\nThe friends rest for a moment before moving on...");
        textos.put("battle_won", "\nBattle won!");
        textos.put("victory_game", "\nThe friends crossed White Space together. Congratulations, the adventure is over!");
        textos.put("defeat", "\nThe friends could not go on. Game over.");
        textos.put("item_tofu", "Tofu");
        textos.put("item_candy", "Candy");
        textos.put("item_life_jam", "Life Jam");
        textos.put("item_shiny_knife", "Shiny Knife");
        textos.put("item_baseball_bat", "Baseball Bat");
        textos.put("item_comfy_sweater", "Comfy Sweater");
        textos.put("item_used", "{0} uses {1}.");
        textos.put("equipped", "{0} equipped {1} (+{2} attack, +{3} defense).");
        textos.put("loot_found", "{0} found: {1}!");
    }
}
