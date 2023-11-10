public class Aniversario extends Cartao{
    public Aniversario(String destinatario) {
        super(destinatario);
    }

    public void Mensagem() {
        System.out.println("Parabéns, " + this.destinatario + "!\nQue seu aniversário seja uma explosão de alegria, comemorações e abraços apertados.\nQue você tenha um ano incrível, cheio de realizações e felicidade!\nUm abraço!");
    }
}
