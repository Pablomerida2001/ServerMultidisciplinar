package Ftp;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class Controller {
	
	String username;
	String password;
	FTPClient client;
	
	public Controller(String username, String password) {
		this.username = username;
		this.password = password;
		this.client  = new FTPClient();

	}
	
	public boolean connect() {
		try {
			client.connect("127.0.0.1");
			return true;
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean loginFTP() {
		 try {
			if(client.login(username, password)){
                // Entrando a modo pasivo
                client.enterLocalPassiveMode();
                // Activar recibir/enviar cualquier tipo de archivo
                client.setFileType(FTP.BINARY_FILE_TYPE);

                // Obtener respuesta del servidor y acceder.
                int respuesta = client.getReplyCode();
                if (FTPReply.isPositiveCompletion(respuesta) == true) {
                    return true;
                }else{
                    return false;
                }
            }else{
            	System.out.println("no");
                return false;
            }
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void registerFTP() {
	}

}
