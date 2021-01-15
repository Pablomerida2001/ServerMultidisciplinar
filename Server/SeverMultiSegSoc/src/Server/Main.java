package Server;
/*
 * Punto de entrada de Server
 * Crear nuevos sockets y asigna a un hilo que es responsable de gestion de esta conecion
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	static ServerSocket server; 
	static Socket socket;
	static int port = 5013;
	
	public static void main(String[] args) {
		try {
			server = new ServerSocket(port);
			while(true) {
					socket = new Socket();		
					System.out.println("Waiting connection...");
					socket = server.accept();
					ConnectionThread connection = new ConnectionThread(socket);
					connection.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}