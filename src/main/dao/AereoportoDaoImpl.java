package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import main.model.Aereo;
import main.model.Aereoporto;
import main.model.Passeggero;

public class AereoportoDaoImpl implements AereoportoDao{

	@Override
	public Aereoporto get(int id) {
		String query = "SELECT * FROM AEREOPORTO WHERE ID_AEREOPORTO = ?";
		
		// ottenere la connessione
		try (
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query			
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			// ricostruisco il risultato
			Aereoporto a = new Aereoporto();
			if(result.next()) {
				a.setIdAereoporto(result.getInt("ID_AEREOPORTO"));
				a.setCitta(result.getString("CITTA"));
				return a;
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return null;
	}

	@Override
	public List<Aereoporto> getAll() {
		String query = "SELECT * FROM AEREOPORTO";
		List<Aereoporto> listaAereoporti= new ArrayList<>();

		try(
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			){
			
			ResultSet result = statement.executeQuery();			
			
			while(result.next()) {
				Aereoporto a = new Aereoporto();					
				a.setIdAereoporto(result.getInt("ID_AEREOPORTO"));				
				a.setCitta(result.getString("CITTA"));	
				
				listaAereoporti.add(a);
			}				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Aereoporto a: listaAereoporti)
			System.out.println(a);
		
		return listaAereoporti;

	}



	@Override
	public void insert(Aereoporto t) {
		String query = "INSERT INTO aereoporto (CITTA) VALUES (?)";
		try (
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareCall(query);	
			){
			// eseguo query
			statement.setString(1, t.getCitta());
		
			statement.execute();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Aereoporto t) {
		String query = "UPDATE AEREOPORTO SET CITTA = ? WHERE ID_AEREOPORTO = ?";			
				
		// ottenere la connessione
		try (
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query			
			
			statement.setString(1, t.getCitta());
			statement.setInt(2, t.getIdAereoporto());		
				
			statement.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
		String query = "DELETE FROM AEREOPORTO WHERE ID_AEREOPORTO = ?";
		
		// ottenere la connessione
		try (
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query				
			
			statement.setInt(1,(Integer) id);
			statement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public void getAereoportoMaxPartiti(Date d) {
		String query = "SELECT ID_AEREOPORTO_PARTENZA, COUNT(VOLO.ID_AEREOPORTO_PARTENZA) as AEREI_PARTITI "
				+ "FROM VOLO "
				+ "WHERE GIORNO_DEL_VOLO = ? "
				+ "GROUP BY ID_AEREOPORTO_PARTENZA "
				+ "ORDER BY AEREI_PARTITI DESC "
				+ "LIMIT 1;";
		Integer i = null;
		Integer aereiPartiti = null;
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareCall(query);	
				){
				// eseguo query
				System.out.println(d);
				
				String pattern = "yyyy-MM-dd";
				SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			
				String todayAsString = formatter.format(d);
				
				statement.setString(1, todayAsString);
			
				System.out.println(todayAsString);
				ResultSet result = statement.executeQuery();
				
				if(result.next()) {
					i = result.getInt("ID_AEREOPORTO_PARTENZA");
					aereiPartiti = result.getInt("AEREI_PARTITI");
					System.out.println("id Aereoporto partenza: "+ i + " totale aerei partiti: " + aereiPartiti);
				}
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
