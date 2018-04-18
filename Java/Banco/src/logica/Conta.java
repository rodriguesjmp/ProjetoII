package logica;

public class Conta {
	private int contaID;
	private Cliente cliente;
	private String tipo;
	private String dataCriacao;
	private double saldo;
	
	public Conta(int contaID, Cliente cliente, String tipo, String dataCriacao, double saldo) {
		this.contaID = contaID;
		this.cliente = cliente;
		this.tipo = tipo;
		this.dataCriacao = dataCriacao;
		this.saldo = saldo;
	}
	
	
	public int getContaID() {
		return contaID;
	}
	public void setContaID(int contaID) {
		this.contaID = contaID;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public double getSaldo() {
		return saldo;
	}

}