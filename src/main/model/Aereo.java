package main.model;

public class Aereo {

	private Integer idAereo;
	private Integer idStatoAereo;
	

	public Integer getIdAereo() {
		return idAereo;
	}
	public void setIdAereo(Integer idAereo) {
		this.idAereo = idAereo;
	}
	public Integer getIdStatoAereo() {
		return idStatoAereo;
	}
	public void setIdStatoAereo(Integer idStatoAereo) {
		this.idStatoAereo = idStatoAereo;
	}
	
	@Override
	public String toString() {
		return "Aereo idAereo=" + idAereo + ", idStatoAereo=" + idStatoAereo;
	}
	
}
