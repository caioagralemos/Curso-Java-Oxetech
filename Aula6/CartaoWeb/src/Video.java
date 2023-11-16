public class Video implements Arquivo{
    double size = 12.3;
    public void tamanho() {
        System.out.println("O tamanho do arquivo é de " + this.size + " MBs.");
    }
}
