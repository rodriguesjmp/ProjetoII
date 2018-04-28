package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.Agencia;
import logica.Cliente;

public class ClienteDAO implements IClienteDAO {

	@Override
	public List<Cliente> listarClientes() {
		String cmdSql = "SELECT * FROM jmpr1525_Banco.clientes";
		return readClientes(cmdSql);
		}
		
	@Override
	public List<Cliente> listarClientes(int agenciaID) {
		String cmdSql = "SELECT * FROM jmpr1525_Banco.clientes WHERE agencia_id = " + agenciaID;
		return readClientes(cmdSql);
		}
		
	
	public List<Cliente> readClientes(String cmdSql) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		DbUtilities dbutils = new DbUtilities();
		ResultSet rs = null;

		try {
			rs = dbutils.ReadRecords(cmdSql);
			
			if (rs.next()) { 
				do {
					int agenciaID = rs.getInt(1);
					int numeroCliente = rs.getInt(2);
					char tipo = rs.getString(3).charAt(0);
					String nome = rs.getString(4);
					String cartaoCidadao = rs.getString(5);
					String morada = rs.getString(6);
					String telefone = rs.getString(7);
					String email = rs.getString(8);
					String dataCriacao = rs.getDate(9).toString();
					String dataNascimento = String.valueOf(rs.getDate(10));
					String profissao = rs.getString(11);
					
					AgenciaDAO agenciaDao = new AgenciaDAO();
					Agencia agencia = agenciaDao.consultaAgencia(agenciaID);

					Cliente cliente = new Cliente(numeroCliente, agencia, tipo, nome, cartaoCidadao, morada, telefone, email, dataCriacao, dataNascimento, profissao);
					clientes.add(cliente);
				} while (rs.next());
			}  else {
				System.out.println("Nenhum registo encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu o erro: " + e.getMessage());
		} finally {
			dbutils.DisconnectFromDB();
		}

		return clientes;
	}

	@Override
	public Cliente consultaCliente(int agenciaID, int numeroCliente) {
		DbUtilities dbutilities = new DbUtilities();
		String stmt = "SELECT * FROM jmpr1525_Banco.clientes WHERE agencia_id = " + agenciaID +
				" AND numero_cliente = " + numeroCliente;
		
		AgenciaDAO agenciaDao = new AgenciaDAO();
		Agencia agencia = null;
		
		ResultSet rs = dbutilities.ReadRecords(stmt);
		try {
			if (rs.next()) {
				do {
					agencia = agenciaDao.consultaAgencia(rs.getInt(1));
					Cliente cliente = new Cliente(rs.getInt(2), agencia, rs.getString(3).charAt(0), rs.getString(4), rs.getString(5), rs.getString(6), 
							rs.getString(7), rs.getString(8), rs.getDate(9).toString(), String.valueOf(rs.getDate(10)), rs.getString(11));

					dbutilities.DisconnectFromDB();
					return cliente;
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
	public void insereCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		String cmdSql;
		DbUtilities dbutilities = new DbUtilities();
		cmdSql = "INSERT INTO jmpr1525_Banco.clientes (agencia_id, numero_cliente, tipo, nome, cartao_cidadao, morada, " +
				"telefone, email, data_criacao, data_nascimento, profissao) VALUES (\"" + 
				String.valueOf(cliente.getAgencia().getAgenciaID()) + "\", \"" +
				String.valueOf(cliente.getNumeroCliente()) + "\", '" + 
				cliente.getTipo() + "', \"" +
				cliente.getNome() + "\", \"" +
				cliente.getCartaoCidadao() + "\", \"" +
				cliente.getMorada() + "\", \"" +
				cliente.getTelefone() + "\", \"" +
				cliente.getEmail() + "\", \"" +
				cliente.getDataCriacao() + "\", \"" +
				cliente.getDataNascimento() + "\", \"" +
				cliente.getProfissao() + "\" )";

		dbutilities.ExecuteSqlStatement(cmdSql);

		System.out.println("O registo foi inserido com sucesso.");

		dbutilities.DisconnectFromDB();
	}

	@Override
	public void alteraCliente(Cliente cliente) {
		DbUtilities dbutilities = new DbUtilities();
		
		String cmdSql = "UPDATE jmpr1525_Banco.clientes SET tipo = '" + cliente.getTipo() +
				"', nome = \"" + cliente.getNome() +
				"\", cartao_cidadao = \"" + cliente.getCartaoCidadao() + 
				"\", morada = \"" + cliente.getMorada() + 
				"\", telefone = \"" + cliente.getTelefone() +
				"\", email = \"" + cliente.getEmail() +
				"\", data_nascimento = \"" + String.valueOf(cliente.getDataNascimento()) +
				"\", profissao = \"" + cliente.getProfissao() +
				"\" WHERE agencia_id = \"" + Integer.toString(cliente.getAgencia().getAgenciaID()) + 
				"\" AND numero_cliente = \"" + Integer.toString(cliente.getNumeroCliente()) + "\"";

		dbutilities.ExecuteSqlStatement(cmdSql);

		System.out.println("O registo foi alterado com sucesso.");

		dbutilities.DisconnectFromDB();

	}

	@Override
	public void apagaCliente(int agenciaID, int numeroCliente) {
		DbUtilities dbutilities = new DbUtilities();
		String cmdSql = "DELETE FROM jmpr1525_Banco.clientes WHERE agencia_id = " + agenciaID +
				" AND numero_cliente = " + numeroCliente;
		
		dbutilities.ExecuteSqlStatement(cmdSql);
		
		System.out.println("O registo foi eliminado com sucesso.");
		
		dbutilities.DisconnectFromDB();
	}

}