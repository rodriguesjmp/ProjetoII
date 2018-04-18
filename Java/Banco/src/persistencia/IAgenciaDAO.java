package persistencia;

import java.util.List;

import logica.Agencia;

public interface IAgenciaDAO {
	public List<Agencia> listarAgencias();
	public Agencia consultaAgencia(int agenciaID);
	public void insereAgencia(Agencia agencia);
	public void alteraAgencia(Agencia agencia);
	public void apagaAgencia(int agenciaID);
	public void atualizaUltimaConta(int agenciaID, int ultimaConta);
}