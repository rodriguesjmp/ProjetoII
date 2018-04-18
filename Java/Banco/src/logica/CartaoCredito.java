package logica;

public class CartaoCredito extends Cartao {
	private String dataLimitePagamento;
	private String periodoExtrato;
	private double plafondMensal;
	private double plafondDisponivel;
	
	public CartaoCredito(int cartaoID, String descricao, String dataCriacao, char tipo, Conta conta) {
		super(cartaoID, descricao, dataCriacao, tipo, conta);
		// TODO Auto-generated constructor stub
	}

	public String getDataLimitePagamento() {
		return dataLimitePagamento;
	}

	public void setDataLimitePagamento(String dataLimitePagamento) {
		this.dataLimitePagamento = dataLimitePagamento;
	}

	public String getPeriodoExtrato() {
		return periodoExtrato;
	}

	public void setPeriodoExtrato(String periodoExtrato) {
		this.periodoExtrato = periodoExtrato;
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
}