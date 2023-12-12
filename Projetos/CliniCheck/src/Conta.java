import java.util.Scanner;

public class Conta {
    String user;
    String password;
    int contador_consultas;

    public Conta(String user, String password) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            sb.append((char)(c + 5));
        }
        this.password = sb.toString();
        this.user = user.toLowerCase();
        this.contador_consultas = 0;
    }

    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a senha de sua conta: ");
        String password = scanner.nextLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            sb.append((char)(c + 5));
        }
        password = sb.toString();
        return password.equals(this.password);
    }

    public void setContador() {
        this.contador_consultas++;
    }
}
