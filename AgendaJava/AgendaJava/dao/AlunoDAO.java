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
import modelo.Acesso;

public class AlunoDAO {

    private Connection connection;

    public AlunoDAO(Connection connection) {
        this.connection = connection;
    }

    public void createBasico(Aluno aluno) {
        try {
            String sql = "INSERT INTO aluno (nome, numeroTelefone, dataNascimento, dataInscricao) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, aluno.getNome());
                pstm.setString(2, aluno.getNumeroTelefone());
                pstm.setObject(3, aluno.getDataNascimento());
                pstm.setObject(4, aluno.getDataInscricao());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        aluno.setMatricula(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createComAcesso(Aluno aluno) {
        try {
            String sql = "INSERT INTO aluno (nome, numeroTelefone, dataNascimento, dataInscricao) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, aluno.getNome());
                pstm.setString(2, aluno.getNumeroTelefone());
                pstm.setObject(3, aluno.getDataNascimento());
                pstm.setObject(4, aluno.getDataInscricao());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        aluno.setMatricula(rst.getInt(1));
                        for (Acesso acesso : aluno.getAcessos()){
                            AcessoDAO aDao = new AcessoDAO(connection);
                            aDao.createHistorico(acesso, aluno);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletaMatricula(int matricula){
        try{
            String sql = "DELETE FROM aluno WHERE matricula = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, matricula);

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletaAluno(Aluno aluno){
        try{
            String sql = "DELETE FROM aluno WHERE matricula = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, aluno.getMatricula());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    public ArrayList<Aluno> retriveAllAlunosSemAcesso (){
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();

        try{
            String sql = "SELECT matricula, nome, dataNascimento, endereco, numeroTelefone, email, dataInscricao, historicoPagamento, statusAssociacao FROM aluno";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()){
                    int matricula = rst.getInt("matricula");
                    String nome = rst.getString("nome");
                    LocalDate dataNascimento = rst.getObject("dataNascimento", LocalDate.class);
                    String endereco = rst.getString("endereco");
                    String numeroTelefone = rst.getString("numeroTelefone");
                    String email = rst.getString("email");
                    LocalDate dataInscricao = rst.getObject("dataInscricao", LocalDate.class);
                    boolean historicoPagamento = rst.getBoolean("historicoPagamento");
                    boolean statusAssociacao = rst.getBoolean("statusAssociacao");
                    Aluno p = new Aluno(matricula, nome, endereco, dataNascimento, numeroTelefone, email, dataInscricao, historicoPagamento, statusAssociacao);
                    alunos.add(p);
                }
            }
            return alunos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

    public ArrayList<Aluno> retriveAllAlunosComAcesso (){
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        Aluno ultimo = null;


        try{
            String sql = "SELECT matricula, nome, dataNascimento, endereco, numeroTelefone, email, dataInscricao, historicoPagamento,"
            + " statusAssociacao, IDAcesso, dataHoraEntrada, dataHoraSaida, statusPagamento, fk_Aluno_Matricula FROM aluno"
            + " LEFT JOIN acesso on matricula = fk_Aluno_Matricula";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()){
                    if (ultimo == null || ultimo.getMatricula() != rst.getInt(1)) {
                        int matricula = rst.getInt("matricula");
                        String nome = rst.getString("nome");
                        LocalDate dataNascimento = rst.getObject("dataNascimento", LocalDate.class);
                        String endereco = rst.getString("endereco");
                        String numeroTelefone = rst.getString("numeroTelefone");
                        String email = rst.getString("email");
                        LocalDate dataInscricao = rst.getObject("dataInscricao", LocalDate.class);
                        boolean historicoPagamento = rst.getBoolean("historicoPagamento");
                        boolean statusAssociacao = rst.getBoolean("statusAssociacao");
                        Aluno p = new Aluno(matricula, nome, endereco, dataNascimento, numeroTelefone, email, dataInscricao, historicoPagamento, statusAssociacao);
                        alunos.add(p);
                        ultimo = p;
                    }


                    if (rst.getInt("IDAcesso") != 0) {
                        int idAcesso = rst.getInt("idAcesso");
                        LocalDateTime dataHoraEntrada = rst.getObject("dataHoraEntrada", LocalDateTime.class);
                        LocalDateTime dataHoraSaida = rst.getObject("dataHoraSaida", LocalDateTime.class);
                        Boolean statusPagamento = rst.getBoolean("statusPagamento");
                        Acesso a = new Acesso(idAcesso, dataHoraEntrada, dataHoraSaida , statusPagamento);

                        ultimo.addAcesso(a);
                    }
                }
            }
            return alunos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Aluno retriveAlunoMatriculaSemAcessos(int matricula){

        try{
            String sql = "SELECT matricula, nome, dataNascimento, endereco, numeroTelefone, email, dataInscricao, historicoPagamento, statusAssociacao FROM aluno "
            + "WHERE matricula = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setInt(1, matricula);
                
                pstm.execute();

                ResultSet rst = pstm.getResultSet();

                rst.next();
                 
                String nome = rst.getString("nome");
                LocalDate dataNascimento = rst.getObject("dataNascimento", LocalDate.class);
                String endereco = rst.getString("endereco");
                String numeroTelefone = rst.getString("numeroTelefone");
                String email = rst.getString("email");
                LocalDate dataInscricao = rst.getObject("dataInscricao", LocalDate.class);
                boolean historicoPagamento = rst.getBoolean("historicoPagamento");
                boolean statusAssociacao = rst.getBoolean("statusAssociacao");
                Aluno aluno = new Aluno(matricula, nome, endereco, dataNascimento, numeroTelefone, email, dataInscricao, historicoPagamento, statusAssociacao);
                return aluno;
            }
            
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

    public Aluno retriveAlunoMatriculaComAcessos(int matricula){

        try{
            String sql = "SELECT matricula, nome, dataNascimento, endereco, numeroTelefone, email, dataInscricao, historicoPagamento,"
            + " statusAssociacao, IDAcesso, dataHoraEntrada, dataHoraSaida, statusPagamento, fk_Aluno_Matricula FROM aluno"
            + " LEFT JOIN acesso on matricula = fk_Aluno_Matricula"
            + "WHERE matricula = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setInt(1, matricula);
                
                pstm.execute();

                ResultSet rst = pstm.getResultSet();
                Aluno aluno = null;
                while(rst.next()){
                    if (aluno == null) {
                        String nome = rst.getString("nome");
                        LocalDate dataNascimento = rst.getObject("dataNascimento", LocalDate.class);
                        String endereco = rst.getString("endereco");
                        String numeroTelefone = rst.getString("numeroTelefone");
                        String email = rst.getString("email");
                        LocalDate dataInscricao = rst.getObject("dataInscricao", LocalDate.class);
                        boolean historicoPagamento = rst.getBoolean("historicoPagamento");
                        boolean statusAssociacao = rst.getBoolean("statusAssociacao");
                        aluno = new Aluno(matricula, nome, endereco, dataNascimento, numeroTelefone, email, dataInscricao, historicoPagamento, statusAssociacao);
               
                    }

                    if (rst.getInt("IDAcesso") != 0) {
                        int idAcesso = rst.getInt("idAcesso");
                        LocalDateTime dataHoraEntrada = rst.getObject("dataHoraEntrada", LocalDateTime.class);
                        LocalDateTime dataHoraSaida = rst.getObject("dataHoraSaida", LocalDateTime.class);
                        Boolean statusPagamento = rst.getBoolean("statusPagamento");
                        Acesso a = new Acesso(idAcesso, dataHoraEntrada, dataHoraSaida , statusPagamento);

                        aluno.addAcesso(a);
                    }
                    
                }
                return aluno;
            }
            
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

    public void updateNome(int matricula, String novoNome){
        try{
            String sql = "UPDATE aluno SET nome = ? WHERE matricula = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, novoNome);
                pstm.setInt(2, matricula);

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    public void updateNumeroTelefone(int matricula, String novoNumeroTelefone){
        try{
            String sql = "UPDATE aluno SET numeroTelefone = ? WHERE matricula = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, novoNumeroTelefone);
                pstm.setInt(2, matricula);

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    public void updateEndereco(int matricula, String novoEndereco){
        try{
            String sql = "UPDATE aluno SET endereco = ? WHERE matricula = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, novoEndereco);
                pstm.setInt(2, matricula);

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    public void updateEmail(int matricula, String novoEmail){
        try{
            String sql = "UPDATE aluno SET email = ? WHERE matricula = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, novoEmail);
                pstm.setInt(2, matricula);

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    public void updateStatusAssociacao(int matricula, boolean novoStatusAssociacao){
        try{
            String sql = "UPDATE aluno SET statusAssociacao = ? WHERE matricula = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setBoolean(1, novoStatusAssociacao);
                pstm.setInt(2, matricula);

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    public void updateHistoricoPagamento(int matricula, boolean novoHistoricoPagamento){
        try{
            String sql = "UPDATE aluno SET historicoPagamento = ? WHERE matricula = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setBoolean(1, novoHistoricoPagamento);
                pstm.setInt(2, matricula);

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

}
