public class Contato {
    private int id;
    private String nome;
    private String numero;
    private String email;

    public Contato(int id, String nome, String numero, String email) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.email = email;
    }

    public Contato(int id, String nome, String numero) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.email = null;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNumero() {
        return numero;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
