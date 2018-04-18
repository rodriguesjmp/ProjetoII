package logica;

public class Agencia {
	private int agenciaID;
	private String nome;
	private String morada;
	private String telefone;
	private int ultimaConta;

	public Agencia(int agenciaID, String nome, String morada, String telefone, int ultimaConta) {
		this.agenciaID = agenciaID;
		this.nome = nome;
		this.morada = morada;
		this.telefone = telefone;
		this.ultimaConta = ultimaConta;
	}
	
	public int getAgenciaID() {
		return agenciaID;
	}
	public void setAgenciaID(int agenciaID) {
		this.agenciaID = agenciaID;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	
	
	public int getUltimaConta() {
		return ultimaConta;
	}

	public int incrementaUltimaConta() {
		ultimaConta++;
		return ultimaConta;
	}

	@Override
	public String toString() {
		return "Agencia [agenciaID=" + agenciaID + ", nome=" + nome + ", morada=" + morada + ", telefone=" + telefone +
				", ultimaConta=" + ultimaConta + "]";
	}
	
}