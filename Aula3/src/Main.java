public class Main {
    public static void main(String[] args) {
        Cliente caio = new Cliente("Caio Agra Lemos", "14171458463");
        Cliente zeca_pg = new Cliente("Zeca Pau Gordinho", "192391231");

        caio.adicionarContaEspecial(1000);

        caio.contas[0].Deposito(100);
        caio.contas[0].Saque(95);
        caio.contas[0].Extrato();

        caio.contas[0].Saque(1000);
        caio.contas[0].Extrato();

        caio.contas[0].Saque(6);
    }
}
