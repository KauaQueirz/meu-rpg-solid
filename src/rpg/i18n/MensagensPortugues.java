package rpg.i18n;

/** Textos do jogo em português. */
public class MensagensPortugues extends MensagensBase {

    public MensagensPortugues() {
        // Atenção: o MessageFormat trata o apóstrofo como símbolo especial; para usá-lo, escreva duas vezes ('').
        textos.put("welcome", "=========================================\n  Projeto RPG - SOLID: batalhas no Espaço Branco\n  (homenagem acadêmica ao jogo OMORI)\n=========================================");
        textos.put("prologue", "Num espaço branco e silencioso, quatro amigos se reencontram: Omori, Aubrey, Kel e Hero.\nPara seguir em frente, eles precisam enfrentar o que aparecer pelo caminho.\nJuntos, são mais fortes!");
        textos.put("enemy_sprout_mole", "Toupeira Broto");
        textos.put("enemy_forest_bunny", "Coelho da Floresta");
        textos.put("enemy_space_ex", "Ex-Namorado Espacial");
        textos.put("enemy_sweetheart", "Sweetheart");
        textos.put("battle_start", "\n>>> {0} entra na batalha! <<<");
        textos.put("intro_sprout_mole", "Uma toupeira com um broto na cabeça surge da terra e começa a perseguir o grupo.");
        textos.put("intro_forest_bunny", "Um coelho enorme pula de trás das árvores, com os olhos fixos nos amigos.");
        textos.put("intro_space_ex", "As estrelas piscam e uma figura flutuante aparece no meio do céu, muito dramática.");
        textos.put("intro_sweetheart", "Um castelo de doces se ilumina e Sweetheart, cheia de si, bloqueia a última passagem.");
        textos.put("round", "\n=============== Rodada {0} ===============");
        textos.put("summary", "  {0}: [{3}] {1}/{2}");
        textos.put("turn_header", "\n--- Turno de {0} ---");
        textos.put("status", "{0} - Vida: [{3}] {1}/{2}");
        textos.put("status_focus", "{0} - Foco: {1}/{2}");
        textos.put("status_courage", "{0} - Coragem: {1}/{2}");
        textos.put("status_energy", "{0} - Energia: {1}/{2}");
        textos.put("status_juice", "{0} - Suco: {1}/{2}");
        textos.put("sheet_stats", "  Ataque: {0} ({1})  |  Defesa: {2} ({3})");
        textos.put("sheet_equipped", "  Equipado: {0}");
        textos.put("sheet_bag", "  Mochila: {0}");
        textos.put("sheet_none", "nada");
        textos.put("bag_empty", "vazia");
        textos.put("menu_option", "  {0}) {1}");
        textos.put("menu_option_off", "  {0}) {1} (indisponível)");
        textos.put("menu_prompt", "Escolha uma ação:");
        textos.put("invalid_option", "Opção inválida. Digite um número de 1 a {0}.");
        textos.put("action_unavailable", "{0} não pode ser usada agora. Escolha outra ação.");
        textos.put("enemy_turn", "\n* Vez de {0} *");
        textos.put("action_attack", "Ataque básico");
        textos.put("action_use_item", "Usar {0} (x{1})");
        textos.put("action_equip", "Equipar {0}");
        textos.put("skill_omori", "Facada Certeira");
        textos.put("skill_aubrey", "Tacada Furiosa");
        textos.put("skill_kel", "Chuva de Bolas");
        textos.put("skill_hero", "Banquete de Panquecas");
        textos.put("skill_space_beam", "Raio Espacial");
        textos.put("skill_sweet_scream", "Grito Doce");
        textos.put("attack", "{0} ataca {1}!");
        textos.put("attack_omori", "{0} avança e corta {1} com a faca!");
        textos.put("attack_aubrey", "{0} gira o taco e acerta {1}!");
        textos.put("attack_kel", "{0} arremessa uma bola em {1}!");
        textos.put("attack_hero", "{0} acerta {1} com uma colher de pau!");
        textos.put("attack_sprout_mole", "{0} investe contra {1} com as raízes!");
        textos.put("attack_forest_bunny", "{0} salta e dá uma mordida em {1}!");
        textos.put("attack_space_ex", "{0} lança um olhar gelado em {1}!");
        textos.put("attack_sweetheart", "{0} atinge {1} com um golpe doce e pegajoso!");
        textos.put("omori_skill", "{0} se concentra e crava a lâmina em {1}: Facada Certeira!");
        textos.put("aubrey_skill", "{0} grita e desce o taco com toda a força em {1}: Tacada Furiosa!");
        textos.put("kel_skill", "{0} arremessa várias bolas seguidas em {1}: Chuva de Bolas!");
        textos.put("hero_skill", "{0} serve um banquete de panquecas quentinhas para todo mundo!");
        textos.put("space_beam", "{0} dispara um Raio Espacial em {1}!");
        textos.put("sweet_scream", "{0} solta um Grito Doce que ecoa em {1}!");
        textos.put("no_focus", "{0} está sem foco!");
        textos.put("no_courage", "{0} está sem coragem!");
        textos.put("no_energy", "{0} está sem energia!");
        textos.put("no_juice", "{0} não pode servir o banquete agora!");
        textos.put("no_charges", "{0} está sem fôlego!");
        textos.put("damage_received", "  {0} sofre {1} de dano.  [{4}] {2}/{3}");
        textos.put("healed", "  {0} recupera {1} de vida.  [{4}] {2}/{3}");
        textos.put("defeated", "  {0} foi derrotado!");
        textos.put("fainted", "  {0} desmaiou!");
        textos.put("revived", "  {0} se levanta, ainda meio tonto.");
        textos.put("rest", "\nOs amigos descansam um pouco antes de seguir em frente...");
        textos.put("battle_won", "\nBatalha vencida!");
        textos.put("victory_game", "\nOs amigos atravessaram o Espaço Branco juntos. Parabéns, fim da aventura!");
        textos.put("defeat", "\nOs amigos não conseguiram seguir adiante. Fim de jogo.");
        textos.put("item_tofu", "Tofu");
        textos.put("item_candy", "Doce");
        textos.put("item_life_jam", "Geleia da Vida");
        textos.put("item_shiny_knife", "Faca Brilhante");
        textos.put("item_baseball_bat", "Taco de Beisebol");
        textos.put("item_comfy_sweater", "Moletom Quentinho");
        textos.put("item_used", "{0} usa {1}.");
        textos.put("equipped", "{0} equipou {1} (+{2} de ataque, +{3} de defesa).");
        textos.put("loot_found", "{0} encontrou: {1}!");
    }
}
