package tsi;
import edu.tsi.Practico.MDB.*;


public class ManagedBeanJSF {
	
	public ManagedBeanJSF() {
		super();
		iniciarSesion();
		
	}

	String SID;
	String nombre;
	String zip;
	String respuesta;
	
	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSID() {
		return SID;
	}

	public void setSID(String sID) {
		SID = sID;
	}

	//Action method to add user
	public String consultaWS1() {
		respuesta = new MDBClient().invocarWS1(SID, nombre);
		return "ws";
	}
	
	public String consultaWS2() {
		respuesta = new MDBClient().invocarWS2(SID, nombre);
		return "ws";
	}

	
	public String consultaWeather() {
		respuesta = new MDBClient().invocarWS3(SID, nombre, zip);
		return "weather";
	}
	
	public String finalizarSesion() {
		respuesta = new MDBClient().finalizarSesion(SID);
		SID = "";
		iniciarSesion();
		return "ws";
	}
	
	public void iniciarSesion()
	{
		if (SID == null || SID.isEmpty())
			SID = new MDBClient().iniciarSesion();
	}

}
