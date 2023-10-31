public class Aluno extends Pessoa{
    private Double[] notas = new Double[4];
    public Aluno(String nome, int idade, String cpf, double nota1, double nota2, double nota3, double nota4) {
        super(nome,idade,cpf);
        this.notas[0] = nota1;
        this.notas[1] = nota2;
        this.notas[2] = nota3;
        this.notas[3] = nota4;
    }

    public void setNotas(int num_nota, double nota) {
        if (num_nota >= 0 && num_nota < 4) {
            this.notas[num_nota] = nota;
            System.out.println("Nota inserida");
        } else {
            System.out.println("Algo deu errado.");
        }
    }

    public double getMedia() {
        int soma = 0;
        for (int i = 0; i < this.notas.length; i++) {
            soma+= this.notas[i];
        }
        return (double) soma/this.notas.length;
    }
}
