import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Java GUI");
        JFrame frame2 = new JFrame("Seus Dados");

        JPanel Nome = new JPanel();
        Nome.add(new JLabel("Nome"));
        JTextField inputNome = new JTextField(15);
        Nome.add(inputNome);

        JPanel CPF = new JPanel();
        CPF.add(new JLabel("CPF"));
        JTextField inputCPF = new JTextField(15);
        CPF.add(inputCPF);

        JPanel Idade = new JPanel();
        Idade.add(new JLabel("Idade"));
        JTextField inputIdade = new JTextField(15);
        Idade.add(inputIdade);

        JPanel Email = new JPanel();
        Email.add(new JLabel("Email"));
        JTextField inputEmail = new JTextField(15);
        Email.add(inputEmail);

        JButton botao = new JButton("Enviar");

        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame2.setSize(350, 300);
                JLabel lbl = new JLabel("<html> Nome: " + inputNome.getText() + "<br><br>" +
                        "CPF: " + inputCPF.getText() + "<br><br>" +
                        "Email: " + inputEmail.getText() + "<br><br>" +
                        "Idade: " + inputIdade.getText() + "</html>");
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                frame2.add(lbl);
                frame2.setVisible(true);
            }
        });

        frame.setLayout(new FlowLayout());
        frame.setSize(350, 300);
        frame.add(Nome);
        frame.add(CPF);
        frame.add(Email);
        frame.add(Idade);
        frame.add(botao);

        frame.setVisible(true);
    }
}