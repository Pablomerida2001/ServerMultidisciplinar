package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

import Ftp.Controller;

public class ConnectionThread extends Thread{
	
	Socket socket;
	DbConnection dbconnection;
	DataInputStream dataIS;
	DataOutputStream dataOS;
	User user;
	Controller ftpController;
	
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
				String[] values = msg.split("\\*");

				switch (values[0]) {
				case "0001":
					login(values[1], values[2]);
					break;
				case "0002":
					break;
				}
			}
		} catch(java.net.SocketException ee) {
			System.out.println("Client disconnected");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void login(String email, String passwrd) {
		String query = "Select * FROM user where Email = '"+email+"'";
		ResultSet rs = dbconnection.executeQuery(query);
		try {
			if(rs.next()) {
				if(rs.getString(6).equals(passwrd)) {
					this.user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5),rs.getString(6));
					dataOS.writeInt(0);
					connectFTP();
					loginFTP();
				}else {
					dataOS.writeInt(1);							
				}
			}else {
				dataOS.writeInt(2);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void register() {
		
	}
	
	
	public void connectFTP() {
		ftpController = new Controller(user.getName(), user.getPassword());
		if(!ftpController.connect()) {
			System.out.println("error de conexion ftp");
		}
	}
	
	public void registerFTP() {
		
	}
	
	public void loginFTP() {
		if(!ftpController.loginFTP()) {
			System.out.println("error de inicio de sesion ftp");
		}
	}
	
}