package Database.UserService.FindUser;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DbConnection;
import Database.User;

public class FindUserService {
	private DbConnection db;
	
	public FindUserService() {
		db = new DbConnection();
	}
	
	public void login(String email, String passwrd) {
		User user;
		String query = "Select * FROM user where Email = '"+email+"'";
		ResultSet rs = db.executeQuery(query);
		try {
			if(rs.next()) {
				if(rs.getString(6).equals(passwrd)) {
					user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5),rs.getString(6));
//					dataOS.writeInt(0);
				}else {
//					dataOS.writeInt(1);							
				}
			}else {
//				dataOS.writeInt(2);
			}
		} catch (SQLException e) {
		}
	}
}

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DbConnection;
import Database.User;

public class FindUserService {
	private DbConnection db;
	
	public FindUserService() {
		db = new DbConnection();
	}
	
	public void login(String email, String passwrd) {
		User user;
		String query = "Select * FROM user where Email = '"+email+"'";
		ResultSet rs = db.executeQuery(query);
		try {
			if(rs.next()) {
				if(rs.getString(6).equals(passwrd)) {
					user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5),rs.getString(6));
//					dataOS.writeInt(0);
				}else {
//					dataOS.writeInt(1);							
				}
			}else {
//				dataOS.writeInt(2);
			}
		} catch (SQLException e) {
		}
	}
}
package Database.UserService.FindUser;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DbConnection;
import Database.User;

public class FindUserService {
	private DbConnection db;
	
	public FindUserService() {
		db = new DbConnection();
	}
	
	public void login(String email, String passwrd) {
		User user;
		String query = "Select * FROM user where Email = '"+email+"'";
		ResultSet rs = db.executeQuery(query);
		try {
			if(rs.next()) {
				if(rs.getString(6).equals(passwrd)) {
					user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5),rs.getString(6));
//					dataOS.writeInt(0);
				}else {
//					dataOS.writeInt(1);							
				}
			}else {
//				dataOS.writeInt(2);
			}
		} catch (SQLException e) {
		}
	}
}
