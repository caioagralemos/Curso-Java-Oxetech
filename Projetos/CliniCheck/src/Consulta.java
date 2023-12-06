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
}
