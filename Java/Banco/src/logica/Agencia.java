package logica;

public class Agencia {
	private int agenciaID;
	private String nome;
	private String morada;
	private String telefone;
	private int ultimoClienteID;
	
	public Agencia(int agenciaID, String nome, String morada, String telefone, int ultimoClienteID) {
		this.agenciaID = agenciaID;
		this.nome = nome;
		this.morada = morada;
		this.telefone = telefone;
		this.ultimoClienteID = ultimoClienteID;
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
	public int getUltimoClienteID() {
		return ultimoClienteID;
	}
	public void setUltimoClienteID(int ultimoClienteID) {
		this.ultimoClienteID = ultimoClienteID;
	}

	@Override
	public String toString() {
		return "Agencia [agenciaID=" + agenciaID + ", nome=" + nome + ", morada=" + morada + ", telefone=" + telefone
				+ ", ultimoClienteID=" + ultimoClienteID + "]";
	}
	
}