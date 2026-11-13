package rpg.presentation;

/** Única classe que conhece o System.out (SRP). */
public class SaidaConsole implements Saida {

    @Override
    public void exibir(String mensagem) {
        System.out.println(mensagem);
    }
}
