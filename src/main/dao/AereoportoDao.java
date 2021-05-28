package main.dao;

import java.util.Date;

import main.model.Aereoporto;

public interface AereoportoDao extends Dao<Aereoporto> {

	public void getAereoportoMaxPartiti(Date d);
}
