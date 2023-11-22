package modelo;

import java.time.LocalDateTime;

public class Acesso {
    private int idAcesso;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private boolean statusPagamento;
 
    public Acesso(int idAcesso, LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSaida, boolean statusPagamento){
        this.idAcesso = idAcesso;
        this.dataHoraEntrada = dataHoraEntrada;
        this.dataHoraSaida = dataHoraSaida;
        this.statusPagamento = statusPagamento;
    }
    
   
    public Acesso(LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSaida, boolean statusPagamento){
        this.dataHoraEntrada = dataHoraEntrada;
        this.dataHoraSaida = dataHoraSaida;
        this.statusPagamento = statusPagamento;
        
    } 

    public Acesso(boolean statusPagamento){
        this.dataHoraEntrada = LocalDateTime.now();
        this.statusPagamento = statusPagamento;
        
    } 
    

     public int getIdAcesso() {
        return idAcesso;
    }
    public void setIdAcesso(int idAcesso) {
        this.idAcesso = idAcesso;
    }
    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }
    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }
    public LocalDateTime getDataHoraSaida() {
        return dataHoraSaida;
    }
    public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }
    public boolean isStatusPagamento() {
        return statusPagamento;
    }
    public void setStatusPagamento(boolean statusPagamento) {
        this.statusPagamento = statusPagamento;
    }


    
    @Override
    public String toString() {
        return "{'acesso':{'id': " + this.idAcesso + ",'entrada': '" + this.dataHoraEntrada + "', 'saida': '" + this.dataHoraSaida + "'}}";
    }
}
