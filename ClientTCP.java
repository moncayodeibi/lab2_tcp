import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * Trivial TCP client.
 */
public class ClientTCP {

	
	private static int SERVER_PORT = 9090;
	
	/**
     * Runs the client as an application.  First it displays a dialog
     * box asking for the IP address or hostname of a host running
     * the server, then connects to it and displays the message that
     * it serves.
     */
	
	public static void main(String[] args) throws IOException {
        
		//Se crea la varaiable 'out' que permitira el envio del mensaje al servidor
		PrintWriter out;
		String serverAddress = JOptionPane.showInputDialog("Enter IP Address of a machine that is\n" +
            							   "running the date service on port "+SERVER_PORT+":");
		
		//Se pide los dos valores para la suma
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
		JOptionPane.showMessageDialog(null, "Respuesta de la suma "+ a + " + " + b + " = " + answer);
		System.exit(0);
    }
	
}
