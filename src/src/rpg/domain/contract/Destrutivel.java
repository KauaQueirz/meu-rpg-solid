package rpg.domain.contract;

import rpg.domain.event.Notificador;

/**
 * Tudo aquilo que pode sofrer dano e ser destruído: personagens, baús, fortalezas...
 *
 * DIP: quem ataca depende desta abstração, e não de uma classe concreta.
 * ISP: contrato pequeno, só com o que um alvo precisa oferecer.
 */
public interface Destrutivel {

    void receberDano(int valor, Notificador notificador);

    boolean estaDestruido();

    String getNome();
}
