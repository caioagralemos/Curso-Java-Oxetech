public class contaBancaria {
    static int qtdContas = 0;
    private final int num_conta;
    private float saldo;

    public contaBancaria() {
        qtdContas++;
        this.num_conta = qtdContas;
        this.saldo = 0;
    }

    void Extrato() {
        System.out.println("Seu saldo atual é: R$ " + this.saldo + "\n");;
    }

    void Saque (float valor) {
        if (valor > 0) {
            if (valor > this.saldo) {
                System.out.println("Você não tem saldo suficiente!");
            } else {
                this.saldo -= valor;
                System.out.println("Saque de R$ " + valor + " realizado com sucesso:");
            }
        } else {
            System.out.println("Saque inválido, verifique o valor e tente novamente.");
        }
        this.Extrato();
    }

    void Deposito (float valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito de R$ " + valor + " realizado com sucesso:");
        } else {
            System.out.println("Depósito inválido, verifique o valor e tente novamente.");
        }
        this.Extrato();
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float valor) {
        this.saldo += valor;
    }
}
