package persistencia;

import java.util.List;

import logica.Cartao;
import logica.CartaoCredito;

public interface ICartaoDAO {
	public Cartao consultaCartao(int cartaoID);
	public int insereCartao(Cartao cartao);
	public int insereCartao(CartaoCredito cartaoCredito);
	public void alteraCartao(Cartao cartao);
	public void apagaCartao(int cartaoID);
	public List<Cartao> listaCartoes();
	public List<Cartao> listaCartoes(int agenciaID, int numeroConta);
}