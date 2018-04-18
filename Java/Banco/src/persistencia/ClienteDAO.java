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
		List<Cliente> clientes = new ArrayList<Cliente>();
		DbUtilities dbutils = new DbUtilities();
		ResultSet rs = null;
		String cmdSql;

		try {
			cmdSql = "SELECT * FROM jmpr1525_Banco.clientes";
			rs = dbutils.ReadRecords(cmdSql);
			
			if (rs.next()) { 
				do {
					int agenciaID = rs.getInt(1);
					int clienteID = rs.getInt(2);
					String tipo = rs.getString(3);
					String nome = rs.getString(4);
					String cartaoCidadao = rs.getString(5);
					String morada = rs.getString(6);
					String telefone = rs.getString(7);
					String email = rs.getString(8);
					String profissao = rs.getString(9);
					
					AgenciaDAO agenciaDao = new AgenciaDAO();
					Agencia agencia = agenciaDao.consultaAgencia(agenciaID);
					 					
					Cliente cliente = new Cliente(clienteID, agencia, tipo, nome, cartaoCidadao, morada, telefone, email, profissao);
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
	public Cliente consultaCliente(int agenciaID, int clienteID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insereCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		String cmdSql;
		DbUtilities dbutilities = new DbUtilities();
		cmdSql = "INSERT INTO jmpr1525_Banco.clientes (agencia_id, cliente_id, tipo, nome, cartao_cidadao, morada, telefone, email, profissao) " +
				"VALUES (\"" + 
				String.valueOf(cliente.getAgencia().getAgenciaID()) + "\", \"" +
				String.valueOf(cliente.getClienteID()) + "\", \"" + 
				cliente.getTipo() + "\", \"" +
				cliente.getNome() + "\", \"" +
				cliente.getCartaoCidadao() + "\", \"" +
				cliente.getMorada() + "\", \"" +
				cliente.getTelefone() + "\", \"" +
				cliente.getEmail() + "\", \"" +
				cliente.getProfissao() + "\" )";

		dbutilities.ExecuteSqlStatement(cmdSql);

		System.out.println("O registo foi inserido com sucesso.");

		dbutilities.DisconnectFromDB();
	}

	@Override
	public void alteraCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apagaCliente(int agenciaID, int clienteID) {
		// TODO Auto-generated method stub
		
	}

}
