public abstract class Cartao implements Arquivo{
    String destinatario;
    abstract void Mensagem();
    double size = 1;

    public void tamanho() {
        System.out.println("O tamanho do arquivo é de " + this.size + " MBs.");
    }

    public Cartao(String destinatario) {
        this.destinatario = destinatario;
    }
}
