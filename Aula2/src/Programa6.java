import java.util.Scanner;

public class Programa6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = 1;
        int contador = 0;

        while (num != -1) {
            num = input.nextInt();
            if (num != -1) {
                contador++;
            }
        }

        System.out.println("Foram recebidos " + contador + " números.");
    }
}
