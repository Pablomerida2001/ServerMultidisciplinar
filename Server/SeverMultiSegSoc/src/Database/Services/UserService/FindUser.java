package Database.Services.UserService;
/*
 * Clase FindUser
 * 
 * Clase que se que busca a un usuario por su email y contraseña
 * 
 * @Author Grupo2
 * 
 * @Version 1.0
 * 
 */
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DbConnection;
import Models.User;

public class FindUser {

	private static DbConnection db;
	
	/*
	 * Metodo que busca a un usuario por su email y contraseña
	 * Devuelva un objeto User, con datos si usuario existe y sin datos cuando no existe
	 */
	/**
     * Metodo que busca a un usuario por su email y contraseña
     * @param email - type String - email del usuario
     * @param password - type String - contraseña del usuario
     * @return User -> los datos del usuario
     */
	public static User FindUser(String email, String password) {
		db = new DbConnection();
		User user = new User();
		
		String query = "Select Distinct * "
						+ "From user "
						+ "Where Email = '" + email + "' and Password like '" + password + "'";
		ResultSet rs = db.executeQuery(query);
		try {
			if(rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5),rs.getString(6), rs.getBoolean(7));
				System.out.println(rs.getString(7));
			}
			
			db.closeConnection();
			return user;
		} catch (SQLException e) {
			System.out.println("Error, exception in FindUser by email and pwd. " + e.getMessage());

			db.closeConnection();
			return user;
		}
	}
}
