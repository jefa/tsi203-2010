package bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the log database table.
 * 
 */
@Embeddable
public class LogPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer idws;

    @Temporal( TemporalType.DATE)
	private java.util.Date date;

    public LogPK() {
    }
	public Integer getIdws() {
		return this.idws;
	}
	public void setIdws(Integer idws) {
		this.idws = idws;
	}
	public java.util.Date getDate() {
		return this.date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LogPK)) {
			return false;
		}
		LogPK castOther = (LogPK)other;
		return 
			this.idws.equals(castOther.idws)
			&& this.date.equals(castOther.date);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idws.hashCode();
		hash = hash * prime + this.date.hashCode();
		
		return hash;
    }
}