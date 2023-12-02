import java.time.LocalDate;
import java.util.Date;

public class Task {
    private String titulo;
    private String categoria;
    private String descricao;
    private Data prazo;
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

    public String toString() {
        return "Tarefa: " + this.titulo + " - " +  this.categoria + "\n" + this.descricao  +
                "\nPrazo: " + this.prazo + "\nStatus: " + this.status;
    }
}
