import com.MDBClient;

public class Main {

	public static void main(String[] args) {
		
		String userName = "Augusto Wismario";
		
		System.out.println("Invocando a iniciarSesion");
		MDBClient mdb = new MDBClient();
		
		String sessionID = mdb.iniciarSesion();
		
		System.out.println("Respuesta del servidor: " + sessionID);
		
		System.out.println("Invocando al WS1...");		
		System.out.println("Respuesta del servid: " + mdb.invocarWS1(sessionID, userName));
		
		System.out.println("Invocando al WS2...");
		System.out.println("Respuesta del servid: " + mdb.invocarWS1(sessionID, userName));
		
		System.out.println("Invocando al WS2...");
		System.out.println("Respuesta del servid: " + mdb.finalizarSesion(sessionID));
		
		
	}

}