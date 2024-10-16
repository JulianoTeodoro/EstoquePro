package estacio.API.EstoquePro.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Context {
	private static final String URL = "jdbc:mysql://estoquepro-estacio.c1eiug8i6wfp.us-east-1.rds.amazonaws.com";
	private static final String User = "admin";
	private static final String Password = "6EQAMjuwKHShMotG0MAv";


	public static Connection getConexao() {
        try {
            return DriverManager.getConnection(URL, User, Password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

	}
}
