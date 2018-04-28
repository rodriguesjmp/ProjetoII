package logica;

public class Cartao {
	private int cartaoID;
	private String descricao;
	private String nomeNoCartao;
	private String dataCriacao;
	private String dataValidade;
	private char tipo;
	private Conta conta;
	
	public Cartao(int cartaoID, String descricao,String nomeNoCartao, String dataCriacao, String dataValidade, char tipo, Conta conta) {
		this.cartaoID = cartaoID;
		if(descricao.isEmpty()) {
			if (tipo == 'D') {
				this.descricao = "CARTÃO DÉBITO";
			} else {
				this.descricao = "CARTÃO CRÉDITO";
			}
		} else {
			this.descricao = descricao;
		}
		this.nomeNoCartao = nomeNoCartao.toUpperCase();
		this.dataCriacao = dataCriacao;
		this.dataValidade = dataValidade;
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
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
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
	public String getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
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

	public double saldoDisponivel() {
		return conta.getSaldo();
	}
	
	public void deposita(double valor) {
		this.conta.atualizaSaldo(valor);
	}
	
	public boolean levanta(double valor) {
		boolean isMovementOkay = false;
		
		if(this.conta.getSaldo() < valor) {
			System.out.println("O saldo da conta é insuficiente para a operação desejada!");
		} else {
			deposita(valor * -1);
			isMovementOkay = true;
		}
		
		return isMovementOkay;
	}
	
	public boolean tranfere(Conta contaDestino, double valor) {
		boolean isMovementOkay = false;
		
		if(this.conta.equals(contaDestino)) {
			System.out.println("Não pode transferir dinheiro para a própria conta...!");
		} else {
			isMovementOkay = levanta(valor);
			if (isMovementOkay) {
				contaDestino.atualizaSaldo(valor);
			}
		}
		
		return isMovementOkay;
	}
	
	@Override
	public String toString() {
		return "Cartao [cartaoID=" + cartaoID + ", descricao=" + descricao + ", nomeNoCartao=" + nomeNoCartao + ", dataCriacao=" + dataCriacao + 
				", tipo=" + tipo + ", conta=" + conta.toString() + "]";
	}
}