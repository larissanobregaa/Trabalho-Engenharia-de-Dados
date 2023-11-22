package modelo;

import java.time.LocalDate;

public class Instrutor{

    private int IDinstrutor;
    private String nome;
    private LocalDate dataNascimento;
    private String numeroTelefone;
    private String especializacao;


    public Instrutor(int IDinstrutor, String nome, LocalDate dataNascimento, String numeroTelefone, String especializacao) {
        this.IDinstrutor = IDinstrutor;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.numeroTelefone = numeroTelefone;
        this.especializacao = especializacao;
    }

    public Instrutor(String nome, LocalDate dataNascimento, String numeroTelefone, String especializacao) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.numeroTelefone = numeroTelefone;
        this.especializacao = especializacao;
    }

 public int getIDinstrutor() {
        return IDinstrutor;
    }

    public void setIDinstrutor(int iDinstrutor) {
        IDinstrutor = iDinstrutor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }


    @Override
    public String toString() {
        return "{'instrutor':{'id': "+this.IDinstrutor+", 'nome': '"+this.nome+"', 'numeroTelefone': '+"+this.numeroTelefone + "}}";
    }
}