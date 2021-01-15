package Database.Services.UserService;

import Database.DbConnection;

public class ChangeOnlineStatus {
	
	private static DbConnection db;
	
	/*
	 * Metodo que cambia el estado online del usuario para evitar varias conexiones al mismo tiempo
	 * desde una cuenta, y que cuando se desconecte se ponga el estado offline
	 */
	
	public static void changeOnlineStatus(boolean online, String email, String password) {
		db = new DbConnection();
		int num = 0;
		if(online) {
			num = 1;
		}
		String sql = "Update user SET Online ="+num+" Where Email = '" 
		+ email + "' and Password like '" + password + "'";
		db.insertData(sql);
		db.closeConnection();
	}
}
