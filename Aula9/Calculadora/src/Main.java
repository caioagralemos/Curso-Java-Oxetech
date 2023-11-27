import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    static class Calculator {
        public static String num = "0";
        public static int memoria = 0;
        public static String operacao = "+";
        public static String ButtonPressed(JButton botao) {
            if (num.equals("0")) {
                num = botao.getText();
            } else {
                num = num + botao.getText();
            }

            return num;
        }

        public static String reset() {
            num = "0";
            return num;
        }
    }

    public static void main(String[] args) {
        JFrame calculadora = new JFrame("Calculadora");

        JPanel visor = new JPanel();
        visor.add(new JLabel(Calculator.num));
        visor.setSize(100, 290);
        visor.setBackground(Color.gray);
        calculadora.add(visor);


        JButton botao_c = new JButton("(C)");
        botao_c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calculator.reset();
                visor.removeAll();
                visor.add(new JLabel(Calculator.num));
                calculadora.revalidate();
            }
        });

        JButton botao_igual = new JButton("(=)");
        JButton botao_adicao = new JButton("(+)");
        JButton botao_subtracao = new JButton("(-)");
        JButton botao_multiplicacao = new JButton("(x)");
        JButton botao_divisao = new JButton("(/)");

        JButton[] botoes = new JButton[10];
        botoes[0] = new JButton("0");
        botoes[1] = new JButton("1");
        botoes[2] = new JButton("2");
        botoes[3] = new JButton("3");
        botoes[4] = new JButton("4");
        botoes[5] = new JButton("5");
        botoes[6] = new JButton("6");
        botoes[7] = new JButton("7");
        botoes[8] = new JButton("8");
        botoes[9] = new JButton("9");

        calculadora.add(botao_adicao);
        calculadora.add(botao_subtracao);
        calculadora.add(botao_multiplicacao);
        calculadora.add(new JButton());
        calculadora.add(botao_divisao);
        calculadora.add(new JButton());

        for (JButton botao : botoes) {
            botao.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Calculator.num = Calculator.ButtonPressed(botao);
                    visor.removeAll();
                    visor.add(new JLabel(Calculator.num));
                    calculadora.revalidate();
            }
            });
            if (botao.getText().equals("9")) {
                calculadora.add(botao_c);
                calculadora.add(botao);
                calculadora.add(botao_igual);
            } else {
                calculadora.add(botao);
            }
        }

        calculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculadora.setSize(300, 500);
        calculadora.setLayout(new FlowLayout());
        calculadora.setVisible(true);
    }
}