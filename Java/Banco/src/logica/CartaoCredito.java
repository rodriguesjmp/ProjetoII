package logica;

public class CartaoCredito extends Cartao {
	private String dataLimitePagamento;
	private int diaInicioExtrato;
	private double plafondMensal;
	private double plafondDisponivel;
	
	public CartaoCredito(int cartaoID, String descricao, String dataCriacao, String dataValidade, char tipo, Conta conta, String dataLimitePagamento, int diaInicioExtrato, double plafondMensal, double plafond_disponivel) {
		super(cartaoID, descricao, dataCriacao, dataValidade, tipo, conta);
		this.dataLimitePagamento = dataLimitePagamento;
		this.diaInicioExtrato = diaInicioExtrato;
		this.plafondMensal = plafondMensal;
		this.plafondDisponivel = plafond_disponivel;
	}

	public String getDataLimitePagamento() {
		return dataLimitePagamento;
	}

	public void setDataLimitePagamento(String dataLimitePagamento) {
		this.dataLimitePagamento = dataLimitePagamento;
	}

	public int getDiaInicioExtrato() {
		return diaInicioExtrato;
	}

	public void setDiaInicioExtrato(int diaInicioExtrato) {
		this.diaInicioExtrato = diaInicioExtrato;
	}

	public double getPlafondMensal() {
		return plafondMensal;
	}

	public void setPlafondMensal(double plafondMensal) {
		this.plafondMensal = plafondMensal;
	}

	public double getPlafondDisponivel() {
		return plafondDisponivel;
	}

	public void setPlafondDisponivel(double plafondDisponivel) {
		this.plafondDisponivel = plafondDisponivel;
	}
	
	public double saldoDisponivel() {
		return super.saldoDisponivel() + this.getPlafondDisponivel();
	}
	

}