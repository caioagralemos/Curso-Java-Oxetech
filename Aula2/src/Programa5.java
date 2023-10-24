import java.util.Scanner;

public class Programa5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        String dia = "";

        switch (num) {
            case 0:
                dia = "m domingo";
                break;
            case 1:
                dia = "a segunda";
                break;
            case 2:
                dia = "a terça";
                break;
            case 3:
                dia = "a quarta";
                break;
            case 4:
                dia = "a quinta";
                break;
            case 5:
                dia = "a sexta";
                break;
            case 6:
                dia = "m sábado";
                break;
            default:
                dia = "m dia";
                break;
        }

        System.out.printf("Bo" + dia);
    }
}
