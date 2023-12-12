public class Consulta {
    private int id;
    private Medico medico;
    private Paciente paciente;
    private String especialidade;
    private Data data;
    public Consulta(int id, Medico medico, Paciente paciente, String especialidade, Data data) {
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.especialidade = especialidade;
        this.data = data;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public int getId() {
        return id;
    }

    public Data getData() {
        return data;
    }

    public String toString() {
        return "CONSULTA DE ID " + this.id + " - " + this.paciente.getNome().toUpperCase() + " TRATANDO " +  this.especialidade.toUpperCase() + " com Dr(a). " + this.medico.getNome() +
                " na data " + this.data;
    }
}
