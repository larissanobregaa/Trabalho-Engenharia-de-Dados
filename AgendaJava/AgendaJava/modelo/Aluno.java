package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Aluno {

    private int matricula;
    private String nome;
    private String endereco;
    private LocalDate dataNascimento;
    private String numeroTelefone;
    private String email;
    private LocalDate dataInscricao;
    private boolean historicoPagamento;
    private boolean statusAssociacao;
    private ArrayList<Acesso> acessos;

    
    public Aluno(int matricula, String nome, String endereco, LocalDate dataNascimento, String numeroTelefone, 
            String email, LocalDate dataInscricao, boolean historicoPagamento, boolean statusAssociacao, ArrayList<Acesso> acessos) {
        this.matricula = matricula;
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.numeroTelefone = numeroTelefone;
        this.email = email;
        this.dataInscricao = dataInscricao;
        this.historicoPagamento = historicoPagamento;
        this.statusAssociacao = statusAssociacao;
        this.acessos = acessos;
    }

    public Aluno(String nome, String endereco, LocalDate dataNascimento, String numeroTelefone, 
            String email, LocalDate dataInscricao, boolean historicoPagamento, boolean statusAssociacao, ArrayList<Acesso> acessos) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.numeroTelefone = numeroTelefone;
        this.email = email;
        this.dataInscricao = dataInscricao;
        this.historicoPagamento = historicoPagamento;
        this.statusAssociacao = statusAssociacao;
        this.acessos = acessos;
    }

    public Aluno(int matricula, String nome, String endereco, LocalDate dataNascimento, String numeroTelefone, String email, LocalDate dataInscricao, boolean historicoPagamento, boolean statusAssociacao) {
        this.matricula = matricula;
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.numeroTelefone = numeroTelefone;
        this.email = email;
        this.dataInscricao = dataInscricao;
        this.historicoPagamento = historicoPagamento;
        this.statusAssociacao = statusAssociacao;
        this.acessos = new ArrayList<Acesso>();
    }

    public Aluno(String nome, String endereco, LocalDate dataNascimento, String numeroTelefone, String email, LocalDate dataInscricao, boolean historicoPagamento, boolean statusAssociacao) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.numeroTelefone = numeroTelefone;
        this.email = email;
        this.dataInscricao = dataInscricao;
        this.historicoPagamento = historicoPagamento;
        this.statusAssociacao = statusAssociacao;
        this.acessos = new ArrayList<Acesso>();
    }

    public Aluno(String nome, String numeroTelefone, LocalDate dataNascimento){
        this.nome = nome;
        this.numeroTelefone = numeroTelefone;
        this.dataNascimento = dataNascimento;
        this.dataInscricao = LocalDate.now();
        this.acessos = new ArrayList<Acesso>();
    }

    public Aluno(int matricula){
        this.matricula = matricula;
        this.acessos = new ArrayList<Acesso>();
    }

   public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDate dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public boolean isHistoricoPagamento() {
        return historicoPagamento;
    }

    public void setHistoricoPagamento(boolean historicoPagamento) {
        this.historicoPagamento = historicoPagamento;
    }

    public boolean isStatusAssociacao() {
        return statusAssociacao;
    }

    public void setStatusAssociacao(boolean statusAssociacao) {
        this.statusAssociacao = statusAssociacao;
    }

    public ArrayList<Acesso> getAcessos() {
        return acessos;
    }

    public void setAcessos(ArrayList<Acesso> acessos) {
        this.acessos = acessos;
    }

    public void addAcesso(Acesso acesso) {
        this.acessos.add(acesso);
    }

    public void removeAcesso(Acesso acesso) {
        this.acessos.remove(acesso);
    }


    @Override
    public String toString() {
        return "{'aluno':{'matricula': " + this.matricula + ", 'nome': '" + this.nome + "', 'numeroTelefone': '" + this.numeroTelefone + "'}}";
    }
}