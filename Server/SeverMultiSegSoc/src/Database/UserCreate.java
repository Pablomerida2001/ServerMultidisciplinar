package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserCreate{

	public static boolean registrar(User user, Connection connection) {

		PreparedStatement ps = null;
		/*
		 * String name; String surname; Role role; String email; String
		 * password;
		 */

		String sql = "INSERT INTO user (name, surname, rol, email, password) values (?,?,?,?)";

		try {
			ps = connection.prepareStatement(sql);
			ps.setString(2, user.getName());
			ps.setString(3, user.getSurname());
			ps.setString(4, "Funcionario");
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getPassword());
			ps.execute();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
}