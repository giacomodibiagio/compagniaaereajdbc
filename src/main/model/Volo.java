package main.model;

import java.util.Date;

public class Volo {
	
	private Integer idVolo;
	private Date orarioPartenza;
	private Date orarioArrivo;
	private Date giornoDelVolo;	
	private Integer idAereo;
	private Integer idPilota;
	private Integer idAereoportoPartenza;
	private Integer idAereoportoArrivo;
	private Integer cancellato;
	
	public Integer getCancellato() {
		return cancellato;
	}
	public void setCancellato(Integer cancellato) {
		this.cancellato = cancellato;
	}
	public Integer getIdVolo() {
		return idVolo;
	}
	public void setIdVolo(Integer idVolo) {
		this.idVolo = idVolo;
	}
	public Date getOrarioPartenza() {
		return orarioPartenza;
	}
	public void setOrarioPartenza(Date orarioPartenza) {
		this.orarioPartenza = orarioPartenza;
	}
	public Date getOrarioArrivo() {
		return orarioArrivo;
	}
	public void setOrarioArrivo(Date orarioArrivo) {
		this.orarioArrivo = orarioArrivo;
	}
	public Date getGiornoDelVolo() {
		return giornoDelVolo;
	}
	public void setGiornoDelVolo(Date giornoDelVolo) {
		this.giornoDelVolo = giornoDelVolo;
	}
	public Integer getIdAereo() {
		return idAereo;
	}
	public void setIdAereo(Integer idAereo) {
		this.idAereo = idAereo;
	}
	public Integer getIdPilota() {
		return idPilota;
	}
	public void setIdPilota(Integer idPilota) {
		this.idPilota = idPilota;
	}
	public Integer getIdAereoportoPartenza() {
		return idAereoportoPartenza;
	}
	public void setIdAereoportoPartenza(Integer idAereoportoPartenza) {
		this.idAereoportoPartenza = idAereoportoPartenza;
	}
	public Integer getIdAereoportoArrivo() {
		return idAereoportoArrivo;
	}
	public void setIdAereoportoArrivo(Integer idAereoportoArrivo) {
		this.idAereoportoArrivo = idAereoportoArrivo;
	}
	
	
	@Override
	public String toString() {
		return "Volo idVolo=" + idVolo + "\norarioPartenza=" + orarioPartenza + "\norarioArrivo=" + orarioArrivo
				+ "\ngiornoDelVolo=" + giornoDelVolo + "\nidAereo=" + idAereo + "\nidPilota=" + idPilota
				+ "\nidAereoportoPartenza=" + idAereoportoPartenza + "\nidAereoportoArrivo=" + idAereoportoArrivo + "\n\n";
	}
	
}
