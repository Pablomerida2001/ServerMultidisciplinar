package Server;
/*
 * Clase Main
 * 
 * Punto de entrada de Server
 * Crea nuevos sockets y asigna a un hilo que es responsable de gestion de esta conecion
 * 
 * @Author Grupo2
 * 
 * @Version 1.0
 * 
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	static ServerSocket server; 
	static Socket socket;
	static int port = 5013;
	
	/*
	 * Punto de entrada
	 */
	public static void main(String[] args) {
		try {
			//se inicia el servidor en el puerto 5013
			server = new ServerSocket(port);
			while(true) {
					//se esperan conexiones, y se inicia un nuevo hilo para cada conexion.
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