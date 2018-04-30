package persistencia;

import java.util.List;

import logica.Movimento;

public interface IMovimentoDAO {
	public void insereMovimento(Movimento movimento);
	public void apagaMovimento(int agenciaID, int numeroConta, int movimentoNumero);
	public Movimento consultaMovimento(int agenciaID, int numeroConta, int movimentoNumero);
	public List<Movimento> listaMovimentosConta(int agenciaID, int numeroConta);
}
