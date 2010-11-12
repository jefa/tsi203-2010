package partuzabook.integracion.datatypes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatatypeEvento implements Serializable{
	private static final long serialVersionUID = 1L;

	private int idEvento;

	private String nombre;
	
	private Date fecha;
	
	private int tipo;
	
	private String descripcion;
	
	private String direccion;	
	
	private String urlCover;
	
	
    public DatatypeEvento() {
    	
    }
	
    public int getIdEvento(){
    	return this.idEvento;
    }

    public void setIdEvento(int id) {
		this.idEvento = id;
	}

    public String getNombre(){
    	return this.nombre;
    }
    
    public void setNombre(String nombre){
    	this.nombre = nombre;
    }

    public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date date) {
		this.fecha = date;
	}

	public String getFormattedDate() {
		SimpleDateFormat df = new SimpleDateFormat("d MMM yyyy"); 
		return df.format(this.fecha);
	}

    public int getTipo(){
    	return this.tipo;
    }

    public void setTipo(int tipo){
    	this.tipo = tipo;
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

    public String getDireccion(){
    	return this.direccion;
    }
    
    public void setDireccion(String dir){
    	this.direccion = dir;
    }

    public String getUrlCover(){
    	return this.urlCover;
    }

    public void setUrlCover(String url){
    	this.urlCover = url;
    }
        
}
