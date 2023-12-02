public class Task {
    private final String titulo;
    private final String categoria;
    private final String descricao;
    private final Data prazo;
    private String status;

    public Task(String titulo, String categoria, String descricao, Data prazo) {
        this.status = "A fazer";
        this.prazo = prazo;
        this.categoria = categoria;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public void marcarStatus() {
        if (this.status.equals("A fazer")) {
            this.status = "Feito";
        } else {
            this.status = "A fazer";
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public String isStatus() {
        return status;
    }

    public String getCategoria() {
        return categoria;
    }

    public String toString() {
        return "Tarefa: " + this.titulo + " - " +  this.categoria + "\n" + this.descricao  +
                "\nPrazo: " + this.prazo + "\nStatus: " + this.status;
    }
}
