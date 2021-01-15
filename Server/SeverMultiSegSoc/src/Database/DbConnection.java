package Database;
/*
 * Clase DbConnection
 * 
 * Clase de conexion a base de datos. Tiene metodos para ejecutar consultas a la base de 
 * datos, y puede devolver resultado o no
 * 
 * @Author Grupo2
 * 
 * @Version 1.0
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	
	private Connection connection;
	private Statement statement;
	
	/**
     * Constructor por defecto
     */
	public DbConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/seguridadsocial", "root", ""); 
			statement = connection.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Ejecute el SQL query (actualizando los datos)
     * @param sql - type String - sql que hay que ejecutar
     * @return ResultSet -> se ha ejecutado correctamente
     * 			null -> exepction
     */
	public synchronized ResultSet executeQuery(String sql) {
		try {
			this.statement.execute(sql);
			return this.statement.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
     * Ejecute el SQL query (insertando los datos)
     * @param sql - type String - sql que hay que ejecutar
     */
	public synchronized void insertData(String sql) {
		try {
			this.statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
     * Getters
     */
	public Connection getConexion() {
		return this.connection;
	}
	
	/**
     * Cierra la conecion con BD
     */
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error in closing connection to DB. " + e.getMessage());
		}
	}
	
}