package Database.Services.UserService;

import Database.DbConnection;

public class ChangeOnlineStatus {
	
	private static DbConnection db;
	
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
