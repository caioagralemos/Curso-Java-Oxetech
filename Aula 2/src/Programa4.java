import java.util.Scanner;

public class Programa4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num1 = input.nextInt();
        int num2 = input.nextInt();
        int num3 = input.nextInt();

        int media = (num1 + num2 + num3) / 3;

        if (media >= 7) {
            System.out.println("Sua média é maior ou igual que sete!\nMédia: " + media);
        } else {
            System.out.println("Sua média é menor que sete!\nMédia: " + media);
        }
    }
}
