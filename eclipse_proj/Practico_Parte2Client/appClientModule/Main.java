import com.*;

public class Main {

	public static void main(String[] args) {
		
		String userName = "Augusto Wismario";
		
		System.out.println("Invocando al WS1...");
		System.out.println("Respuesta del servid: " + new MDBClient().invoke(userName, MDBClient.WS1));
		
		System.out.println("Invocando al WS2...");
		System.out.println("Respuesta del servid: " + new MDBClient().invoke(userName, MDBClient.WS2));
		
	}

}