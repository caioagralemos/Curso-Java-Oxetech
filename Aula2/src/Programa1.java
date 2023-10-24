import java.util.Scanner;

public class Programa1 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String nome = entrada.nextLine();
        if (nome.equals("Caio")) {
            System.out.println("Olá eu!");
        } else {
            System.out.println("Olá, " + nome);
        }
    }
}
