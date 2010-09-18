package bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the log database table.
 * 
 */
@Embeddable
public class CachePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer idws;
	private String params;

    public CachePK() {
    }
	public Integer getIdws() {
		return this.idws;
	}
	public void setIdws(Integer idws) {
		this.idws = idws;
	}
	public String getParams() {
		return this.params;
	}
	public void setParams(String params) {
		this.params = params;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CachePK)) {
			return false;
		}
		CachePK castOther = (CachePK)other;
		return 
			this.idws.equals(castOther.idws)
			&& this.params.equals(castOther.params);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idws.hashCode();
		hash = hash * prime + this.params.hashCode();
		
		return hash;
    }
}