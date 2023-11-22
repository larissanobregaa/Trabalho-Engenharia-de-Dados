import java.security.KeyStore.PasswordProtection;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import modelo.Acesso;
import modelo.Aluno;
import modelo.Instrutor;
import dao.ConnectionFactory;
import dao.InstrutorDAO;
import dao.AcessoDAO;
import dao.AlunoDAO;
import dao.TurmaDAO;
import dao.AulaDAO;

public class Principal{


    public static void main(String[] args) throws SQLException {

        ConnectionFactory fabricaDeConexao = new ConnectionFactory();
        Connection connection = fabricaDeConexao.recuperaConexao();

        AcessoDAO acDAO = new AcessoDAO(connection);
        AlunoDAO alDAO = new AlunoDAO(connection);
        AulaDAO auDAO = new AulaDAO(connection);
        InstrutorDAO iDAO = new InstrutorDAO(connection);
        TurmaDAO tDAO = new TurmaDAO(connection);


        Aluno pessoa1 = new Aluno("Amanda Senra", "00011122200", LocalDate.of(2000, 1, 01)); 
        Aluno pessoa2 = new Aluno("Gabriel Martinez", "00011122211", LocalDate.of(2001, 2, 05));
        Aluno pessoa3 = new Aluno("Joao Curvello", "00011122222", LocalDate.of(2002, 3, 10));
        Aluno pessoa4 = new Aluno("Joao Correia", "00011122233", LocalDate.of(2003, 4, 15));
        Aluno pessoa5 = new Aluno("Joao Constant", "00011122244", LocalDate.of(2004, 5, 20)); 
        Aluno pessoa6 = new Aluno("Matheus Herzog", "00011122255", LocalDate.of(2005, 6, 25)); 
        Aluno pessoa7 = new Aluno("Thaís Bustamante", "00011122266", LocalDate.of(2000, 7, 30));
        Aluno pessoa8 = new Aluno("Théo Mauricio", "00011122277", LocalDate.of(2001, 8, 01)); 
        Aluno pessoa9 = new Aluno("Victor Lobianco", "00011122288", LocalDate.of(2002, 9, 05));

        Acesso acesso1 = new Acesso(LocalDateTime.of(2022, 4, 14, 10, 11, 23), LocalDateTime.of(2022, 4, 14, 12, 1, 2), false);
        Acesso acesso2 = new Acesso(LocalDateTime.of(2022, 4, 15, 9, 1, 33), LocalDateTime.of(2022, 4, 15, 10, 15, 22), false);
        Acesso acesso3 = new Acesso(LocalDateTime.of(2022, 4, 15, 10, 54, 22), LocalDateTime.of(2022, 4, 15, 12, 1, 45), false);
        Acesso acesso4 = new Acesso(LocalDateTime.of(2022, 4, 16, 10, 25, 11), LocalDateTime.of(2022, 4, 16, 12, 10, 33), false);
        Acesso acesso5 = new Acesso(LocalDateTime.of(2022, 4, 17, 10, 2, 23), LocalDateTime.of(2022, 4, 17, 12, 30, 1), false);
        Acesso acesso6 = new Acesso(LocalDateTime.of(2022, 4, 18, 10, 33, 44), LocalDateTime.of(2022, 4, 18, 12, 33, 17), false);

        pessoa7.addAcesso(acesso1);
        pessoa7.addAcesso(acesso2);
        pessoa8.addAcesso(acesso3);
        pessoa8.addAcesso(acesso4);
        pessoa9.addAcesso(acesso5);
        pessoa9.addAcesso(acesso6);

    
        Instrutor instrutor1 = new Instrutor("Joao", LocalDate.of(1983, 3, 10), "994378235",  "Musculacao");
        Instrutor instrutor2 = new Instrutor("Ana", LocalDate.of(1987, 4, 11), "994234565",  "Danca(tango)");


 
        alDAO.createBasico(pessoa1);
        alDAO.createBasico(pessoa2);
        alDAO.createBasico(pessoa3);
        alDAO.createBasico(pessoa4);
        alDAO.createBasico(pessoa5);
        alDAO.createBasico(pessoa6);


        alDAO.createComAcesso(pessoa7);
        alDAO.createComAcesso(pessoa8);
        alDAO.createComAcesso(pessoa9);

        iDAO.createBasico(instrutor1);
        iDAO.createBasico(instrutor2);

        Aluno aluno1 = alDAO.retriveAlunoMatriculaSemAcessos(2);
        System.out.println(aluno1);

    }

}