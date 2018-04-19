package persistencia;

import java.util.List;

import logica.Cartao;

public interface ICartaoDAO {
	public Cartao consultaCartao(int cartaoID);
	public void insereCartao(Cartao cartao);
	public void alteraCartao(Cartao cartao);
	public void apagaCartao(int cartaoID);
	public List<Cartao> listaCartoesCliente(int agenciaID, int numeroCliente);
}