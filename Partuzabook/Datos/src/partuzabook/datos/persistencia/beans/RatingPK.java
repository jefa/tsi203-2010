package partuzabook.datos.persistencia.beans;


import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ratings database table.
 * 
 */
@Embeddable
public class RatingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="usr_id")
	private String usrId;

	@Column(name="cnt_id")
	private Integer cntId;

	public RatingPK() {
    }
	public String getUsrId() {
		return this.usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public Integer getCntId() {
		return this.cntId;
	}
	public void setCntId(Integer cntId) {
		this.cntId = cntId;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RatingPK)) {
			return false;
		}
		RatingPK castOther = (RatingPK)other;
		return 
			this.usrId.equals(castOther.usrId)
			&& this.cntId.equals(castOther.cntId);			

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.usrId.hashCode();
		hash = hash * prime + this.cntId.hashCode();
		
		return hash;
    }
}