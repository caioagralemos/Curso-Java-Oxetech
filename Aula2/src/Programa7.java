import java.util.Scanner;

public class Programa7 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        float total = 0;
        for (int i = 0; i < 10; i++) {
            total += input.nextFloat();
        }

        float media = total / 10;
        System.out.println("A média dos dez números recebidos é " + media);
    }
}
