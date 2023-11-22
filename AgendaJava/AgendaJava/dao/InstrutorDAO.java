package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;

import java.util.ArrayList;

import modelo.Aluno;
import modelo.Instrutor;

public class InstrutorDAO {
    private Connection connection;

    public InstrutorDAO(Connection connection) {
        this.connection = connection;
    }

    public void createBasico(Instrutor instrutor) {
        try {
            String sql = "INSERT INTO instrutor (nome, dataNascimento, numeroTelefone, especializacao) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, instrutor.getNome());
                pstm.setObject(2, instrutor.getDataNascimento());
                pstm.setString(3, instrutor.getNumeroTelefone());
                pstm.setString(4, instrutor.getEspecializacao());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        instrutor.setIDinstrutor(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    public void deletaInstrutor(int id){
        try{
            String sql = "DELETE FROM instrutor WHERE IDInstrutor = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, id);

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    public void deletaInstrutor(Instrutor instrutor){
        try{
            String sql = "DELETE FROM instrutor WHERE IDInstrutor = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, instrutor.getIDinstrutor());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    public ArrayList<Instrutor> retriveAllInstrutor(){

        ArrayList<Instrutor> instrutores = new ArrayList<Instrutor>();
		try {
			String sql = "SELECT IDInstrutor, nome, dataNascimento, numeroTelefone, especializacao FROM instrutor";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()){
                    int IDInstrutor = rst.getInt("IDInstrutor");
                    String nome = rst.getString("nome");
                    LocalDate dataNascimento = rst.getObject("dataNascimento", LocalDate.class);
                    String numeroTelefone = rst.getString("numeroTelefone");
                    String especializacao = rst.getString("especializacao");
                    Instrutor instrutor = new Instrutor(IDInstrutor, nome, dataNascimento, numeroTelefone , especializacao);
                    instrutores.add(instrutor);
                }
			}
            return instrutores;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

    public Instrutor retriveInstrutor(int iDInstrutor){

        try{
            String sql = "SELECT IDInstrutor, nome, dataNascimento, numeroTelefone, especializacao FROM instrutor "
            + "WHERE IDInstrutor = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setInt(1, iDInstrutor);
                
                pstm.execute();

                ResultSet rst = pstm.getResultSet();

                rst.next();
                 
                String nome = rst.getString("nome");
                LocalDate dataNascimento = rst.getObject("dataNascimento", LocalDate.class);
                String numeroTelefone = rst.getString("numeroTelefone");
                String especializacao = rst.getString("especializacao");
                
                Instrutor instrutor = new Instrutor(nome, dataNascimento, numeroTelefone, especializacao);
                return instrutor;
            }
            
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

    public void updateNomeInstrutor(int IDInstrutor, String novoNomeInstrutor){
        try{
            String sql = "UPDATE instrutor SET nomeInstrutor = ? WHERE IDInstrutor = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, novoNomeInstrutor);
                pstm.setInt(2, IDInstrutor);

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    public void updateNumeroTelefone(int IDInstrutor, String novoNumeroTelefone){
        try{
            String sql = "UPDATE instrutor SET numeroTelefone = ? WHERE IDInstrutor = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, novoNumeroTelefone);
                pstm.setInt(2, IDInstrutor);

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    public void updateEspecializacao(int IDInstrutor, String novaEspecializacao){
        try{
            String sql = "UPDATE instrutor SET especializacao = ? WHERE IDInstrutor = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, novaEspecializacao);
                pstm.setInt(2, IDInstrutor);

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
}
