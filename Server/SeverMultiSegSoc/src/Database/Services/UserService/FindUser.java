package Database.Services.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DbConnection;
import Database.User;

public class FindUser {

	private static DbConnection db;
	
	public static User FindUser(String email, String password) {
		db = new DbConnection();
		User user = new User();
		String query = "Select * FROM user where Email = '"+email+"' and Password like '" + password + "'";
		ResultSet rs = db.executeQuery(query);
		try {
			if(rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5),rs.getString(6));
				return user;
			}else {
				return user;
			}
		} catch (SQLException e) {
			System.out.println("Error, exception in FindUser by email and pwd. " + e.getMessage());
			return user;
		}
	}
	
}
