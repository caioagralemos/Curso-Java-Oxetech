public class Consulta {
    private Medico medico;
    private Paciente paciente;
    private String especialidade;
    private Data data;
    public Consulta(Medico medico, Paciente paciente, String especialidade, Data data) {
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

    public String toString() {
        return this.paciente.getNome().toUpperCase() + " TRATANDO " +  this.especialidade.toUpperCase() + "\nDr(a). " + this.medico.getNome() +
                "\nData: " + this.data;
    }
}
