package partuzabook.integracion.datatypes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatatypeContenido implements Serializable{
	private static final long serialVersionUID = 1L;

	private String descripcion;
	
	private String url;
	
	
    public DatatypeContenido() {
    	
    }

    public String getDescripcion(){
    	return this.descripcion;
    }
    
    public void setDescripcion(String desc){
    	this.descripcion = desc;
    }
    
	public String getDescripcionHTML() {
		return descripcion.replace("\n", "<br/>").replace("\r", "");
	}

    public String getUrl(){
    	return this.url;
    }

    public void setUrl(String url){
    	this.url = url;
    }
        
}
