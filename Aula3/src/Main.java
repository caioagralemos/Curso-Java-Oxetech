public class Main {
    public static void main(String[] args) {
        Cliente caio = new Cliente("Caio Agra Lemos", "14171458463");
        Cliente zeca_pg = new Cliente("Zeca Pau Gordinho", "192391231");

        caio.conta.Extrato();
        caio.conta.Deposito(1230.91299F);
        caio.conta.Saque(1232);
    }
}
