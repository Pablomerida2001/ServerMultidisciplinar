package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	
	//clase de conexion a base de datos. Tiene metodos para ejecutar consultas a la base de 
	//datos, y puede devolver resultado o no
	
	Connection connection;
	Statement statement;
	
	public DbConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/seguridadsocial", "root", ""); 
			statement = connection.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized ResultSet executeQuery(String sql) {
		try {
			this.statement.execute(sql);
			return this.statement.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public synchronized void insertData(String sql) {
		try {
			this.statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConexion() {
		return this.connection;
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error in closing connection to DB. " + e.getMessage());
		}
	}
	
}