package practicoParte3;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Log database table.
 * 
 */
@Embeddable
public class LogPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="idWS")
	private Integer _idWS_;

    @Temporal( TemporalType.DATE)
	private java.util.Date date;

    public LogPK() {
    }
	public Integer get_idWS_() {
		return this._idWS_;
	}
	public void set_idWS_(Integer _idWS_) {
		this._idWS_ = _idWS_;
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
			this._idWS_.equals(castOther._idWS_)
			&& this.date.equals(castOther.date);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this._idWS_.hashCode();
		hash = hash * prime + this.date.hashCode();
		
		return hash;
    }
}