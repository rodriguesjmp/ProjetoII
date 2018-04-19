package persistencia;

import java.util.List;

import logica.Cliente;

public interface IClienteDAO {
	public List<Cliente> listarClientes();
	public Cliente consultaCliente(int agenciaID, int numeroCliente);
	public void insereCliente(Cliente cliente);
	public void alteraCliente(Cliente cliente);
	public void apagaCliente(int agenciaID, int numeroCliente);
}