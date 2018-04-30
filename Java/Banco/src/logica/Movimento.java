package logica;

public class Movimento {
	private int numeroMovimento;
	private String dataMovimento;
	private double valor;
	private char tipo;
	private String tipoDescr;
	private String descricao;
	private Cartao cartao;
	private Conta contaReferencia;
	private int refNumeroMovimento;
	
	public Movimento(int numeroMovimento, String dataMovimento, double valor, char tipo, String tipoDescr, String descricao,
			Cartao cartao, Conta contaReferencia, int refNumeroMovimento) {
		this.numeroMovimento = numeroMovimento;
		this.dataMovimento = dataMovimento;
		this.valor = valor;
		this.tipo = tipo;
		this.tipoDescr = tipoDescr;
		this.descricao = descricao;
		this.cartao = cartao;
		this.contaReferencia = contaReferencia;
		this.refNumeroMovimento = refNumeroMovimento;
	}
	public int getNumeroMovimento() {
		return numeroMovimento;
	}
	public void setNumeroMovimento(int numeroMovimento) {
		this.numeroMovimento = numeroMovimento;
	}
	public Cartao getCartao() {
		return cartao;
	}
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	public String getDataMovimento() {
		return dataMovimento;
	}
	public void setDataMovimento(String dataMovimento) {
		this.dataMovimento = dataMovimento;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public char getTipo() {
		return tipo;
	}
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	public String getTipoDescr() {
		return tipoDescr;
	}
	public void setTipoDescr(String tipoDescr) {
		this.tipoDescr = tipoDescr;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Conta getContaReferencia() {
		return contaReferencia;
	}
	public void setContaReferencia(Conta contaReferencia) {
		this.contaReferencia = contaReferencia;
	}
	public int getRefNumeroMovimento() {
		return refNumeroMovimento;
	}
	public void setRefNumeroMovimento(int refNumeroMovimento) {
		this.refNumeroMovimento = refNumeroMovimento;
	}
	@Override
	public String toString() {
		return "Movimento [numeroMovimento=" + numeroMovimento + ", dataMovimento=" + dataMovimento + ", valor=" + valor
				+ ", tipo=" + tipo + ", tipoDescr=" + tipoDescr + ", descricao=" + descricao + ", cartao=" + cartao.toString()
				+ ", contaDestino=" + contaReferencia.toString() + ", refNumeroMovimento=" + refNumeroMovimento + "]";
	}
	
}
