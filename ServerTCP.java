package epn.edu.ec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ClientTCP {
	private static int SERVER_PORT = 9090;
	
	public static void main(String[] args) throws IOException {
	        
			PrintWriter out;	//Se crea la varaiable 'out' que permitira el envio del mensaje al servidor
			String serverAddress = JOptionPane.showInputDialog("Enter IP Address of a machine that is\n" 
			+ "running the date service on port "+SERVER_PORT+":");
	        
			String a = JOptionPane.showInputDialog("Ingrese el primer valor de la suma: ");		//inicializamos la variable a
			String b = JOptionPane.showInputDialog("Ingrese el segundo valor de la suma: ");	//inicializamos la variable b
			
			String envio = a + "," + b;		//unimos los dos valores en una cadena String, separados por una coma ','
	        
			Socket clientSocket = new Socket(serverAddress, SERVER_PORT);	//establece la conexión con el servidor
			
			out = new PrintWriter(clientSocket.getOutputStream(), true);	//se inicializa la variable out con el socket del cliente y se envia el mensaje al servidor
			out.println(envio);

			InputStreamReader inputStream = new InputStreamReader(clientSocket.getInputStream());	//obtiene la respuesta del servidor mediante el socket

			BufferedReader input = new BufferedReader(inputStream);		//Lee el mensaje
			String answer = input.readLine();	//

			JOptionPane.showMessageDialog(null, "Respuesta de la suma "+ a + " + " + b + " = " + answer);	//Imprime los datos del mensaje
			System.exit(0);
	    }
}

-----

package epn.edu.ec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServerTCP {
	private static int PORT = 9090;
	public static void main(String[] args) throws IOException {
       
   	ServerSocket serverSocket = new ServerSocket(PORT);		//creamos el servidor con el puerto 9090
    System.out.println("Server listening on port "+PORT);	//Mostramos el mensaje que se ah inicializado el servidor
    int suma=0;		//iniciamos la variable tipo entero para realizar la suma entre el cliente y el servidor
    	try {
    		while (true) {
               Socket socket = serverSocket.accept();	//instanciamos un cliente y realizamos la conceccion entre Cliente-Servidor 
               InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());	//Se crea la variable inputStream para leer el mensaje enviado por el cliente
               try {
               	BufferedReader input = new BufferedReader(inputStream);		//Se crea el bufferReader que nos permitira extraer el String enviado por el cliente
           		String answer = input.readLine();	
           		StringTokenizer st = new StringTokenizer(answer	, ",");		//Una vez extraido el String se separan los valores con el StringTokenizer y se suman los valores para enviar la suma al cliente
           		while (st.hasMoreTokens()) {
						suma+=Integer.parseInt(st.nextToken());		//Realiza la suma de las variables que fueron enviados por parte del cliente
					}
                   PrintWriter out = new PrintWriter(socket.getOutputStream(), true);	//permita la salida del socket limpiando su salida 
                   out.println(suma);		//Desplega el resultado de la suma 
               } finally {
                   socket.close();		//Finalizamos el socket Cliente
               }
           }
       }
       finally {
       	serverSocket.close();	//Finalizamos el socket Servidor
       }
   }
}
