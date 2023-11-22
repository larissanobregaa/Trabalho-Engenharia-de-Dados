package modelo;

import java.util.ArrayList;

public class Turma{

    private int IDTurma;
    private String tipoTurma;
    private int horario;
    private int qtdAlunos;
    private Instrutor instrutor;
    private ArrayList<Aluno> alunos;


    public Turma(int IDTurma, String tipoTurma, int horario, int qtdAlunos, Instrutor instrutor, ArrayList<Aluno> alunos) {
        this.IDTurma = IDTurma;
        this.tipoTurma = tipoTurma;
        this.horario = horario;
        this.qtdAlunos = qtdAlunos;
        this.instrutor = instrutor;
        this.alunos = alunos;
    }

    public Turma(int IDTurma, String tipoTurma, int horario, int qtdAlunos) {
        this.IDTurma = IDTurma;
        this.tipoTurma = tipoTurma;
        this.horario = horario;
        this.qtdAlunos = qtdAlunos;
        this.alunos = new ArrayList<Aluno>();
    }

    public Turma(String tipoTurma, int horario, int qtdAlunos) {
        this.tipoTurma = tipoTurma;
        this.horario = horario;
        this.qtdAlunos = qtdAlunos;
        this.alunos = new ArrayList<Aluno>();
    }

    public int getIDTurma() {
        return IDTurma;
    }

    public void setIDTurma(int iDTurma) {
        IDTurma = iDTurma;
    }

    public String getTipoTurma() {
        return tipoTurma;
    }

    public void setTipoTurma(String tipoTurma) {
        this.tipoTurma = tipoTurma;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    public int getQtdAlunos() {
        return qtdAlunos;
    }

    public void setQtdAlunos(int qtdAlunos) {
        this.qtdAlunos = qtdAlunos;
    }
    
    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }

    
    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void addAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public void removeAluno(Aluno aluno) {
        this.alunos.remove(aluno);
    }

    @Override
    public String toString() {
        return "{'turma':{'id': "+this.IDTurma+", 'tipoTurma': '"+this.tipoTurma+"', 'horario': '+"+this.horario + "'}}";
    }
}