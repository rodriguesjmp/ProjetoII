package persistencia;

import java.util.List;

import logica.Conta;
import logica.ContaAPrazo;
import logica.ContaPoupanca;

public interface IContaDAO {
	public List<Conta> listarContas();
	public List<Conta> listarContas(int agenciaID, int numeroCliente);
	public Conta consultaConta(int agenciaID, int numeroConta);
	public Conta consultaConta(int agenciaID, int numeroConta, String tipo);
	public void insereConta(Conta conta);
	public void insereConta(ContaAPrazo contaPrazo);
	public void insereConta(ContaPoupanca contaPoupanca);
	public void alteraConta(Conta conta);
	public void alteraConta(ContaAPrazo contaPrazo);
	public void alteraConta(ContaPoupanca contaPoupanca);
	public void apagaConta(int agenciaID, int numeroConta);
	public void atualizaSaldoUltimoMovimento(int agenciaID, int numeroConta, double saldo, int ultimoMovimento);
}