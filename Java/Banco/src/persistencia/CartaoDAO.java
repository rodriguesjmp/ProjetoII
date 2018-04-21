package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import logica.Cartao;
import logica.CartaoCredito;
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
					
					ContaDAO contaDao = new ContaDAO();
					Conta conta = contaDao.consultaConta(agenciaID, numeroConta);
					
					Cartao cartao = new Cartao(cartaoID, descricao, dataCriacao, tipo, conta);
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
	public int consultaCartao(int agenciaID, int numeroConta, char tipo) {
		int cartaoID = 0;
		DbUtilities dbutilities = new DbUtilities();
		String stmt = "SELECT * FROM jmpr1525_Banco.cartoes WHERE agencia_id = " + agenciaID +
					" AND numero_conta = " + numeroConta + " AND tipo = '" + tipo + "'";
		ResultSet rs = dbutilities.ReadRecords(stmt);
		try {
			if (rs.next()) {
				do {
					cartaoID = rs.getInt(1);
				} while (rs.next());
			}else {
				System.out.println("Não há registos.");
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu o erro: " + e.getMessage());
		}
		
		dbutilities.DisconnectFromDB();

		return cartaoID;
	}

	
	@Override
	public void insereCartao(Cartao cartao) {
		String cmdSql;
		cmdSql = "INSERT INTO jmpr1525_Banco.cartoes (cartao_id, agencia_id, numero_conta, tipo, data_criacao, descricao, plafond_mensal, " +
				"plafond_disponivel, data_limite_pagamento, dia_inicio_extrato) VALUES (NULL, \"" +
				String.valueOf(cartao.getConta().getCliente().getAgencia().getAgenciaID()) + "\", \"" + 
				String.valueOf(cartao.getConta().getNumeroConta()) + "\", '" + 
				cartao.getTipo() + "', \"" +
				cartao.getDataCriacao() + "\", \"" +
				cartao.getDescricao() + "\", NULL, NULL, NULL, NULL)";
		
		createCartao(cmdSql);

	}

	@Override
	public void insereCartao(CartaoCredito cartaoCredito) {
		String cmdSql;
		cmdSql = "INSERT INTO jmpr1525_Banco.cartoes (cartao_id, agencia_id, numero_conta, tipo, data_criacao, descricao, plafond_mensal, " +
				"plafond_disponivel, data_limite_pagamento, dia_inicio_extrato) VALUES (NULL, \"" +
				String.valueOf(cartaoCredito.getConta().getCliente().getAgencia().getAgenciaID()) + "\", \"" + 
				String.valueOf(cartaoCredito.getConta().getNumeroConta()) + "\", '" + 
				cartaoCredito.getTipo() + "', \"" +
				cartaoCredito.getDataCriacao() + "\", \"" +
				cartaoCredito.getDescricao() + "\", \"" +
				String.valueOf(cartaoCredito.getPlafondMensal()) + "\", \"" +
				String.valueOf(cartaoCredito.getPlafondDisponivel()) + "\", \"" +
				cartaoCredito.getDataLimitePagamento() + "\", \"" +
				cartaoCredito.getDiaInicioExtrato() + "\")";
		
		createCartao(cmdSql);

	}

	private void createCartao(String cmdSql) {
		DbUtilities dbutilities = new DbUtilities();
		dbutilities.ExecuteSqlStatement(cmdSql);
		System.out.println("O registo foi inserido com sucesso.");
		dbutilities.DisconnectFromDB();
	}

	@Override
	public void alteraCartao(Cartao cartao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apagaCartao(int cartaoID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cartao> listaCartoesCliente(int agenciaID, int numeroCliente) {
		// TODO Auto-generated method stub
		return null;
	}

}
