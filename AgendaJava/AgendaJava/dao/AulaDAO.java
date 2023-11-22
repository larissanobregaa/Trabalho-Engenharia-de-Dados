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
import modelo.Aula;
import modelo.Instrutor;

public class AulaDAO {
    private Connection connection;

    public AulaDAO(Connection connection) {
        this.connection = connection;
    }

    public void createAula(Aula aula) {
        try {
            String sql = "INSERT INTO aula (horario, fk_instrutor, fk_aluno) VALUES (?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, aula.getHorario());
                pstm.setInt(2, aula.getInstrutor().getIDinstrutor());
                pstm.setInt(3, aula.getAluno().getMatricula());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        aula.setIdAula(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    
    }
}
