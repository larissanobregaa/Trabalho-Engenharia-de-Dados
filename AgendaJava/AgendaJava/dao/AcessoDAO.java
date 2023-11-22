package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import modelo.Aluno;
import modelo.Instrutor;
import modelo.Acesso;

public class AcessoDAO {

    private Connection connection;

    public AcessoDAO(Connection connection) {
        this.connection = connection;
    }


    public void create(Acesso acesso, Aluno aluno) {
        try {
            String sql = "INSERT INTO acesso (dataHoraEntrada, statusPagamento, fk_aluno_matricula) VALUES (?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, acesso.getDataHoraEntrada());
                pstm.setBoolean(2, aluno.isHistoricoPagamento());
                pstm.setInt(3, aluno.getMatricula());
                
                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        acesso.setIdAcesso(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createHistorico(Acesso acesso, Aluno aluno) {
        try {
            String sql = "INSERT INTO acesso (dataHoraEntrada, dataHoraSaida, statusPagamento, fk_aluno_matricula) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, acesso.getDataHoraEntrada());
                pstm.setObject(2, acesso.getDataHoraSaida());
                pstm.setBoolean(3, aluno.isHistoricoPagamento());
                pstm.setInt(4, aluno.getMatricula());
                
                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        acesso.setIdAcesso(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fecharAcessoAluno(Aluno aluno){
        fecharAcessoMatricula(aluno.getMatricula());
    }

    public void fecharAcessoMatricula(int matricula){
        try{
            String sql = "UPDATE acesso SET dataHoraSaida = ? WHERE dataHoraSaida is null and fk_aluno_matricula = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, LocalDateTime.now());
                pstm.setInt(2, matricula);

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fecharTodosAcessos(){
        try{
            String sql = "UPDATE acesso SET dataHoraSaida = ? WHERE dataHoraSaida is null";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, LocalDateTime.now());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

     public ArrayList<Acesso> retriveAllAcesso(){

        ArrayList<Acesso> acessos = new ArrayList<Acesso>();
		try {
			String sql = "SELECT IDacesso, dataHoraEntrada, dataHoraSaida, statusPagamento FROM acesso";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()){
                    int IDacesso = rst.getInt("IDacesso");
                    LocalDateTime dataHoraEntrada = rst.getObject("dataHoraEntrada", LocalDateTime.class);
                    LocalDateTime dataHoraSaida = rst.getObject("dataHoraSaida", LocalDateTime.class);
                    Boolean statusPagamento = rst.getBoolean("statusPagamento");
                    Acesso acesso = new Acesso(IDacesso, dataHoraEntrada, dataHoraSaida, statusPagamento);
                    acessos.add(acesso);
                }
			}
            return acessos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

    public ArrayList<Acesso> retriveAllAcessoAluno(int matricula){

        ArrayList<Acesso> acessos = new ArrayList<Acesso>();
		try {
			String sql = "SELECT IDacesso, dataHoraEntrada, dataHoraSaida, statusPagamento FROM acesso WHERE fk_Aluno_matricula = ? ";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				
                pstm.setInt(1, matricula);
                
                pstm.execute();
                
                ResultSet rst = pstm.getResultSet();
                while (rst.next()){
                    int IDacesso = rst.getInt("IDacesso");
                    LocalDateTime dataHoraEntrada = rst.getObject("dataHoraEntrada", LocalDateTime.class);
                    LocalDateTime dataHoraSaida = rst.getObject("dataHoraSaida", LocalDateTime.class);
                    Boolean statusPagamento = rst.getBoolean("statusPagamento");
                    Acesso acesso = new Acesso(IDacesso, dataHoraEntrada, dataHoraSaida, statusPagamento);
                    acessos.add(acesso);
                }
			}
            return acessos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }
    
}
