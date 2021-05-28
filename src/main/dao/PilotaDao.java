package main.dao;

import main.model.Pilota;

public interface PilotaDao extends Dao<Pilota> {

	public void aggiornaStipendio();
	public void getPilotaMaxOre();
	public void countPiloti();
}
