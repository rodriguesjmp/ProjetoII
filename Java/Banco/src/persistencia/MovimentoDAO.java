package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import logica.Cartao;
import logica.Conta;
import logica.Movimento;

public class MovimentoDAO implements IMovimentoDAO {

	@Override
	public void insereMovimento(Movimento movimento) {
		StringBuilder sb = new StringBuilder("INSERT INTO jmpr1525_Banco.movimentos (agencia_id, numero_conta, numero_movimento, " +
				"cartao_id, data_movimento, tipo, tipo_descr, valor, descricao, ref_agencia_id, ref_numero_conta) VALUES (");
		
		sb.append("\"");
		sb.append(String.valueOf(movimento.getCartao().getConta().getCliente().getAgencia().getAgenciaID()));
		sb.append("\", \"");
		sb.append(String.valueOf(movimento.getCartao().getConta().getNumeroConta()));
		sb.append("\", \"");
		sb.append(String.valueOf(movimento.getNumeroMovimento()));		
		sb.append("\", \"");
		sb.append(String.valueOf(movimento.getCartao().getCartaoID()));
		sb.append("\", \"");
		sb.append(movimento.getDataMovimento());
		sb.append("\", '");
		sb.append(movimento.getTipo());
		sb.append("', \"");
		sb.append(movimento.getTipoDescr());
		sb.append("\", \"");
		sb.append(String.valueOf(movimento.getValor()));
		sb.append("\", \"");
		sb.append(movimento.getDescricao());
		sb.append("\", ");
		
		if(movimento.getContaReferencia() != null) {
			sb.append("\"");
			sb.append(movimento.getContaReferencia().getCliente().getAgencia().getAgenciaID());
			sb.append("\", \"");
			sb.append(movimento.getContaReferencia().getNumeroConta());
			sb.append("\" ");
		} else {
			sb.append("null, null ");
		}		
		sb.append(")");
		
		String cmdSql = sb.toString();
		createMovimento(cmdSql);

	}

	private void createMovimento(String cmdSql) {
		DbUtilities dbutilities = new DbUtilities();

		dbutilities.ExecuteSqlStatement(cmdSql);

		System.out.println("O registo foi inserido com sucesso.");

		dbutilities.DisconnectFromDB();
	}
	
	
	@Override
	public void alteraMovimento(Movimento movimento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apagaMovimento(int agenciaID, int numeroConta, int movimentoNumero) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Movimento consultaMovimento(int agenciaID, int numeroConta, int movimentoNumero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movimento> listaMovimentosConta(int agenciaID, int numeroConta) {
				String cmdSql = "SELECT * FROM jmpr1525_Banco.movimentos WHERE agencia_id = " +
						agenciaID + " AND numero_conta = " + numeroConta;
				
				return readMovimentos(cmdSql);
	}

	private List<Movimento> readMovimentos(String cmdSql) {
		List<Movimento> movimentos = new ArrayList<Movimento>();
		DbUtilities dbutils = new DbUtilities();
		ResultSet rs = null;

		try {
			rs = dbutils.ReadRecords(cmdSql);
			
			if (rs.next()) { 
				do {
					int agenciaID = rs.getInt(1);
					int numeroConta = rs.getInt(2);
					int numeroMovimento = rs.getInt(3);
					int cartaoID = rs.getInt(4);
					String dataMovimento = rs.getDate(5).toString();
					char tipo = rs.getString(6).charAt(0);
					String tipoDescr = rs.getString(7);
					Double valor = rs.getDouble(8);
					String descricao = rs.getString(9);
					
					int refAgenciaID = rs.getInt(10);
					if (rs.wasNull()) refAgenciaID = -1;
					int refNumeroConta = rs.getInt(11);
					if (rs.wasNull()) refNumeroConta = -1;

					/*
					 * Se o movimento tem um cartao associado, então vai buscar respectivo Cartao -> Conta -> Cliente -> Agencia
			 		 * se o movimento não tiver cartao associado, vai buscar Conta -> Cliente -> Agencia pelos campos
				 	 * agenciaID e numeroConta e cria um "cartao em branco"
					 */
					CartaoDAO cartaoDao = new CartaoDAO();
					ContaDAO contaDao = new ContaDAO();
					Cartao cartao = null;
					if (cartaoID > 0) {
						cartao = cartaoDao.consultaCartao(cartaoID);
					} else {
						Conta conta = contaDao.consultaConta(agenciaID, numeroConta);
						cartao = new Cartao(0, "", "", "1900-01-01", LocalDate.MAX.toString(), 'D', conta);
					}
					
					Conta contaReferencia = null;
					if (refAgenciaID > 0 && refNumeroConta > 0) {
						contaReferencia = contaDao.consultaConta(refAgenciaID, refNumeroConta);
					}
					
					Movimento movimento = new Movimento(numeroMovimento, dataMovimento, valor, tipo, tipoDescr, descricao, cartao, contaReferencia);
					movimentos.add(movimento);
				} while (rs.next());
			}  else {
				System.out.println("Nenhum registo encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu o erro: " + e.getMessage());
		} finally {
			dbutils.DisconnectFromDB();
		}

		return movimentos;
	}
	
}
