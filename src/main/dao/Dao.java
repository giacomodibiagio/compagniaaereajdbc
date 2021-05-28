package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

	default Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/compagnia_aerea?serverTimezone=UTC&useSSL=false";
		return DriverManager.getConnection(url, "root","root");				
	}
	
	public abstract T get(int id);
	public abstract List<T> getAll();
	public abstract void insert(T t);
	public abstract void update(T t);
	public abstract void delete(int id);
}
