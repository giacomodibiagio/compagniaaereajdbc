package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import main.model.Passeggero;
import main.model.Pilota;
import main.model.Volo;

public class PasseggeroDaoImpl implements PasseggeroDao {

	@Override
	public Passeggero get(int id) {
		String query = "SELECT * FROM PASSEGGERO WHERE ID_PASSEGGERO = ?";
		
		// ottenere la connessione
		try (
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query			
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			// ricostruisco il risultato
			Passeggero p = new Passeggero();
			if(result.next()) {
				p.setIdPasseggero(result.getInt("ID_PASSEGGERO"));	
				p.setNome(result.getString("NOME"));
				p.setCognome(result.getString("COGNOME"));
				p.setCodiceFiscale(result.getString("CODICE_FISCALE"));

				return p;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Passeggero> getAll() {		
		String query = "SELECT * FROM PASSEGGERO";
		List<Passeggero> listaPasseggeri= new ArrayList<>();

		try(
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			){
			
			ResultSet result = statement.executeQuery();			
			
			while(result.next()) {
				Passeggero p = new Passeggero();					
				p.setIdPasseggero(result.getInt("ID_PASSEGGERO"));
				p.setNome(result.getString("NOME"));	
				p.setCognome(result.getString("COGNOME"));
				p.setCodiceFiscale(result.getString("CODICE_FISCALE"));
				
				listaPasseggeri.add(p);
			}				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Passeggero p: listaPasseggeri)
			System.out.println(p);
		
		return listaPasseggeri;		

	}

	@Override
	public void insert(Passeggero t) {
		String query = "INSERT INTO PASSEGGERO (NOME,COGNOME,CODICE_FISCALE) VALUES (?,?,?);";
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query
			statement.setString(1,t.getNome());
			statement.setString(2,t.getCognome());
			statement.setString(3,t.getCodiceFiscale());
				
			statement.execute();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Passeggero t) {
		String query = "UPDATE PASSEGGERO SET NOME = ? , SET COGNOME = ?, SET CODICE_FISCALE = ? WHERE ID_PASSEGGERO = ?";			
		
		// ottenere la connessione
		try (
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);	
			){
			// eseguo query			
			
			statement.setString(1, t.getNome());
			statement.setString(2, t.getCognome());	
			statement.setString(3, t.getCodiceFiscale());
			statement.setInt(4, t.getIdPasseggero());
				
			statement.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
		String query = "DELETE FROM PASSEGGERO WHERE ID_PASSEGGERO = ?";
		
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
	
	public void getPasseggeroMax() {
		String query = "SELECT speso.ID_PASSEGGERO, MAX(totSpeso) speso_di_piu "
				+ " FROM ( "
				+ "	SELECT SUM(b.prezzo) totSpeso, b.id_passeggero "
				+ "	FROM passeggero p\r\n"
				+ "	INNER JOIN biglietto b ON p.id_passeggero = b.id_passeggero "
				+ "	INNER JOIN volo v ON v.id_volo = b.id_volo\r\n"
				+ "	INNER JOIN aereoporto a ON v.id_aereoporto_partenza = a.id_aereoporto "
				+ "	WHERE CANCELLATO = 0 "
				+ "	GROUP BY id_passeggero "
				+ "	ORDER BY totSpeso DESC "
				+ "	) SPESO;";
		
		Integer i = null;
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query);	
			){
				// eseguo query			
			
			
				
				ResultSet result = statement.executeQuery();
				if(result.next()) 
					i = result.getInt("ID_PASSEGGERO");
				
				PasseggeroDaoImpl p = new PasseggeroDaoImpl();
				Passeggero pa = new Passeggero();
				pa = p.get(i);
				
				System.out.println(pa.getNome()+" "+pa.getCognome());
				

				System.out.println(i);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		
	}
	
	public void getPasseggeroMin() {
		String query = "SELECT speso.ID_PASSEGGERO, MIN(totSpeso) speso_di_piu "
				+ " FROM ( "
				+ "		SELECT SUM(b.prezzo) totSpeso, b.id_passeggero "
				+ "		FROM passeggero p\r\n"
				+ "		INNER JOIN biglietto b ON p.id_passeggero = b.id_passeggero "
				+ "		INNER JOIN volo v ON v.id_volo = b.id_volo\r\n"
				+ "		INNER JOIN aereoporto a ON v.id_aereoporto_partenza = a.id_aereoporto "
				+ "		WHERE CANCELLATO = 0 "
				+ "		GROUP BY id_passeggero "
				+ "		ORDER BY totSpeso ASC "
				+ "	)AS speso";
		
		Integer i = null;
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query);	
			){
				// eseguo query			
			
			
				
				ResultSet result = statement.executeQuery();
				if(result.next()) 
					i = result.getInt("ID_PASSEGGERO");
				
				PasseggeroDaoImpl p = new PasseggeroDaoImpl();
				Passeggero pa = new Passeggero();
				pa = p.get(i);
				
				System.out.println(pa.getNome()+" "+pa.getCognome());
				

				System.out.println(i);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	
	public void getPasseggeroMaxViaggi() {
		String query = "SELECT p.id_passeggero, p.nome, p.cognome, COUNT(*) AS NUMERO_VIAGGI "
				+ "FROM PASSEGGERO P "
				+ "INNER JOIN BIGLIETTO B ON p.id_passeggero = b.id_passeggero "
				+ "GROUP BY P.NOME, P.COGNOME "
				+ "ORDER BY NUMERO_VIAGGI DESC "
				+ "LIMIT 1;";
		
		Integer i = null;
		String nome,cognome;
		Integer numViaggi;
		
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query);	
			){
				// eseguo query									
				
				ResultSet result = statement.executeQuery();
				
				if(result.next()) {
					i = result.getInt("ID_PASSEGGERO");
					nome = result.getString("NOME");
					cognome = result.getString("COGNOME");
					numViaggi = result.getInt("NUMERO_VIAGGI");
					System.out.println("id: "+ i + ", " + nome + " " + cognome + ", numero viaggi: "+ numViaggi);
				}
								
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}

}
