package logica;

public class ContaPoupanca extends Conta {
	private double taxaRemuneracao; //5%
	private int periocidadeJuros;	//365dias
	
	public double getTaxaRemuneracao() {
		return taxaRemuneracao;
	}
	public int getPeriocidadeJuros() {
		return periocidadeJuros;
	}

}