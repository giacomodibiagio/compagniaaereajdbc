package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

import main.model.Aereo;
import main.model.Aereoporto;
import main.model.Volo;

public class VoloDaoImpl implements VoloDao {

	@Override
	public Volo get(int id) {
		String query = "SELECT * FROM VOLO WHERE ID_VOLO = ?";
		
		// ottenere la connessione
		try (
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query			
			
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			// ricostruisco il risultato
			Volo v = new Volo();
			if(result.next()) {
				v.setIdVolo(result.getInt("ID_VOLO"));	
				v.setOrarioPartenza(result.getTimestamp("ORARIO_PARTENZA"));
				v.setOrarioArrivo(result.getTimestamp("ORARIO_ARRIVO"));
				v.setGiornoDelVolo(result.getDate("GIORNO_DEL_VOLO"));
				v.setIdAereo(result.getInt("ID_AEREO"));
				v.setIdPilota(result.getInt("ID_PILOTA"));
				v.setIdAereoportoPartenza(result.getInt("ID_AEREOPORTO_PARTENZA"));
				v.setIdAereoportoArrivo(result.getInt("ID_AEREOPORTO_ARRIVO"));
				v.setCancellato(result.getInt("CANCELLATO"));
				
				return v;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}

	@Override
	public List<Volo> getAll() {
		String query = "SELECT * FROM VOLO";
		List<Volo> listaVoli = new ArrayList<>();

		try(
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			){
			
			ResultSet result = statement.executeQuery();			
			
			while(result.next()) {
				Volo v = new Volo();	
				
				v.setIdVolo(result.getInt("ID_VOLO"));
				v.setIdAereo(result.getInt("ID_AEREO"));
				v.setOrarioPartenza(result.getTimestamp("ORARIO_PARTENZA"));
				v.setOrarioArrivo(result.getTimestamp("ORARIO_ARRIVO"));
				v.setGiornoDelVolo(result.getDate("GIORNO_DEL_VOLO"));
				v.setIdAereo(result.getInt("ID_AEREO"));
				v.setIdPilota(result.getInt("ID_PILOTA"));
				v.setIdAereoportoPartenza(result.getInt("ID_AEREOPORTO_PARTENZA"));
				v.setIdAereoportoArrivo(result.getInt("ID_AEREOPORTO_ARRIVO"));
				v.setCancellato(result.getInt("CANCELLATO"));	
				
				listaVoli.add(v);
			}				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Volo v: listaVoli)
			System.out.println(v);
		
		return listaVoli;
	}


	@Override
	public void insert(Volo t) {
		String query = "INSERT INTO VOLO (ORARIO_PARTENZA,ORARIO_ARRIVO,GIORNO_DEL_VOLO,ID_AEREO,ID_PILOTA,"
				+ "ID_AEREOPORTO_PARTENZA,ID_AEREOPORTO_ARRIVO,CANCELLATO) VALUES (?,?,?,?,?,?,?,?);";
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query
			
			statement.setTimestamp(1, new Timestamp(t.getOrarioPartenza().getTime()));
			statement.setTimestamp(2, new Timestamp(t.getOrarioArrivo().getTime()));
			statement.setTimestamp(3, new Timestamp(t.getGiornoDelVolo().getTime()));
			statement.setInt(4, t.getIdAereo());
			statement.setInt(5, t.getIdPilota());	
			statement.setInt(6, t.getIdAereoportoPartenza());
			statement.setInt(7, t.getIdAereoportoArrivo());	
			statement.setInt(8, 0);
		
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Volo t) {
		String query = "UPDATE VOLO SET GIORNO_DEL_VOLO = ?, ID_AEREO = ?, ID_PILOTA = ?, ID_AEREOPORTO_PARTENZA = ?,"
				+ " ID_AEREOPORTO_ARRIVO = ?  WHERE ID_VOLO = ?";
		
		// ottenere la connessione
		try (
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query			
			
			statement.setTimestamp(1, new Timestamp(t.getGiornoDelVolo().getTime()));
			statement.setInt(2, t.getIdAereo());
			statement.setInt(3, t.getIdPilota());
			statement.setInt(4, t.getIdAereoportoPartenza());
			statement.setInt(5, t.getIdAereoportoArrivo());
			statement.setInt(6, t.getIdVolo());
				
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
	
	public void countPassFrom(Volo v) {
		String query = "SELECT COUNT(id_biglietto) AS NUMERO_PASSEGGERI "
				+ "FROM biglietto "
				+ "INNER JOIN volo ON biglietto.id_volo = volo.id_volo "
				+ "INNER JOIN aereoporto ON id_aereoporto = id_aereoporto_partenza "
				+ "WHERE aereoporto.citta = ?;";
		
		Integer i = null;
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query);	
			){
				// eseguo query			
			
				AereoportoDaoImpl a = new AereoportoDaoImpl();
				Aereoporto temp = new Aereoporto();
				
				temp = a.get(v.getIdAereoportoPartenza());
			
				statement.setString(1, temp.getCitta());		
			
				statement.execute();
				
				ResultSet result = statement.executeQuery();
				if(result.next()) 
					i = result.getInt("NUMERO_PASSEGGERI");
				
				System.out.println(i);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	
	public void getInVolo(Date d) {
		String query = "SELECT id_aereo AS AEREI_IN_VOLO "
				+ "FROM volo "
				+ "WHERE orario_partenza <= ? AND orario_arrivo >= ?;";
		
		Integer i = null;
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query);	
			){
				// eseguo query			
				String pattern = "yyyy-MM-dd HH:mm:ss";
				SimpleDateFormat formatter = new SimpleDateFormat(pattern);
				
				String todayAsString = formatter.format(d);
				
				statement.setString(1, todayAsString);		
				statement.setString(2, todayAsString);			
			
				
				ResultSet result = statement.executeQuery();
				if(result.next()) 
					i = result.getInt("AEREI_IN_VOLO");
				
				System.out.println(d);
				System.out.println(i);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	
	public void getVoloProficuo() {
		String query = "SELECT ID_VOLO, MAX(SOMMA_PREZZI) PREZZO_TOTALE "
				+ "	FROM ( "
				+ "			SELECT ID_VOLO, SUM(PREZZO) AS SOMMA_PREZZI "
				+ "            FROM BIGLIETTO "
				+ "            GROUP BY ID_VOLO "
				+ "		) B; ";
		
		Integer somma = null;
		Integer idVolo = null;
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query);	
			){
				// eseguo query						
				
				ResultSet result = statement.executeQuery();
				if(result.next()) {
					idVolo = result.getInt("ID_VOLO");
					somma = result.getInt("PREZZO_TOTALE");
				}
				
				
				System.out.println("ID VOLO: "+ idVolo);
				System.out.println("SOMMA DEI PREZZI: " +somma);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	
	public void getVoliNegativi(Date d) {
		String query = "SELECT ID_VOLO, SOMMA_BIGLIETTI "
				+ "FROM ( "
				+ "		SELECT VOLO.ID_VOLO,SUM(PREZZO)-STIPENDIO AS SOMMA_BIGLIETTI "
				+ "        FROM BIGLIETTO "
				+ "        JOIN VOLO ON biglietto.id_volo = volo.id_volo "
				+ "        JOIN PILOTA ON volo.id_pilota = pilota.id_pilota "
				+ "        WHERE GIORNO_DEL_VOLO = ? "
				+ "        GROUP BY VOLO.ID_VOLO       "
				+ "	) GUADAGNO;";
		
		Integer i = null;
		Integer sommaVolo = null;
		System.out.println(d);
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query);	
			){
				// eseguo query			
				String pattern = "yyyy-MM-dd HH:mm:ss";
				SimpleDateFormat formatter = new SimpleDateFormat(pattern);
				
				String todayAsString = formatter.format(d);
				System.out.println(todayAsString);
				
				statement.setString(1, todayAsString);					
				
				ResultSet result = statement.executeQuery();
				
				while(result.next()) { 
					i = result.getInt("ID_VOLO");
					sommaVolo = result.getInt("SOMMA_BIGLIETTI");
					System.out.println(i + " " + sommaVolo);
				}
				

			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		
	}
	
	public void getPasseggeriInVolo(int idVolo) {
		String query = "SELECT NOME, COGNOME, CODICE_FISCALE "
				+ "FROM BIGLIETTO "
				+ "JOIN PASSEGGERO ON BIGLIETTO.ID_PASSEGGERO = PASSEGGERO.ID_PASSEGGERO "
				+ "WHERE BIGLIETTO.ID_VOLO = ?;";
		String nome, cognome, codiceFiscale;
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query);	
			){
				// eseguo query			
			
				statement.setInt(1, idVolo);					
				
				ResultSet result = statement.executeQuery();
				
				while(result.next()) { 
					nome = result.getString("NOME");
					cognome = result.getString("COGNOME");
					codiceFiscale = result.getString("CODICE_FISCALE");
					System.out.println(nome + " " + cognome + " " + codiceFiscale);
				}
				

			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	
	public void getPasseggeriPrevisti(int idAereoPortoArrivo, Date d) {
		String query = "SELECT COUNT(ID_PASSEGGERO) AS PASSEGGERI "
				+ "FROM VOLO V "
				+ "INNER JOIN BIGLIETTO ON BIGLIETTO.ID_VOLO = V.ID_VOLO "
				+ "WHERE V.ID_AEREOPORTO_ARRIVO = ? AND GIORNO_DEL_VOLO = ?;";
		
		System.out.println(idAereoPortoArrivo+ " "+ d);
		Integer i = null;
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query);	
			){
				// eseguo query			
				String pattern = "yyyy-MM-dd";
				SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			
				String todayAsString = formatter.format(d);
				
				statement.setInt(1, idAereoPortoArrivo);					
				statement.setString(2, todayAsString);
				
				ResultSet result = statement.executeQuery();
				
				while(result.next()) { 
					i = result.getInt("PASSEGGERI");
					System.out.println(i);
				}						
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		
	}
	

}
