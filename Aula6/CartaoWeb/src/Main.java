public class Main {
    public static void main(String[] args) {
        Cartao[] cartoes = new Cartao[3];

        cartoes[0] = new Natal("Angélica");
        cartoes[1] = new DiaDosNamorados("Bu");
        cartoes[2] = new Aniversario("Moacir");

        for (int i = 0; i < cartoes.length; i++) {
            cartoes[i].Mensagem();
        }
    }
}