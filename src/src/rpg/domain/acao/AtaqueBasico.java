package rpg.domain.acao;

import rpg.domain.contract.Destrutivel;
import rpg.domain.event.Notificador;
import rpg.domain.model.Personagem;

/** Ataque comum: sempre disponível para qualquer Personagem. */
public class AtaqueBasico implements Acao {

    private final Personagem dono;

    public AtaqueBasico(Personagem dono) {
        this.dono = dono;
    }

    @Override
    public String getChaveRotulo() {
        return "action_attack";
    }

    @Override
    public boolean estaDisponivel() {
        return true;
    }

    @Override
    public void executar(Destrutivel alvo, Notificador notificador) {
        dono.atacar(alvo, notificador);
    }
}
