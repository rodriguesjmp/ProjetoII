package logica;

public class Cartao {
	private int cartaoID;
	private String descricao;
	private String nomeNoCartao;
	private String dataCriacao;
	private char tipo;
	private Conta conta;
	
	public Cartao(int cartaoID, String nomeNoCartao, String dataCriacao, char tipo, Conta conta) {
		this.cartaoID = cartaoID;
		if (tipo == 'D') {
			this.descricao = "CARTÃO DÉBITO";
		} else {
			this.descricao = "CARTÃO CRÉDITO";
		}
		this.nomeNoCartao = nomeNoCartao.toUpperCase();
		this.dataCriacao = dataCriacao;
		this.tipo = tipo;
		this.conta = conta;		
	}
	
	public int getCartaoID() {
		return cartaoID;
	}
	public void setCartaoID(int cartaoID) {
		this.cartaoID = cartaoID;
	}
	public String getDescricao() {
		return descricao;
	}
//	public void setDescricao(String descricao) {
//		this.descricao = descricao;
//	}
	public String getNomeNoCartao() {
		return nomeNoCartao;
	}
	public void setNomeNoCartao(String nomeNoCartao) {
		this.nomeNoCartao = nomeNoCartao;
	}
	public String getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public char getTipo() {
		return tipo;
	}
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Override
	public String toString() {
		return "Cartao [cartaoID=" + cartaoID + ", descricao=" + descricao + ", nomeNoCartao=" + nomeNoCartao + ", dataCriacao=" + dataCriacao + 
				", tipo=" + tipo + ", conta=" + conta.toString() + "]";
	}
}