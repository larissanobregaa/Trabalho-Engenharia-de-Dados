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

import modelo.Acesso;
import modelo.Aluno;
import modelo.Instrutor;
import modelo.Turma;

public class TurmaDAO {
    private Connection connection;

    public TurmaDAO(Connection connection) {
        this.connection = connection;
    }

    public void createTurmaBase(Turma turma) {
        try {
            String sql = "INSERT INTO turma (tipo, horario, qtdAlunos, fk_instrutor) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, turma.getTipoTurma());
                pstm.setInt(2, turma.getHorario());
                pstm.setInt(3, turma.getQtdAlunos());
                pstm.setInt(4, turma.getInstrutor().getIDinstrutor());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        turma.setIDTurma(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAlunoATurma(Turma turma, Aluno aluno) {
        try {
            String sql = "INSERT INTO aluno_has_turma (Aluno_matricula, Turma_IDTurma) VALUES (?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, aluno.getMatricula());
                pstm.setInt(2, turma.getIDTurma());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        turma.setIDTurma(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAlunoDaTurma(Turma turma, Aluno aluno) {
        try {
            String sql = "DELETE from aluno_has_turma where Aluno_matricula = ? and Turma_IDTurma = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, aluno.getMatricula());
                pstm.setInt(2, turma.getIDTurma());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        turma.setIDTurma(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletaTurma(int iDTurma){
        try{
            String sql = "DELETE FROM turma WHERE IDTurma = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, iDTurma);

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    public void deletaTurma(Turma turma) {
        deletaTurma(turma.getIDTurma());
    }

    public Turma retriveTurma(int iDTurma){

        try{
            String sql = "SELECT t.IDTurma, t.tipoTurma, t.horario, t.qtdAlunos, t.fk_Instrutor, a.Aluno_matricula, a.Turma_IDTurma FROM turma as t LEFT JOIN instrutor as i on"
            + " t.fk_Instrutor = i.IDInstrutor LEFT JOIN aluno_has_turma as a on t.IDTurma = a.Turma_IDTurma"
            + "WHERE t.IDTurma = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setInt(1, iDTurma);
                
                pstm.execute();

                ResultSet rst = pstm.getResultSet();
                Turma turma = null;
                Instrutor ultimo = null;
                while(rst.next()){
                    if (turma == null) {
                        String tipoTurma = rst.getString("t.tipoTurma");
                        int horario = rst.getInt("t.horario");
                        int qtdAlunos = rst.getInt("t.qtdAlunos");
                        turma = new Turma(iDTurma, tipoTurma, horario, qtdAlunos);
               
                    }

                    if (ultimo == null && rst.getInt("fk_Instrutor") != 0) {
                        InstrutorDAO iDao = new InstrutorDAO(connection);
                        turma.setInstrutor(iDao.retriveInstrutor(rst.getInt("fk_Instrutor")));
                    }


                    if (rst.getInt("Aluno_matricula") != 0) {
                        AlunoDAO aDao = new AlunoDAO(connection);
                        turma.addAluno(aDao.retriveAlunoMatriculaSemAcessos(rst.getInt("Aluno_matricula")));
                    }
                    
                }
                return turma;
            }
            
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

    public void updateInstrutor(int iDTurma, int novoiDInstrutor){
        try{
            String sql = "UPDATE turma SET fk_Instrutor = ? WHERE IDTurma = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, novoiDInstrutor);
                pstm.setInt(2, iDTurma);

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

}
