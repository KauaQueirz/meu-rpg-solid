package rpg.domain.acao;

import rpg.domain.contract.Destrutivel;
import rpg.domain.contract.HabilidadeEspecial;
import rpg.domain.event.Notificador;

/**
 * Adapta qualquer HabilidadeEspecial para o menu de ações.
 * DIP/ISP: depende apenas da interface HabilidadeEspecial, e não de Mago, Guerreiro etc.
 */
public class UsarHabilidade implements Acao {

    private final HabilidadeEspecial dono;

    public UsarHabilidade(HabilidadeEspecial dono) {
        this.dono = dono;
    }

    @Override
    public String getRotulo(Notificador notificador) {
        return notificador.traduzir(dono.getChaveHabilidade());
    }

    @Override
    public boolean estaDisponivel() {
        return dono.podeUsarHabilidade();
    }

    @Override
    public void executar(Destrutivel alvo, Notificador notificador) {
        dono.usarHabilidade(alvo, notificador);
    }
}
