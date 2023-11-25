import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame1 = new JFrame("Calculadora de Média");
        JFrame frame2 = new JFrame("Sua Média");

        JPanel Primeiro_num = new JPanel();
        Primeiro_num.add(new JLabel("Seu primeiro número: "));
        JTextField field_n1 = new JTextField(15);
        Primeiro_num.add(field_n1);

        JPanel Segundo_num = new JPanel();
        Segundo_num.add(new JLabel("Seu segundo número: "));
        JTextField field_n2 = new JTextField(15);
        Segundo_num.add(field_n2);

        JButton botao = new JButton("Enviar");

        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        frame1.remove(new JLabel("Algo deu errado :/"));
                        frame1.revalidate();
                        float n1 = Float.parseFloat(field_n1.getText());
                        float n2 = Float.parseFloat(field_n2.getText());
                        frame1.setVisible(false);
                        frame2.setLayout(new FlowLayout());
                        frame2.setSize(350, 300);
                        float resultado = (n1 + n2) / 2;
                        JLabel lbl = new JLabel("Seu resultado é: " + resultado);
                        lbl.setHorizontalAlignment(SwingConstants.CENTER);
                        frame2.add(lbl);
                        frame2.setVisible(true);
                        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    } catch (Exception error) {
                        frame1.add(new JLabel("Algo deu errado :/"));
                    }
            }
        });

        frame1.setLayout(new FlowLayout());
        frame1.setSize(350, 300);
        frame1.add(Primeiro_num);
        frame1.add(Segundo_num);
        frame1.add(botao);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }
}