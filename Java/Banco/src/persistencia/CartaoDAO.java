package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.Agencia;
import logica.Cartao;
import logica.CartaoCredito;
import logica.Cliente;
import logica.Conta;

public class CartaoDAO implements ICartaoDAO {

	@Override
	public Cartao consultaCartao(int cartaoID) {
		DbUtilities dbutilities = new DbUtilities();
		String stmt = "SELECT * FROM jmpr1525_Banco.cartoes WHERE cartao_id = " + cartaoID;
		ResultSet rs = dbutilities.ReadRecords(stmt);
		try {
			if (rs.next()) {
				do {
					int agenciaID = rs.getInt(2);
					int numeroConta = rs.getInt(3);
					char tipo = rs.getString(4).charAt(0);
					String dataCriacao = rs.getDate(5).toString();
					String descricao = rs.getString(6);
					String nomeNoCartao = rs.getString(7);
					String dataValidade = rs.getString(8);
					
					ContaDAO contaDao = new ContaDAO();
					Conta conta = contaDao.consultaConta(agenciaID, numeroConta);
					
					Cartao cartao = new Cartao(cartaoID, descricao, nomeNoCartao, dataCriacao, dataValidade, tipo, conta);
					dbutilities.DisconnectFromDB();
					
					return cartao;
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
	public int insereCartao(Cartao cartao) {
		String cmdSql;
		cmdSql = "INSERT INTO jmpr1525_Banco.cartoes (cartao_id, agencia_id, numero_conta, tipo, data_criacao, descricao, nome_no_cartao, data_validade) VALUES (NULL, \"" +
				String.valueOf(cartao.getConta().getCliente().getAgencia().getAgenciaID()) + "\", \"" +
				String.valueOf(cartao.getConta().getNumeroConta()) + "\", '" +
				cartao.getTipo() + "', \"" +
				cartao.getDataCriacao() + "\", \"" +
				cartao.getDescricao() + "\", \"" + 
				cartao.getNomeNoCartao() + "\", \"" +
				cartao.getDataValidade() + "\")";	
		
		int lastId = createCartao(cmdSql);

		/*		
		cmdSql = "INSERT INTO jmpr1525_Banco.cartoes (agencia_id, numero_conta, tipo, data_criacao, descricao, nome_no_cartao VALUES (?,?,?,?,?,?)";
				
		String[] dados = {
				String.valueOf(cartao.getConta().getCliente().getAgencia().getAgenciaID()),
				String.valueOf(cartao.getConta().getNumeroConta()),
				String.valueOf(cartao.getTipo()),
				cartao.getDataCriacao(),
				cartao.getDescricao(),
				cartao.getNomeNoCartao() };
		
		int lastId = createCartao(cmdSql, dados);
*/
		
		return lastId;
	}

	@Override
	public int insereCartao(CartaoCredito cartaoCredito) {
		String cmdSql;
		cmdSql = "INSERT INTO jmpr1525_Banco.cartoes (agencia_id, numero_conta, tipo, data_criacao, descricao, nome_no_cartao, data_validade, " + 
				"plafond_mensal, plafond_disponivel, data_limite_pagamento, dia_inicio_extrato) VALUES (NULL, \"" +
				String.valueOf(cartaoCredito.getConta().getCliente().getAgencia().getAgenciaID()) + "\", \"" + 
				String.valueOf(cartaoCredito.getConta().getNumeroConta()) + "\", '" +
				String.valueOf(cartaoCredito.getTipo()) + "', \"" +
				cartaoCredito.getDataCriacao() + "\", \"" +
				cartaoCredito.getDescricao() + "\", \"" +
				cartaoCredito.getNomeNoCartao() + "\", \"" +
				cartaoCredito.getDataValidade() + "\", \"" +
				String.valueOf(cartaoCredito.getPlafondMensal()) + "\", \"" +
				String.valueOf(cartaoCredito.getPlafondDisponivel()) + "\", \"" +
				cartaoCredito.getDataLimitePagamento() + "\", \"" +
				String.valueOf(cartaoCredito.getDiaInicioExtrato()) + "\")";
		
		int lastId = createCartao(cmdSql);

		return lastId;
	}

	private int  createCartao(String cmdSql) {
		DbUtilities dbutilities = new DbUtilities();
		int lastId = dbutilities.ExecuteSqlStatement(cmdSql);
		System.out.println("O registo foi inserido com sucesso.");
		dbutilities.DisconnectFromDB();
		
		return lastId;
	}

	private int  createCartao(String cmdSql, String[] dados) {
		DbUtilities dbutilities = new DbUtilities();
		int lastId = dbutilities.ExecuteSqlStatement(cmdSql, dados);
		System.out.println("O registo foi inserido com sucesso.");
		dbutilities.DisconnectFromDB();
		
		return lastId;
	}

	@Override
	public void alteraCartao(Cartao cartao) {
		DbUtilities dbutilities = new DbUtilities();

		String cmdSql = "UPDATE jmpr1525_Banco.cartoes SET agencia_id = \"" + 
				String.valueOf(cartao.getConta().getCliente().getAgencia().getAgenciaID()) +
				"\", numero_conta = \"" + String.valueOf(cartao.getConta().getNumeroConta()) + 
				"\", tipo = '" + cartao.getTipo() + 
				"', data_criacao = \"" + cartao.getDataCriacao() + 
				"\", descricao = \"" + cartao.getDescricao() + 
				"\", nome_no_cartao = \"" + cartao.getNomeNoCartao() +
				"\", data_validade = \"" + cartao.getDataValidade() +
				"\" WHERE cartao_id = \"" + Integer.toString(cartao.getCartaoID()) + "\"";

		dbutilities.ExecuteSqlStatement(cmdSql);

		System.out.println("O registo foi alterado com sucesso.");

		dbutilities.DisconnectFromDB();

	}
	@Override
	public void alteraCartao(CartaoCredito cartaoCredito) {
		DbUtilities dbutilities = new DbUtilities();

		String cmdSql = "UPDATE jmpr1525_Banco.cartoes SET agencia_id = \"" + 
				String.valueOf(cartaoCredito.getConta().getCliente().getAgencia().getAgenciaID()) +
				"\", numero_conta = \"" + String.valueOf(cartaoCredito.getConta().getNumeroConta()) + 
				"\", tipo = '" + cartaoCredito.getTipo() + 
				"', data_criacao = \"" + cartaoCredito.getDataCriacao() + 
				"\", descricao = \"" + cartaoCredito.getDescricao() + 
				"\", nome_no_cartao = \"" + cartaoCredito.getNomeNoCartao() +
				"\", data_validade = \"" + cartaoCredito.getDataValidade() +
				"\", plafond_mensal = \"" + String.valueOf(cartaoCredito.getPlafondMensal()) +
				"\", plafond_disponivel = \"" + String.valueOf(cartaoCredito.getPlafondDisponivel()) +
				"\", data_limite_pagamento = \"" + cartaoCredito.getDataLimitePagamento() +
				"\", dia_inicio_extrato = \"" + String.valueOf(cartaoCredito.getDiaInicioExtrato()) +
				"\" WHERE cartao_id = \"" + Integer.toString(cartaoCredito.getCartaoID()) + "\"";

		dbutilities.ExecuteSqlStatement(cmdSql);

		System.out.println("O registo foi alterado com sucesso.");

		dbutilities.DisconnectFromDB();

	}

	@Override
	public void apagaCartao(int cartaoID) {
		DbUtilities dbutilities = new DbUtilities();
		String cmdSql = "DELETE FROM jmpr1525_Banco.cartoes WHERE cartao_id = " + cartaoID;
		
		dbutilities.ExecuteSqlStatement(cmdSql);
		
		System.out.println("O registo foi eliminado com sucesso.");
		
		dbutilities.DisconnectFromDB();
	}

	/*
	@Override
	public List<Cartao> listaCartoesCliente(int agenciaID, int numeroCliente) {
		/*
		String cmdSql = "SELECT * FROM jmpr1525_banco.cartoes WHERE agencia_id = \""+agenciaID+"\" AND numero_conta in " +
				"(SELECT numero_conta FROM jmpr1525_banco.contas WHERE agencia_id = \""+agenciaID+"\" AND numero_cliente = \""+
				numeroCliente + "\")";
		 */
	/*

		return null;
	}
	 */


	@Override
	public List<Cartao> listaCartoes() {
		String cmdSql = "SELECT * FROM jmpr1525_Banco.cartoes";
		return readCartoes(cmdSql);
	}


	@Override
	public List<Cartao> listaCartoes(int agenciaID, int numeroConta) {
		String cmdSql = "SELECT * FROM jmpr1525_Banco.cartoes WHERE agencia_id = \""+agenciaID+
				"\" AND numero_conta = \""+numeroConta+"\"";
		return readCartoes(cmdSql);
	}

	public List<Cartao> readCartoes(String cmdSql) {
		List<Cartao> cartoes = new ArrayList<Cartao>();
		DbUtilities dbutils = new DbUtilities();
		ResultSet rs = null;

		try {
			rs = dbutils.ReadRecords(cmdSql);
			
			if (rs.next()) { 
				do {
					int cartaoID = rs.getInt(1);
					int agenciaID = rs.getInt(2);
					int numeroConta = rs.getInt(3);
					char tipo = rs.getString(4).charAt(0);
					String dataCriacao = rs.getDate(5).toString();
					String descricao = rs.getString(6);
					String nomeNoCartao = rs.getString(7);
					String dataValidade = rs.getDate(8).toString();
										
					ContaDAO contaDao = new ContaDAO();
					Conta conta = contaDao.consultaConta(agenciaID, numeroConta);

					Cartao cartao = new Cartao(cartaoID, descricao, nomeNoCartao, dataCriacao, dataValidade, tipo, conta);
					cartoes.add(cartao);
				} while (rs.next());
			}  else {
				System.out.println("Nenhum registo encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu o erro: " + e.getMessage());
		} finally {
			dbutils.DisconnectFromDB();
		}

		return cartoes;

	}

}
