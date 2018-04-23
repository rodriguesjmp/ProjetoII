package logica;

public class ContaPoupanca extends Conta {
	private double taxaRemuneracao; //5%
	private int periocidadeJuros;	//365dias

	public ContaPoupanca(int numeroConta, Cliente cliente, String tipo, String descricao, String dataCriacao, double saldo, int ultimoMovimento) {
		super(numeroConta, cliente, tipo, descricao, dataCriacao, saldo, ultimoMovimento);
		this.taxaRemuneracao = 0.05;
		this.periocidadeJuros = 365;
	}
	
	public double getTaxaRemuneracao() {
		return taxaRemuneracao;
	}
	public int getPeriocidadeJuros() {
		return periocidadeJuros;
	}

	protected void setTaxaRemuneracao(double taxaRemuneracao) {
		this.taxaRemuneracao = taxaRemuneracao;
	}
}