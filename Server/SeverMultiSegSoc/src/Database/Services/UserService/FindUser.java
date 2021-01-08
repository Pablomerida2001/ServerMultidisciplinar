package Database.Services.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DbConnection;
import Database.User;

public class FindUser {

	private static DbConnection db;
	
	/*
	 * Metodo que busca a un usuario por su email y contraseña
	 * Devuelva un objeto User, con datos si usuario existe y sin datos cuando no existe
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
