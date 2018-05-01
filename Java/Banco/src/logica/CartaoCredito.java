package logica;

public class CartaoCredito extends Cartao {
	private int diasPrazoPagamento;
	private int diaInicioExtrato;
	private double plafondMensal;
	private double plafondDisponivel;
	
	public CartaoCredito(int cartaoID, String descricao,  String nomeNoCartao, String dataCriacao, String dataValidade, char tipo, Conta conta, 
			int diasPrazoPagamento, int diaInicioExtrato, double plafondMensal, double plafond_disponivel) {
		
		super(cartaoID, descricao, nomeNoCartao, dataCriacao, dataValidade, tipo, conta);
		
		if (diasPrazoPagamento <= 0) {
			this.diasPrazoPagamento = 10;
		} else {
			this.diasPrazoPagamento = diasPrazoPagamento;
		}
		this.diaInicioExtrato = diaInicioExtrato;
		if (plafondMensal == 0.0) {
			this.plafondMensal = 500.0;
			this.plafondDisponivel = 500.0;
		} else {
			this.plafondMensal = plafondMensal;
			this.plafondDisponivel = plafond_disponivel;
		}
		
	}

	public int getDiasPrazoPagamento() {
		return diasPrazoPagamento;
	}

	public void setdiasPrazoPagamento(int diasPrazoPagamento) {
		this.diasPrazoPagamento = diasPrazoPagamento;
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