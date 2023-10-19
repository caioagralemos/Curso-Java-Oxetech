import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // comentário - sintaxe
        //System.out.println("Linha 1\nLinha 2");
        // System.out.println(7 + 4);
        Scanner sc = new Scanner(System.in);
        int numero = sc.nextInt();
        numero+=numero;
        System.out.println(numero);
        if (numero > 100) {
            System.out.println("Seu número vezes 2 é maior que 100.");
        } else {
            System.out.println("Seu número vezes 2 não é maior que 100.");
        }
    }
}