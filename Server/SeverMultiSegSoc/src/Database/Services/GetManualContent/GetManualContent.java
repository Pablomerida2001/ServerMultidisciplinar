package Database.Services.GetManualContent;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DbConnection;

public class GetManualContent {
	
	//metodo para hacer una consulta del contenido del manual de android por
	//indice y por idioma
	
	private static DbConnection db;
	
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
