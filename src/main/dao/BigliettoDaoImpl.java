package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.model.Biglietto;
import main.model.Passeggero;

public class BigliettoDaoImpl implements BigliettoDao {

	@Override
	public Biglietto get(int id) {
		String query = "SELECT * FROM BIGLIETTO WHERE ID_BIGLIETTO = ?";
		
		// ottenere la connessione
		try (
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query			
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			// ricostruisco il risultato
			Biglietto b = new Biglietto();
			if(result.next()) {
				b.setIdBiglietto(result.getInt("ID_BIGLIETTO"));	
				b.setIdPassegero(result.getInt("ID_PASSEGGERO"));
				b.setIdVolo(result.getInt("ID_VOLO"));
				b.setPrezzo(result.getInt("PREZZO"));

				return b;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Biglietto> getAll() {
		String query = "SELECT * FROM BIGLIETTO";
		List<Biglietto> listaBiglietti= new ArrayList<>();

		try(
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			){
			
			ResultSet result = statement.executeQuery();			
			
			while(result.next()) {
				Biglietto b = new Biglietto();					
				b.setIdBiglietto(result.getInt("ID_BIGLIETTO"));
				b.setIdPassegero(result.getInt("ID_PASSEGGERO"));	
				b.setIdVolo(result.getInt("ID_VOLO"));
				b.setPrezzo(result.getInt("PREZZO"));
				
				listaBiglietti.add(b);
			}				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Biglietto b: listaBiglietti)
			System.out.println(b);
		
		return listaBiglietti;		

	}

	@Override
	public void insert(Biglietto t) {
		String query = "INSERT INTO BIGLIETTO (ID_PASSEGGERO,ID_PASSEGGERO,PREZZO) VALUES (?,?,?);";
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query
			statement.setInt(1,t.getIdPassegero());
			statement.setInt(2, t.getIdVolo());
			statement.setInt(3, getOreVolate(t.getIdVolo()));
				
			statement.execute();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Integer getOreVolate(Integer x) {
		String query = "SELECT ((ORARIO_ARRIVO - ORARIO_PARTENZA)/10000) AS ORE_VOLATE "
				+ "FROM VOLO "
				+ "WHERE ID_VOLO =?;";
		Integer i = 0;
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareCall(query);	
			){
			// eseguo query
			statement.setInt(1,x);				
			ResultSet result = statement.executeQuery();
		
			if(result.next()) 
				i = result.getInt("ORE_VOLATE");			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		System.out.println(i*10);
		return i*10;
	}

	@Override
	public void update(Biglietto t) {
		String query = "UPDATE BIGLIETTO SET ID_PASSEGGERO = ? , SET ID_VOLO = ?, SET PREZZO = ? WHERE ID_BIGLIETTO = ?";			
		
		// ottenere la connessione
		try (
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query			
			
			statement.setInt(1, t.getIdPassegero());
			statement.setInt(2, t.getIdVolo());	
			statement.setInt(3, t.getPrezzo());
			statement.setInt(4, t.getIdBiglietto());
				
			statement.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
		String query = "DELETE FROM BIGLIETTO WHERE ID_BIGLIETTO = ?";
		
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

}
