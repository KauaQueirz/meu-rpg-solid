package rpg.domain.model;

/**
 * Inimigo simples. OCP: um novo tipo de inimigo é uma nova classe;
 * nada do que já existe precisou ser alterado.
 */
public class Goblin extends Inimigo {

    public Goblin(String nome) {
        super(nome, 25, 6, 1);
    }
}
