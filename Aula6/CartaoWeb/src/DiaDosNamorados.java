public class DiaDosNamorados extends Cartao{
    public DiaDosNamorados(String destinatario) {
        super(destinatario);
    }

    public void Mensagem() {
        System.out.println("Desde que te conheci, minha vida virou de cabeça pra baixo.\nAliás, parece até que comecei a viver de novo, de tanta coisa que aprendi do seu lado.\nForam muitas experiências novas, e sinto que cresci demais nesse meio-tempo.\nPor isso, só tenho a agradecer pelas descobertas e torcer por muitas outras, sempre com você.\nTe amo, " + this.destinatario + "! Feliz Dia dos Namorados!");
    }
}
