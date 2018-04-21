package persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtilities {
//	static final String DATABASE_URL = "jdbc:mysql://64.62.211.131:3306/jmpr1525_Banco?useSSL=false";
//	static final String DATABASE_USER = "jmpr1525_rumos";
//	static final String DATABASE_PSW = "rumos2018";
	static final String DATABASE_URL = "jdbc:mysql://192.168.1.152:3307/jmpr1525_Banco?useSSL=false";
	static final String DATABASE_USER = "julio";
	static final String DATABASE_PSW = "";
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public DbUtilities() {
		try {
			conn = DriverManager.getConnection(DATABASE_URL,DATABASE_USER, DATABASE_PSW);
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro " + e.getMessage() + " ao tentar ligar à base de dados!");
		}
	}

	public void DisconnectFromDB() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu o seguinte erro " + e.getMessage());
		}
	} // End of DisconnectFromDB()

	/*
	 * metodo para ler dados da base de dados
	 */
	public ResultSet ReadRecords(String cmdSql) {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(cmdSql);
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("Ocorreu o erro " + e.getMessage() + " ao tentar ler dados.");
		}
		
		return rs;
	} // End of ReadRecords()

	/*
	 * metodos para os comandos INSERT, UPDATE e DELETE
	 */
	public void ExecuteSqlStatement(String cmdSql) {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(cmdSql);
		} catch (SQLException e) {
			System.out.println("Ocorreu o erro " + e.getMessage() + " ao tentar alterar dados.");
		}
	} // End of ExecuteSqlStatement()
	
}
