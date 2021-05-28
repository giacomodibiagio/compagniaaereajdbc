package main.model;

public class Pilota {
	
	private Integer idPilota;
	private Integer stipendio;
	private String nome;
	private String cognome;
	private Integer idStatoPilota;
	
	public Integer getIdPilota() {
		return idPilota;
	}
	public void setIdPilota(Integer idPilota) {
		this.idPilota = idPilota;
	}
	public Integer getStipendio() {
		return stipendio;
	}
	public void setStipendio(Integer stipendio) {
		this.stipendio = stipendio;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Integer getIdStatoPilota() {
		return idStatoPilota;
	}
	public void setIdStatoPilota(Integer idStatoPilota) {
		this.idStatoPilota = idStatoPilota;
	}
	@Override
	public String toString() {
		return "Pilota [idPilota=" + idPilota + ", stipendio=" + stipendio + ", nome=" + nome + ", cognome=" + cognome
				+ ", idStatoPilota=" + idStatoPilota + "]";
	}
	
	
		
}
