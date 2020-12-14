package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionThread extends Thread{
	
	Socket socket;
	DbConnection dbconnection;
	DataInputStream dataIS;
	DataOutputStream dataOS;
	
	public ConnectionThread(Socket socket, DbConnection dbconnection) {
		this.socket = socket;
		this.dbconnection = dbconnection;
	}
	
	public void run() {
		try {
			dataIS = new DataInputStream(socket.getInputStream());
			dataOS = new DataOutputStream(socket.getOutputStream());
			
			while(true) {
				String msg = dataIS.readUTF();
				String[] values = msg.split("*");
				
				switch (values[0]) {
				case "0001":
					login(values[1], values[2]);
					break;
				case "0002":
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void login(String email, String passwrd) {
		String query = "Select * FROM email where Email = '"+email+"'";
		ResultSet rs = dbconnection.executeQuery(query);
		try {
			if(rs.next()) {
				if(rs.getString(2).equals(passwrd)) {
					dataOS.writeInt(000);
				}else {
					dataOS.writeInt(001);							
				}
			}else {
				dataOS.writeInt(002);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}