public class Natal extends Cartao{
    public Natal(String destinatario) {
        super(destinatario);
    }

    public void Mensagem() {
        System.out.println("O nascimento de Jesus deve sempre ser lembrado como uma celebração da paz, do amor e da entreajuda.\nQue esta época contribua para a nossa evolução pessoal, nos ensinando a viver de uma forma mais harmoniosa e solidária,\nestendendo a mão para aqueles que mais precisam e perpetuando as graças de Deus por todos aqueles que surgirem no nosso caminho.\nFeliz Natal, " + this.destinatario + "!");
    }
}
