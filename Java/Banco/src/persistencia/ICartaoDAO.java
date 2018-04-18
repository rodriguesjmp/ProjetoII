package persistencia;

import logica.Cartao;

public interface ICartaoDAO {
	public Cartao consultaCartao(int cartaoID);
	public void insereCartao(Cartao cartao);
}