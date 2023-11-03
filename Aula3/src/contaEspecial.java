public class contaEspecial extends contaBancaria{
    private float limite;
    public contaEspecial(float valorLimite){
        this.limite = valorLimite;
    }

    void Saque (float valor) {
        if (valor > 0) {
            if (valor > (this.limite + this.getSaldo())) {
                System.out.println("Seu limite não permite fazer esse saque!\n - Seu limite: R$ " + this.limite + "\n - Seu saldo: R$ " + this.getSaldo());
            } else {
                setSaldo((-valor));
                System.out.println("Saque de R$ " + valor + " realizado com sucesso:");
            }
        } else {
            System.out.println("Saque inválido, verifique o valor e tente novamente.");
        }
        this.Extrato();
    }

    void aumentarLimite(float valor) {
        this.limite += valor;
    }
}
