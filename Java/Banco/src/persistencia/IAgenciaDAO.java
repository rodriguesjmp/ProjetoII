package persistencia;

import java.util.List;

import logica.Agencia;

public interface IAgenciaDAO {
	public List<Agencia> listarAgencias();
	public Agencia consultaAgencia(int agencia_id);
	public void alteraAgencia();
	public void apagaAgencia();
	public void insereAgencia(Agencia agencia);

}