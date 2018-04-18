package logica;

public class ContaPoupanca extends Conta {
	private double taxaRemuneracao; //5%
	private int periocidadeJuros;	//365dias

	public ContaPoupanca(int contaID, Cliente cliente, String tipo, String dataCriacao, double saldo) {
		super(contaID, cliente, tipo, dataCriacao, saldo);
		// TODO Auto-generated constructor stub
	}
	
	public double getTaxaRemuneracao() {
		return taxaRemuneracao;
	}
	public int getPeriocidadeJuros() {
		return periocidadeJuros;
	}

}