package epn.edu.ec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * Clase Cliente TCP.
 */
public class ClientTCP {

	// Creación de una variable estática con el puerto de escucha
	
	private static int SERVER_PORT = 9090;
	
	public static void main(String[] args) throws IOException {
        
		//Se crea la varaiable que permitire el envio del mensaje al servidor
		PrintWriter out;
		//pedimos la ip del servidor
		String serverAddress = JOptionPane.showInputDialog("Enter IP Address of a machine that is\n" +
            							   "running the date service on port "+SERVER_PORT+":");
		
		//ingreso de dos número por pantalla
		String a = JOptionPane.showInputDialog("Ingrese el primer valor de la suma: ");
		String b = JOptionPane.showInputDialog("Ingrese el segundo valor de la suma: ");
		
		//Se une los dos valores en una sola cadena String, separados por una coma ','
		String envio = a + "," + b; 
        
		//Establece la conexión con el servidor mediante un socket
		Socket clientSocket = new Socket(serverAddress, SERVER_PORT);
		
		//Se inicializa la variable 'out' con el socket del cliente y se procede al envio del mensaje al servidor
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		out.println(envio);

		//Obtiene el mensaje enviado por el servidor a través del socket
		InputStreamReader inputStream = new InputStreamReader(clientSocket.getInputStream());

		//Lee los datos del mensaje
		BufferedReader input = new BufferedReader(inputStream);
		String answer = input.readLine();

		//Imprime los datos del mensaje
		JOptionPane.showMessageDialog(null, "La suma de "+ a + " + " + b + " = " + answer);
		System.exit(0);
    }
	
}
