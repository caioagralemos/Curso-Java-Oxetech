import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    static class Calculator {
        public static String num = "0";
        public static String memoria = "0";
        public static String operacao = "";
        public static String ButtonPressed(JButton botao) {
            if (num.contains("Resultado")) {
                memoria = "0";
                operacao = "";
                num = botao.getText();
            } else if (num.equals("0")) {
                num = botao.getText();
            } else {
                num = num + botao.getText();
            }

            return num;
        }

        public static String calcular() {
            if (operacao.equals("+")) {
                num = "Resultado: " + (Integer.parseInt(num) + Integer.parseInt(memoria));
            } else if (operacao.equals("-")) {
                num = "Resultado: " + (Integer.parseInt(memoria) - Integer.parseInt(num));
            } else if (operacao.equals("x")) {
                num = "Resultado: " + (Integer.parseInt(num) * Integer.parseInt(memoria));
            } else {
                num = "Resultado: " + (Float.parseFloat(memoria) / Float.parseFloat(num));
            }
            return num;
        }

        public static String reset() {
            num = "0";
            memoria = "0";
            operacao = "";
            return num;
        }
    }

    public static void main(String[] args) {
        JFrame calculadora = new JFrame("Calculadora");
        calculadora.setLayout(new BorderLayout());
        calculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculadora.setSize(300, 500);

        JPanel visor = new JPanel();
        visor.add(new JLabel(Calculator.num));
        visor.setSize(100, 50); // Altura fixa
        visor.setBackground(Color.gray);
        calculadora.add(visor, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(4,4));

        JButton botao_c = new JButton("(C)");
        botao_c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calculator.reset();
                visor.removeAll();
                visor.add(new JLabel(Calculator.num), BorderLayout.NORTH);
                visor.revalidate();
            }
        });

        JButton botao_igual = new JButton("(=)");
        botao_igual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Calculator.operacao.isEmpty()) {
                    Calculator.calcular();
                    visor.removeAll();
                    visor.add(new JLabel(Calculator.num), BorderLayout.NORTH);
                    visor.revalidate();
                }
            }
        });

        JButton botao_adicao = new JButton("(+)");
        botao_adicao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Calculator.operacao.isEmpty()) {
                    Calculator.operacao = "+";
                    Calculator.memoria = Calculator.num;
                    Calculator.num = "0";
                    visor.removeAll();
                    visor.add(new JLabel(Calculator.num), BorderLayout.NORTH);
                    visor.revalidate();
                } else {
                    Calculator.calcular();
                    visor.revalidate();
                }
            }
        });

        JButton botao_subtracao = new JButton("(-)");
        botao_subtracao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Calculator.operacao.isEmpty()) {
                    Calculator.operacao = "-";
                    Calculator.memoria = Calculator.num;
                    Calculator.num = "0";
                    visor.removeAll();
                    visor.add(new JLabel(Calculator.num), BorderLayout.NORTH);
                    visor.revalidate();
                } else {
                    Calculator.calcular();
                    visor.revalidate();
                }
            }
        });

        JButton botao_multiplicacao = new JButton("(x)");
        botao_multiplicacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Calculator.operacao.isEmpty()) {
                    Calculator.operacao = "x";
                    Calculator.memoria = Calculator.num;
                    Calculator.num = "0";
                    visor.removeAll();
                    visor.add(new JLabel(Calculator.num), BorderLayout.NORTH);
                    visor.revalidate();
                } else {
                    Calculator.calcular();
                    visor.revalidate();
                }
            }
        });

        JButton botao_divisao = new JButton("(/)");
        botao_divisao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Calculator.operacao.isEmpty()) {
                    Calculator.operacao = "/";
                    Calculator.memoria = Calculator.num;
                    Calculator.num = "0";
                    visor.removeAll();
                    visor.add(new JLabel(Calculator.num), BorderLayout.NORTH);
                    visor.revalidate();
                } else {
                    Calculator.calcular();
                    visor.revalidate();
                }
            }
        });

        JButton[] botoes = new JButton[10];
        botoes[0] = new JButton("1");
        botoes[1] = new JButton("2");
        botoes[2] = new JButton("3");
        botoes[3] = new JButton("4");
        botoes[4] = new JButton("5");
        botoes[5] = new JButton("6");
        botoes[6] = new JButton("7");
        botoes[7] = new JButton("8");
        botoes[8] = new JButton("9");
        botoes[9] = new JButton("0");

        grid.add(botao_adicao);
        grid.add(botao_subtracao);
        grid.add(botao_multiplicacao);
        grid.add(botao_divisao);

        for (JButton botao : botoes) {
            botao.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Calculator.num = Calculator.ButtonPressed(botao);
                    visor.removeAll();
                    visor.add(new JLabel(Calculator.num), BorderLayout.NORTH);
                    visor.revalidate();
            }
            });
            if (botao.getText().equals("9")) {
                grid.add(botao_c);
                grid.add(botao);
            } else if (botao.getText().equals("0")) {
                grid.add(botao);
                grid.add(botao_igual);
            } else {
                grid.add(botao);
            }
        }

        calculadora.add(grid, BorderLayout.CENTER);
        calculadora.setVisible(true);
    }
}