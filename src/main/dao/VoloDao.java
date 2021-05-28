package main.dao;

import java.util.Date;

import main.model.Volo;

public interface VoloDao extends Dao<Volo> {
	
	public void countPassFrom(Volo v);
	public void getInVolo(Date d);
	public void getVoliNegativi(Date d);
	public void getPasseggeriInVolo(int idVolo);
	public void getPasseggeriPrevisti(int idAereoPortoArrivo, Date d);
}
