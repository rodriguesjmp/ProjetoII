package logica;

public class ContaAPrazo extends ContaPoupanca {
	private int prazoAnos;

	public ContaAPrazo(int numeroConta, Cliente cliente, String tipo, String descricao, String dataCriacao, double saldo, int ultimoMovimento, int prazoAnos) {
		super(numeroConta, cliente, tipo, descricao, dataCriacao, saldo, ultimoMovimento);
		this.setTaxaRemuneracao(0.1);
		this.prazoAnos = prazoAnos;
	}

	
	public int getPrazoAnos() {
		return prazoAnos;
	}

	public void setPrazoAnos(int prazoAnos) {
		this.prazoAnos = prazoAnos;
	}
	
}