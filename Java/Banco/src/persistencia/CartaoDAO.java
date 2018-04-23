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
					String nomeNoCartao = rs.getString(7);
					String dataValidade = rs.getString(8);
					
					ContaDAO contaDao = new ContaDAO();
					Conta conta = contaDao.consultaConta(agenciaID, numeroConta);
					
					Cartao cartao = new Cartao(cartaoID, nomeNoCartao, dataCriacao, dataValidade, tipo, conta);
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
