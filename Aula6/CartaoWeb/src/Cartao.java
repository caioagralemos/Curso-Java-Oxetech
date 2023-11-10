public abstract class Cartao {
    String destinatario;
    abstract void Mensagem();
    public Cartao(String destinatario) {
        this.destinatario = destinatario;
    }
}
