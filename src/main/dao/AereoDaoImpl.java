package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import main.model.Aereo;
import main.model.Dipendente;

public class AereoDaoImpl implements AereoDao {

	@Override
	public Aereo get(int id) {
		String query = "SELECT * FROM AEREO WHERE ID_AEREO = ?";
		
		// ottenere la connessione
		try (
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query			
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			// ricostruisco il risultato
			Aereo aereo = new Aereo();
			if(result.next()) {
				aereo.setIdAereo(result.getInt("ID_AEREO"));
				aereo.setIdStatoAereo(result.getInt("ID_STATO_AEREO"));
				return aereo;
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Aereo> getAll() {
		String query = "SELECT * FROM AEREO";
		List<Aereo> listaAerei = new ArrayList<>();

		try(
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			){
			
			ResultSet result = statement.executeQuery();			
			
			while(result.next()) {
				Aereo aereo = new Aereo();					
				aereo.setIdAereo(result.getInt("ID_AEREO"));
				aereo.setIdStatoAereo(result.getInt("ID_STATO_AEREO"));
				
				listaAerei.add(aereo);
			}				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Aereo a: listaAerei)
			System.out.println(a);
		
		return listaAerei;
	}

	@Override
	public void insert(Aereo a) {
		String query = "INSERT INTO aereo (ID_STATO_AEREO) VALUES (?);";
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareCall(query);	
				){
				// eseguo query						
				statement.setInt(1,a.getIdStatoAereo());
					
				statement.execute();
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		}		


	@Override
	public void update(Aereo t) {
		String query = "UPDATE AEREO SET ID_STATO_AEREO = ? WHERE ID_AEREO = ?";
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Inserisci il nuovo stato dell'aereo (1->Manutenzione,2->Dismesso,3->Attivo): ");
		int nuovoStato = scan.nextInt();
		
		// ottenere la connessione
		try (
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query			
			
			statement.setInt(1, nuovoStato);
			statement.setInt(2, t.getIdAereo());		
				
			statement.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public void getDoveAtterrato(int idAereo, Date d1, Date d2) {
		String query = "SELECT ID_AEREOPORTO_ARRIVO "
				+ "FROM VOLO "
				+ "JOIN AEREOPORTO ON ID_AEREOPORTO_ARRIVO = AEREOPORTO.ID_AEREOPORTO "
				+ "WHERE GIORNO_DEL_VOLO >= ? AND GIORNO_DEL_VOLO <= ? "
				+ "AND ID_AEREO = ?;";	
		Integer i = null;
		// ottenere la connessione
		try (
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query			
			
			statement.setTimestamp(1, new Timestamp(d1.getTime()));
			statement.setTimestamp(2, new Timestamp(d2.getTime()));
			statement.setInt(3, idAereo);					
				
			ResultSet result = statement.executeQuery();
			
			while(result.next()) { 
				i = result.getInt("ID_AEREOPORTO_ARRIVO");
				System.out.println(i);
			}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}

}
