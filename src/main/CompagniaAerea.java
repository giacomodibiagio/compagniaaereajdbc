package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import main.dao.*;
import main.model.*;

public class CompagniaAerea {
	private String nomeCompagnia;
	private static CompagniaAerea istanza;
	
	private CompagniaAerea() {}
	
	public static CompagniaAerea getIstance() {
		if(istanza == null)
			istanza = new CompagniaAerea();
	
		return istanza;		
	}

	public String getNomeCompagnia() {
		return nomeCompagnia;
	}

	public void setNomeCompagnia(String nomeCompagnia) {
		this.nomeCompagnia = nomeCompagnia;
	}
	
	public void assumerePilota(Pilota p) {
		PilotaDao pilotaDao = new PilotaDaoImpl();
		
		pilotaDao.insert(p);
	}
	
	public void acquistaAereo(Aereo a) {
		AereoDao aereoDao = new AereoDaoImpl();

		aereoDao.insert(a);
	}
	
	public void registraNuovoPasseggero(Passeggero p) {
		PasseggeroDao passeggeroDao = new PasseggeroDaoImpl();
		
		passeggeroDao.insert(p);
	}
	
	public void organizzareNuovoVolo(Volo v) {
		VoloDao voloDao = new VoloDaoImpl();
			
		
		voloDao.insert(v);		
	}
	
	public void modificaStatoAereo(Aereo a) {
		AereoDao aereoDao = new AereoDaoImpl();		
		aereoDao.getAll();		

		a = aereoDao.get(a.getIdAereo());
		
		
		aereoDao.update(a);
	}
	
	public void modificaStatoPilota(Pilota p) {
		PilotaDao pilotaDao = new PilotaDaoImpl();
		pilotaDao.getAll();
			
		
		
		pilotaDao.update(p);		
	}	
	
	public void modificaDatiVolo(int idV) {
		VoloDao voloDao = new VoloDaoImpl();
		voloDao.getAll();
		
		Volo v = voloDao.get(v.getIdVolo());

		v.setIdAereo(4);
		v.setIdAereoportoArrivo(3);
		v.setIdAereoportoPartenza(2);

		
		voloDao.update(v);		
	}
	
	public void getAereiInVolo(Date d1) {
		
		VoloDaoImpl voloDao = new VoloDaoImpl();
		
		voloDao.getInVolo(d1);		
	}
	
	public void getGuadagnoCompagnia() {
		String query = "SELECT SUM(TEMP.PREZZO) - SUM(TEMP.STIPENDIO) AS GUADAGNO_COMPAGNIA "
				+ "FROM ( "
				+ "		SELECT SUM(B.PREZZO) AS PREZZO, P.STIPENDIO, V.ID_VOLO "
				+ "		FROM BIGLIETTO b "
				+ "        LEFT JOIN VOLO V ON B.ID_VOLO = V.ID_VOLO "
				+ "        JOIN PILOTA P ON V.ID_PILOTA = P.ID_PILOTA "
				+ "        WHERE CANCELLATO = 0 "
				+ "        GROUP BY V.ID_VOLO "
				+ "        ) TEMP;";
		
		Integer i = null;
		String url = "jdbc:mysql://localhost:3306/compagnia_aerea?serverTimezone=UTC&useSSL=false";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, "root","root");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try (				
				PreparedStatement statement = connection.prepareStatement(query);	
			){
				// eseguo query						
				ResultSet result = statement.executeQuery();
				
				if(result.next()) 
					i = result.getInt("GUADAGNO_COMPAGNIA");				
				

				System.out.println(i);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	
	
}
