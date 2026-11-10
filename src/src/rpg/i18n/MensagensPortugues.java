package rpg.i18n;

public class MensagensPortugues extends MensagensBase {

    public MensagensPortugues() {
        // Atenção: evite apóstrofo (') nos textos, pois o MessageFormat o trata como símbolo especial.
        textos.put("attack", "{0} ataca {1}!");
        textos.put("damage_received", "{0} recebeu {1} de dano (vida: {2}).");
        textos.put("defeated", "{0} foi derrotado!");
        textos.put("healed", "{0} recuperou {1} de vida (vida: {2}).");
        textos.put("status", "{0} - Vida: {1}/{2}");
        textos.put("brutal_strike", "{0} usa Golpe Brutal em {1}!");
        textos.put("fireball", "{0} lança Bola de Fogo em {1}!");
        textos.put("arrow_rain", "{0} dispara Chuva de Flechas em {1}!");
        textos.put("dragon_breath", "{0} cospe Sopro de Fogo em {1}!");
        textos.put("no_stamina", "{0} está sem vigor!");
        textos.put("no_mana", "{0} está sem mana!");
        textos.put("no_arrows", "{0} está sem flechas suficientes!");
        textos.put("no_breath", "{0} está sem fôlego!");
        textos.put("status_stamina", "{0} - Vigor: {1}/{2}");
        textos.put("status_mana", "{0} - Mana: {1}/{2}");
        textos.put("status_arrows", "{0} - Flechas: {1}/{2}");
        textos.put("welcome", "=== Projeto RPG - SOLID ===");
        textos.put("battle_start", "\n>>> Uma batalha começa: {0} aparece! <<<");
        textos.put("round", "\n=== Rodada {0} ===");
        textos.put("turn_header", "\n--- Turno de {0} ---");
        textos.put("menu_option", "  {0}) {1}");
        textos.put("menu_option_off", "  {0}) {1} (indisponível)");
        textos.put("menu_prompt", "Escolha uma ação:");
        textos.put("invalid_option", "Opção inválida. Digite um número de 1 a {0}.");
        textos.put("action_unavailable", "{0} não pode ser usada agora. Escolha outra ação.");
        textos.put("battle_won", "Batalha vencida!");
        textos.put("victory_game", "\nParabéns! Os heróis venceram a aventura!");
        textos.put("defeat", "\nTodos os heróis caíram. Fim de jogo.");
        textos.put("action_attack", "Ataque básico");
        textos.put("skill_brutal_strike", "Golpe Brutal");
        textos.put("skill_fireball", "Bola de Fogo");
        textos.put("skill_arrow_rain", "Chuva de Flechas");
        textos.put("skill_dragon_breath", "Sopro de Fogo");
    }
}
