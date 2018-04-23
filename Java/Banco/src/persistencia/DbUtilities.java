package persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	PreparedStatement pstmt = null;
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
	public int ExecuteSqlStatement(String cmdSql) {
		int lastId = -1;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(cmdSql, Statement.RETURN_GENERATED_KEYS);
			
			final ResultSet keys = stmt.getGeneratedKeys();
			while (keys.next()) {
			    lastId = keys.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu o erro " + e.getMessage() + " ao tentar alterar dados.");
		}
		
		return lastId;
	} // End of ExecuteSqlStatement()
	
	public int ExecuteSqlStatement(String cmdSql, String[] dados) {
		int lastId = -1;
		try {
			pstmt = conn.prepareStatement(cmdSql, Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < dados.length; i++) {
				pstmt.setString(i + 1, dados[i]);
			}
			pstmt.executeUpdate(cmdSql);
			
			final ResultSet keys = pstmt.getGeneratedKeys();
			while (keys.next()) {
			    lastId = keys.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu o erro " + e.getMessage() + " ao tentar alterar dados.");
		}
		
		return lastId;
	} // End of ExecuteSqlStatement()
}
