package Database.Services.UserService;
/*
 * Clase CreateMovement
 * 
 * Clase que se utiliza para cambiar el estado online del usuario para evitar varias conexiones al mismo tiempo
 * desde una cuenta, y que cuando se desconecte se ponga el estado offline
 * 
 * @Author Grupo2
 * 
 * @Version 1.0
 * 
 */
import Database.DbConnection;

public class ChangeOnlineStatus {
	
	private static DbConnection db;
	
	/**
     * Metodo que cambia el estado online del usuario
     * @param online - type boolean - True -> usuario es online
     * 								  False -> usuario offline
     * @param email - type String - email del usuario
     * @param password - type String - contraseña del usuario
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
