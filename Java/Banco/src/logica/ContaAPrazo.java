package logica;

public class ContaAPrazo extends ContaPoupanca {
	private int prazoDias;

	public ContaAPrazo(int numeroConta, Cliente cliente, String tipo, String dataCriacao, double saldo) {
		super(numeroConta, cliente, tipo, dataCriacao, saldo);
		// TODO Auto-generated constructor stub
	}

	
	public int getPrazoDias() {
		return prazoDias;
	}

	public void setPrazoDias(int prazoDias) {
		this.prazoDias = prazoDias;
	}
	
}