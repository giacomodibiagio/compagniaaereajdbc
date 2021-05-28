package main.dao;

import main.model.Passeggero;

public interface PasseggeroDao extends Dao<Passeggero> {

	public void getPasseggeroMaxViaggi();
	public void getPasseggeroMin();
	public void getPasseggeroMax();
}
