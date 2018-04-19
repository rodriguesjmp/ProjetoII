package logica;

public class Cliente {
	private int numeroCliente;
	private Agencia agencia;
	private String tipo;
	private String nome;
	private String cartaoCidadao;
	private String morada;
	private String telefone;
	private String email;
	private String profissao;
	
	public Cliente(int numeroCliente, Agencia agencia, String tipo, String nome, String cartaoCidadao, String morada,
			String telefone, String email, String profissao) {
		this.numeroCliente = numeroCliente;
		this.agencia = agencia;
		this.tipo = tipo;
		this.nome = nome;
		this.cartaoCidadao = cartaoCidadao;
		this.morada = morada;
		this.telefone = telefone;
		this.email = email;
		this.profissao = profissao;
	}
	
	
	public int getNumeroCliente() {
		return numeroCliente;
	}
	public void setNumeroCliente(int numeroCliente) {
		this.numeroCliente = numeroCliente;
	}
	public Agencia getAgencia() {
		return agencia;
	}
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCartaoCidadao() {
		return cartaoCidadao;
	}
	public void setCartaoCidadao(String cartaoCidadao) {
		this.cartaoCidadao = cartaoCidadao;
	}
	public String getMorada() {
		return morada;
	}
	public void setMorada(String morada) {
		this.morada = morada;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	
	
}