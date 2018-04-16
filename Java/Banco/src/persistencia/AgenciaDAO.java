package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import banco.Application;
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
			Agencia agencia = null;
			
			if (rs.next()) { 
				do {
					agencia = new Agencia(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
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
	public Agencia consultaAgencia(int agencia_id) {
		Agencia agencia;
		DbUtilities dbutilities = new DbUtilities();
		String stmt = "SELECT * FROM jmpr1525_Banco.agencias WHERE agencia_id = " + agencia_id;
		ResultSet rs = dbutilities.ReadRecords(stmt);
		try {
			if (rs.next()) {
				do {
					agencia = new Agencia(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
					return agencia;
				} while (rs.next());
			}else {
				System.out.println("Não há registos.");
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu o erro: " + e.getMessage());
		}
		
		return null;
	}
	
	@Override
	public void insereAgencia(Agencia agencia) {	
		String cmdSql;
		DbUtilities dbutilities = new DbUtilities();
		cmdSql = "INSERT INTO jmpr1525_Banco.agencias (agencia_id, nome, morada, telefone, ultimo_cliente_id) VALUES (\"" + 
				String.valueOf(agencia.getAgenciaID()) + "\", \"" + 
				agencia.getNome() + "\", \"" +
				agencia.getMorada() + "\", \"" +
				agencia.getTelefone() + "\", \"" +
				String.valueOf(agencia.getUltimoClienteID()) + "\")";

		dbutilities.ExecuteSqlStatement(cmdSql);

		System.out.println("O registo foi inserido com sucesso.");

		dbutilities.DisconnectFromDB();
	}
	
	@Override
	public void alteraAgencia() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apagaAgencia() {
		// TODO Auto-generated method stub
		
	}

}
