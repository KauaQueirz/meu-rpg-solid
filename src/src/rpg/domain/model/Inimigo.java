package rpg.domain.model;

/**
 * Adversário comum: é um Personagem como qualquer outro (LSP).
 * Não tem habilidade especial e, por isso, não implementa HabilidadeEspecial (ISP).
 */
public class Inimigo extends Personagem {

    public Inimigo(String nome, int vidaMax, int ataque, int defesa) {
        super(nome, vidaMax, ataque, defesa);
    }
}
