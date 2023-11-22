package modelo;

import java.time.LocalDateTime;

public class Aula {
    int idAula;
    Aluno aluno;
    Instrutor instrutor;
    LocalDateTime horario;



    public Aula(int idAula, Aluno aluno, Instrutor instrutor, LocalDateTime horario){
        this.idAula = idAula;
        this.aluno = aluno;
        this.instrutor = instrutor;
        this.horario = horario;
    }

    public Aula(Aluno aluno, Instrutor instrutor, LocalDateTime horario){
        this.aluno = aluno;
        this.instrutor = instrutor;
        this.horario = horario;
    }
 
    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }


    @Override
    public String toString() {
        return "{'aula':{'id': " + this.idAula + ", 'matricula aluno': '" + this.aluno.getMatricula() + 
        "', 'id instrutor': '" + this.instrutor.getIDinstrutor() + "'}}";
    }
}
