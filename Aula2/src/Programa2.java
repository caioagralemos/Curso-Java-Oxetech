import java.util.Scanner;

public class Programa2 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int num1 = entrada.nextInt();
        int num2 = entrada.nextInt();

        System.out.println("Soma: " + (num1+num2));
        System.out.println("Produto: " + (num1*num2));
        System.out.println("Diferença: " + (num1-num2));
        System.out.println("Média: " + ((num1+num2)/2));
        System.out.println("Razão: " + (num1/num2));
        System.out.println("Resto: " + (num1%num2));
    }
}
