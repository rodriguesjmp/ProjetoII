package persistencia;

import java.util.List;

import logica.Cartao;
import logica.CartaoCredito;

public interface ICartaoDAO {
	public Cartao consultaCartao(int cartaoID);
	public int consultaCartao(int agenciaID, int numeroConta, char tipo);
	public void insereCartao(Cartao cartao);
	public void insereCartao(CartaoCredito cartaoCredito);
	public void alteraCartao(Cartao cartao);
	public void apagaCartao(int cartaoID);
	public List<Cartao> listaCartoesCliente(int agenciaID, int numeroCliente);
}