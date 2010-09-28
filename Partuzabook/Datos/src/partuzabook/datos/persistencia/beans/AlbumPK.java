package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the album database table.
 * 
 */
@Embeddable
public class AlbumPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="evt_id")
	private String evtId;

	@Column(name="cnt_id")
	private Integer cntId;

    public AlbumPK() {
    }
	public String getEvtId() {
		return this.evtId;
	}
	public void setEvtId(String evtId) {
		this.evtId = evtId;
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
		if (!(other instanceof AlbumPK)) {
			return false;
		}
		AlbumPK castOther = (AlbumPK)other;
		return 
			this.evtId.equals(castOther.evtId)
			&& this.cntId.equals(castOther.cntId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.evtId.hashCode();
		hash = hash * prime + this.cntId.hashCode();
		
		return hash;
    }
}