package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.Agencia;
import logica.Cliente;
import logica.Conta;
import logica.ContaAPrazo;
import logica.ContaPoupanca;

public class ContaDAO implements IContaDAO {

	@Override
	public List<Conta> listarContas(int agenciaID, int numeroCliente){
		// Obter todas as contas de um cliente
		String cmdSql = "SELECT * FROM jmpr1525_Banco.contas WHERE agencia_id = " +
				agenciaID + " AND numero_cliente = " + numeroCliente;
		
		return readContas(cmdSql);
	}

	@Override
	public List<Conta> listarContas(){
		// Obtenção de todas as contas existentes no banco
		String cmdSql = "SELECT * FROM jmpr1525_Banco.contas";
		
		return readContas(cmdSql);
	}
	
	public List<Conta> readContas(String cmdSql) {
		List<Conta> contas = new ArrayList<Conta>();
		DbUtilities dbutils = new DbUtilities();
		ResultSet rs = null;

		try {
			rs = dbutils.ReadRecords(cmdSql);
			
			if (rs.next()) { 
				do {
					int agenciaID = rs.getInt(1);
					int numeroConta = rs.getInt(2);
					int numeroCliente = rs.getInt(3);
					String tipo = rs.getString(4);
					String descricao = rs.getString(5);
					String dataCriacao = rs.getDate(6).toString();
					Double saldo = rs.getDouble(7);
//					Double taxaRemuneracao = rs.getDouble(8);
//					int periocidadeJuros = rs.getInt(9);
//					int prazoAnos = rs.getInt(10);
//					Double custo = rs.getDouble(11);
					int ultimoMovimento = rs.getInt(12);
					
					ClienteDAO clienteDao = new ClienteDAO();
					Cliente cliente = clienteDao.consultaCliente(agenciaID, numeroCliente);

					/*
					Conta conta;
					if ("ORDEM".equalsIgnoreCase(tipo)) {
						conta = new Conta(numeroConta, cliente, tipo, dataCriacao, saldo);
					} else if ("POUPANCA".equalsIgnoreCase(tipo)) {
						conta = new ContaPoupanca(numeroConta, cliente, tipo, dataCriacao, saldo);
					} else if ("PRAZO".equalsIgnoreCase(tipo)){
						conta = new ContaAPrazo(numeroConta, cliente, tipo, dataCriacao, saldo, prazoAnos);
					}
					*/
					
					Conta conta = new Conta(numeroConta, cliente, tipo, descricao, dataCriacao, saldo, ultimoMovimento);
					contas.add(conta);
				} while (rs.next());
			}  else {
				System.out.println("Nenhum registo encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu o erro: " + e.getMessage());
		} finally {
			dbutils.DisconnectFromDB();
		}

		return contas;
	}

	
	@Override
	public Conta consultaConta(int agenciaID, int numeroConta) {
		DbUtilities dbutilities = new DbUtilities();
		String stmt = "SELECT * FROM jmpr1525_Banco.contas WHERE agencia_id = " + agenciaID + " AND numero_conta = " + numeroConta;
		ResultSet rs = dbutilities.ReadRecords(stmt);
		try {
			if (rs.next()) {
				do {
					int numeroCliente = rs.getInt(3);
					String tipo = rs.getString(4);
					String descricao = rs.getString(5);
					String dataCriacao = rs.getDate(6).toString();
					Double saldo = rs.getDouble(7);
					int ultimoMovimento = rs.getInt(12);
					
					ClienteDAO clienteDao = new ClienteDAO();
					Cliente cliente = clienteDao.consultaCliente(agenciaID, numeroCliente);
					
					Conta conta = new Conta(numeroConta, cliente, tipo, descricao, dataCriacao, saldo, ultimoMovimento);
					dbutilities.DisconnectFromDB();
					return conta;
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
	public void insereConta(Conta conta) {
		String cmdSql;
		cmdSql = "INSERT INTO jmpr1525_Banco.contas (agencia_id, numero_conta, numero_cliente, tipo, descricao, data_criacao, saldo, " +
				"taxa_remuneracao, periocidade_juros, prazo_anos, custo, ultimomovimento) VALUES (\"" + 
				String.valueOf(conta.getCliente().getAgencia().getAgenciaID()) + "\", \"" +
				String.valueOf(conta.getNumeroConta()) + "\", \"" + 
				String.valueOf(conta.getCliente().getNumeroCliente()) + "\", \"" + 
				conta.getTipo() + "\", \"" +
				conta.getDescricao() + "\", \"" +
				conta.getDataCriacao() + "\", \"" +
				String.valueOf(conta.getSaldo()) + "\", null, null, null, null, \"0\" )";
		
		createConta(cmdSql);
	}

	@Override
	public void insereConta(ContaPoupanca contaPoupanca) {
		String cmdSql;
		cmdSql = "INSERT INTO jmpr1525_Banco.contas (agencia_id, numero_conta, numero_cliente, tipo, descricao, data_criacao, saldo, " +
				"taxa_remuneracao, periocidade_juros, prazo_anos, custo, ultimomovimento) VALUES (\"" + 
				String.valueOf(contaPoupanca.getCliente().getAgencia().getAgenciaID()) + "\", \"" +
				String.valueOf(contaPoupanca.getNumeroConta()) + "\", \"" + 
				String.valueOf(contaPoupanca.getCliente().getNumeroCliente()) + "\", \"" + 
				contaPoupanca.getTipo() + "\", \"" +
				contaPoupanca.getDescricao() + "\", \"" +
				contaPoupanca.getDataCriacao() + "\", \"" +
				String.valueOf(contaPoupanca.getSaldo()) + "\", \"" +
				String.valueOf(contaPoupanca.getTaxaRemuneracao()) + "\", \"" +
				String.valueOf(contaPoupanca.getPeriocidadeJuros()) + "\", null, null, \"0\" )";
		
		createConta(cmdSql);
	}

	@Override
	public void insereConta(ContaAPrazo contaPrazo) {
		String cmdSql;
		cmdSql = "INSERT INTO jmpr1525_Banco.contas (agencia_id, numero_conta, numero_cliente, tipo, descricao, data_criacao, saldo, " +
				"taxa_remuneracao, periocidade_juros, prazo_anos, custo, ultimomovimento) VALUES (\"" + 
				String.valueOf(contaPrazo.getCliente().getAgencia().getAgenciaID()) + "\", \"" +
				String.valueOf(contaPrazo.getNumeroConta()) + "\", \"" + 
				String.valueOf(contaPrazo.getCliente().getNumeroCliente()) + "\", \"" + 
				contaPrazo.getTipo() + "\", \"" +
				contaPrazo.getDescricao() + "\", \"" +
				contaPrazo.getDataCriacao() + "\", \"" +
				String.valueOf(contaPrazo.getSaldo()) + "\", \"" +
				String.valueOf(contaPrazo.getTaxaRemuneracao()) + "\", \"" +
				String.valueOf(contaPrazo.getPeriocidadeJuros()) + "\", \"" +
				String.valueOf(contaPrazo.getPrazoAnos()) + "\", null, \"0\" )";
		
		createConta(cmdSql);
	}

	private void createConta(String cmdSql) {
		DbUtilities dbutilities = new DbUtilities();

		dbutilities.ExecuteSqlStatement(cmdSql);

		System.out.println("O registo foi inserido com sucesso.");

		dbutilities.DisconnectFromDB();
	}

	@Override
	public void alteraConta(Conta conta) {
		
		String cmdSql = "UPDATE jmpr1525_Banco.contas SET tipo = \"" + conta.getTipo() +
				"\", descricao = \"" + conta.getDescricao() +
				"\", data_criacao = \"" + conta.getDataCriacao() + 
				"\" WHERE agencia_id = \"" + Integer.toString(conta.getCliente().getAgencia().getAgenciaID()) + 
				"\" AND numero_conta = \"" + Integer.toString(conta.getNumeroConta()) + "\"";

		
		updateConta(cmdSql);
	}

	@Override
	public void alteraConta(ContaPoupanca contaPoupanca) {
		
		String cmdSql = "UPDATE jmpr1525_Banco.contas SET tipo = \"" + contaPoupanca.getTipo() +
				"\", descricao = \"" + contaPoupanca.getDescricao() +
				"\", data_criacao = \"" + contaPoupanca.getDataCriacao() +
				"\", taxa_remuneracao = \"" + String.valueOf(contaPoupanca.getTaxaRemuneracao()) + 
				"\", periocidade_juros = \"" + String.valueOf(contaPoupanca.getPeriocidadeJuros()) + 
				"\" WHERE agencia_id = \"" + Integer.toString(contaPoupanca.getCliente().getAgencia().getAgenciaID()) + 
				"\" AND numero_conta = \"" + Integer.toString(contaPoupanca.getNumeroConta()) + "\"";

		
		updateConta(cmdSql);
	}

	@Override
	public void alteraConta(ContaAPrazo contaPrazo) {
		
		String cmdSql = "UPDATE jmpr1525_Banco.contas SET tipo = \"" + contaPrazo.getTipo() +
				"\", descricao = \"" + contaPrazo.getDescricao() +
				"\", data_criacao = \"" + contaPrazo.getDataCriacao() +
				"\", taxa_remuneracao = \"" + String.valueOf(contaPrazo.getTaxaRemuneracao()) + 
				"\", periocidade_juros = \"" + String.valueOf(contaPrazo.getPeriocidadeJuros()) + 
				"\", prazo_anos = \"" + String.valueOf(contaPrazo.getPrazoAnos()) + 
				"\" WHERE agencia_id = \"" + Integer.toString(contaPrazo.getCliente().getAgencia().getAgenciaID()) + 
				"\" AND numero_conta = \"" + Integer.toString(contaPrazo.getNumeroConta()) + "\"";

		
		updateConta(cmdSql);
	}

	private void updateConta(String cmdSql) {
		DbUtilities dbutilities = new DbUtilities();
		dbutilities.ExecuteSqlStatement(cmdSql);
		System.out.println("O registo foi alterado com sucesso.");
		dbutilities.DisconnectFromDB();
	}
	
	@Override
	public void apagaConta(int agenciaID, int numeroConta) {
		DbUtilities dbutilities = new DbUtilities();
		String cmdSql = "DELETE FROM jmpr1525_Banco.contas WHERE agencia_id = " + agenciaID +
				" AND numero_conta = " + numeroConta;
		
		dbutilities.ExecuteSqlStatement(cmdSql);
		
		System.out.println("O registo foi eliminado com sucesso.");
		
		dbutilities.DisconnectFromDB();
	}

	@Override
	public void atualizaSaldoUltimoMovimento(int agenciaID, int numeroConta, double saldo, int ultimoMovimento) {
		
		String cmdSql = "UPDATE jmpr1525_Banco.contas SET saldo = \"" + String.valueOf(saldo) +
				"\", ultimomovimento = \"" + String.valueOf(ultimoMovimento) + "\" WHERE agencia_id = \"" + agenciaID + 
				"\" AND numero_conta = \"" + numeroConta + "\"";
		
		System.out.println("Vai atualizar o saldo e o contador do ultimo movimento. Aguarde...");
		updateConta(cmdSql);
	}
}