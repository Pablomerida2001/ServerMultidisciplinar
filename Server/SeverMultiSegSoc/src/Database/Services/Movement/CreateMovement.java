package Database.Services.Movement;
/*
 * Clase CreateMovement
 * 
 * Clase que se utiliza para guardar el movimiento del usuario
 * 
 * @Author Grupo2
 * 
 * @Version 1.0
 * 
 */
import Database.DbConnection;

public class CreateMovement {
	
	private static DbConnection db;
	
	/**
     * Metodo que registra el movimiento del usuario en la base de datos
     * @param userId - type int - id del usuario
     * @param role - type String - role del usuario
     * @param movement - type String - movement que el usuario ha realizado
     * @param date - type String - fecha y hora de realizacion
     */
	public static void InsertMovement(int userId,String role, String movement, String date) {
		db = new DbConnection();
		String sql = "Insert into mov values(" + userId + ",'" + role +"',' "+movement
				+"',' "+date+"');";
		db.insertData(sql);
		db.closeConnection();
	}
}
