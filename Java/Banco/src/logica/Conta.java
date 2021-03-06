package logica;

public class Conta {
	private int numeroConta;
	private Cliente cliente;
	private String tipo;
	private String descricao;
	private String dataCriacao;
	private double saldo;
	private int ultimoMovimento;
	
	public Conta(int numeroConta, Cliente cliente, String tipo, String descricao, String dataCriacao, double saldo, int ultimoMovimento) {
		this.numeroConta = numeroConta;
		this.cliente = cliente;
		this.tipo = tipo;
		this.descricao = descricao;
		this.dataCriacao = dataCriacao;
		this.saldo = saldo;
		this.ultimoMovimento = ultimoMovimento;
	}
	
	
	public int getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public int getUtimoMovimento() {
		return ultimoMovimento;
	}
	public void setUltimoMovimento(int ultimoMovimento) {
		this.ultimoMovimento = ultimoMovimento;
	}

	
	public double getSaldo() {
		return saldo;
	}

	protected void atualizaSaldo(double valor) {
		this.saldo += valor;
	}

	@Override
	public String toString() {
		return "Conta [numeroConta=" + numeroConta + ", " + cliente.toString() + ", tipo=" + tipo + ", dataCriacao="
				+ dataCriacao + ", saldo=" + saldo + ", ultimoMovimento=" + ultimoMovimento + "]";
	}
	
	

}