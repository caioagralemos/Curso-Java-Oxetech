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

        // escopo de decisão com switch
        int x = 0;
        switch (x) {
            case 0:
                System.out.println("Falso");
                break;
            case 1:
                System.out.println("Verdadeiro");
                break;
            default:
                System.out.println("Valor inválido");
                break;
        }

        // comandos de repetição em java
        int contador = 0;
        while (contador <= 5) {
            System.out.println(contador);
            contador++;
        }

        do {
            System.out.println(contador);
            contador++;
        } while (contador <= 5);

        for (int i = 0; i < 10; i++){
            System.out.println(i);
        }
    }
}