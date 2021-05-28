package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.model.Aereo;
import main.model.Passeggero;
import main.model.Pilota;
import main.model.Volo;

public class PilotaDaoImpl implements PilotaDao{

	@Override
	public Pilota get(int id) {
		String query = "SELECT * FROM PILOTA WHERE ID_PILOTA = ?;";

		// ottenere la connessione
		try (
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query			
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			// ricostruisco il risultato
			Pilota p = new Pilota();
			if(result.next()) {
				p.setIdPilota(result.getInt("ID_PILOTA"));
				p.setIdStatoPilota(result.getInt("ID_STATO_PILOTA"));
				p.setNome(result.getString("NOME"));
				p.setCognome(result.getString("COGNOME"));
				return p;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return null;
	}

	@Override
	public List<Pilota> getAll() {
		String query = "SELECT * FROM PILOTA";
		List<Pilota> listaPiloti = new ArrayList<>();

		try(
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			){
			
			ResultSet result = statement.executeQuery();			
			
			while(result.next()) {
				Pilota p = new Pilota();					
				p.setIdPilota(result.getInt("ID_PILOTA"));
				p.setStipendio(result.getInt("STIPENDIO"));
				p.setNome(result.getString("NOME"));
				p.setCognome(result.getString("COGNOME"));
				p.setIdStatoPilota(result.getInt("ID_STATO_PILOTA"));
				
				listaPiloti.add(p);
			}				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Pilota p: listaPiloti)
			System.out.println(p);
		
		return listaPiloti;
	}

	@Override
	public void insert(Pilota t) {
		String query = "INSERT INTO pilota (STIPENDIO,NOME,COGNOME,ID_STATO_PILOTA) "
				+ "VALUES (?,?,?,?);";
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query
			statement.setInt(1, t.getStipendio());
			statement.setString(2, t.getNome());
			statement.setString(3, t.getCognome());
			statement.setInt(4, t.getIdStatoPilota());
				
			statement.execute();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Pilota t) {
		String query = "UPDATE PILOTA SET ID_STATO_PILOTA = ? WHERE ID_PILOTA = ?";
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Inserisci il nuovo stato del pilota (1->Operativo,2->Licenziato,3->Sospeso): ");
		int nuovoStato = scan.nextInt();
		
		// ottenere la connessione
		try (
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query			
			
			statement.setInt(1, nuovoStato);
			statement.setInt(2, t.getIdPilota());		
				
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
	
	public void countPiloti() {
		String query = "SELECT COUNT(id_pilota) AS PILOTI_ATTIVI "
				   	 + "FROM pilota "
					 + "WHERE id_stato_pilota = 1;";
		Integer i = null;
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query				
			
			ResultSet result = statement.executeQuery();
			if(result.next()) 
				i = result.getInt("PILOTI_ATTIVI");
			
			System.out.println(i);
			
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	
	public void getPilotaMaxOre() {
		String query = "SELECT ID_PILOTA,MAX(ORE) ORE_TOTALI "
				+ "	FROM ( "
				+ "			SELECT ID_PILOTA,SUM((orario_arrivo - orario_partenza)/10000) AS ORE "
				+ "            FROM VOLO  "
				+ "            GROUP BY ID_PILOTA "
				+ "            ORDER BY ORE DESC "
				+ "		) VOLI; ";
		
		Integer i = null;
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query				
			
			ResultSet result = statement.executeQuery();
			if(result.next()) 
				i = result.getInt("ID_PILOTA");
			
			PilotaDaoImpl p = new PilotaDaoImpl();
			Pilota pa = new Pilota();
			pa = p.get(i);
			
			System.out.println(pa.getNome()+" "+pa.getCognome());
			
			System.out.println(i);
			
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	
	public void aggiornaStipendio() {
		String query = "SELECT * FROM VOLO;";
		
		try(
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			){
				
			ResultSet result = statement.executeQuery();			
				
			while(result.next()) {
				Pilota p = new Pilota();	
				PilotaDaoImpl p3 = new PilotaDaoImpl();
				p = p3.get(result.getInt("ID_PILOTA"));					
				
				if(p.getIdStatoPilota() == 1) {
					PilotaDaoImpl p1 = new PilotaDaoImpl();
					if(p.getStipendio() <= 9000) {
						p.setStipendio(p.getStipendio()+1000);
					}
							
				p3.update(p);
					
				}

			}				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
