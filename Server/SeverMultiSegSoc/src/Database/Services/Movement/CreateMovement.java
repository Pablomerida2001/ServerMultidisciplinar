package Database.Services.Movement;

import Database.DbConnection;

public class CreateMovement {
	
	private static DbConnection db;
	
	/*
	 * Metodo que guarda el movimiento del usuario
	 */
	public static void InsertMovement(int userId,String role, String movement, String date) {
		db = new DbConnection();
		String sql = "Insert into mov values(" + userId + ",'" + role +"',' "+movement
				+"',' "+date+"');";
		db.insertData(sql);
		db.closeConnection();
	}
}
