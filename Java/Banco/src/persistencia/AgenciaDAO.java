package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.Agencia;

public class AgenciaDAO implements IAgenciaDAO {

	@Override
	public List<Agencia> listarAgencias() {
		List<Agencia> agencias = new ArrayList<Agencia>();
		DbUtilities dbutils = new DbUtilities();
		ResultSet rs = null;
		String cmdSql;

		try {
			cmdSql = "SELECT * FROM jmpr1525_Banco.agencias";
			rs = dbutils.ReadRecords(cmdSql);
			
			if (rs.next()) { 
				do {
					Agencia agencia = new Agencia(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getInt(5));
					agencias.add(agencia);
				} while (rs.next());
			}  else {
				System.out.println("Nenhum registo encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu o erro: " + e.getMessage());
		} finally {
			dbutils.DisconnectFromDB();
		}

		return agencias;
	}

	@Override
	public Agencia consultaAgencia(int agenciaID) {
		DbUtilities dbutilities = new DbUtilities();
		String stmt = "SELECT * FROM jmpr1525_Banco.agencias WHERE agencia_id = " + agenciaID;
		ResultSet rs = dbutilities.ReadRecords(stmt);
		try {
			if (rs.next()) {
				do {
					Agencia agencia = new Agencia(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
					dbutilities.DisconnectFromDB();
					return agencia;
				} while (rs.next());
			}else {
				System.out.println("Não há registos.");
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu o erro: " + e.getMessage());
		}
		
		dbutilities.DisconnectFromDB();
		
		return null;
	}
	
	@Override
	public void insereAgencia(Agencia agencia) {	
		String cmdSql;
		DbUtilities dbutilities = new DbUtilities();
		cmdSql = "INSERT INTO jmpr1525_Banco.agencias (agencia_id, nome, morada, telefone) VALUES (\"" + 
				String.valueOf(agencia.getAgenciaID()) + "\", \"" + 
				agencia.getNome() + "\", \"" +
				agencia.getMorada() + "\", \"" +
				agencia.getTelefone() + "\", \"" +
				agencia.getUltimaConta() + "\" )";

		dbutilities.ExecuteSqlStatement(cmdSql);

		System.out.println("O registo foi inserido com sucesso.");

		dbutilities.DisconnectFromDB();
	}
	
	@Override
	public void alteraAgencia(Agencia agencia) {
		DbUtilities dbutilities = new DbUtilities();
			
		String cmdSql = "UPDATE jmpr1525_Banco.agencias SET nome = \"" + agencia.getNome() +
				"\", morada = \"" + agencia.getMorada() + 
				"\", telefone = \"" + agencia.getTelefone() +
				"\" WHERE agencia_id = \"" + Integer.toString(agencia.getAgenciaID()) + "\"";

		dbutilities.ExecuteSqlStatement(cmdSql);

		System.out.println("O registo foi alterado com sucesso.");

		dbutilities.DisconnectFromDB();

	}

	@Override
	public void apagaAgencia(int agenciaID) {
		DbUtilities dbutilities = new DbUtilities();
		String cmdSql = "DELETE FROM jmpr1525_Banco.agencias WHERE agencia_id = " + agenciaID;
		
		dbutilities.ExecuteSqlStatement(cmdSql);
		
		System.out.println("O registo foi eliminado com sucesso.");
		
		dbutilities.DisconnectFromDB();
	}

	@Override
	public void atualizaUltimaConta(int agenciaID, int ultimaConta) {
		DbUtilities dbutilities = new DbUtilities();
		
		String cmdSql = "UPDATE jmpr1525_Banco.agencias SET ultimaconta = \"" + ultimaConta +
				"\" WHERE agencia_id = \"" + agenciaID + "\"";

		dbutilities.ExecuteSqlStatement(cmdSql);
		
		System.out.println("Ultima conta foi alterada com sucesso.");

		dbutilities.DisconnectFromDB();
	}
}
