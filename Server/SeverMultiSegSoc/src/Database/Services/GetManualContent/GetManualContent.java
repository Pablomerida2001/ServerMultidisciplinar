package Database.Services.GetManualContent;
/*
 * Clase GetManualContent
 * 
 * Clase que se utiliza para hacer una consulta del contenido del manual de android por
	//indice y por idioma
 * 
 * @Author Grupo2
 * 
 * @Version 1.0
 * 
 */
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DbConnection;

public class GetManualContent {
	
	private static DbConnection db;
	
	/**
     * Metodo que consulta el contenido del manual del android
     * @param index - type int - index del contenido que hay que sacar
     * @param lenguage - type String - lenguaje en que hay que sacar
     * @return String -> el content (el texto)
     */
	public static String getContent(int index, String lenguage) {
		db = new DbConnection();
		String sql = "Select Texto From contenido Where Indice = "+index
				+" AND Idioma = '"+lenguage+"'";
		ResultSet rs = db.executeQuery(sql);
		try {
			if(rs.next())return rs.getString(1) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return "";
	}


}
