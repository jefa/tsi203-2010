package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the participants database table.
 * 
 */
@Embeddable
public class ParticipantPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="usr_id")
	private String usrId;

	@Column(name="evt_id")
	private String evtId;

    public ParticipantPK() {
    }
	public String getUsrId() {
		return this.usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getEvtId() {
		return this.evtId;
	}
	public void setEvtId(String evtId) {
		this.evtId = evtId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ParticipantPK)) {
			return false;
		}
		ParticipantPK castOther = (ParticipantPK)other;
		return 
			this.usrId.equals(castOther.usrId)
			&& this.evtId.equals(castOther.evtId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.usrId.hashCode();
		hash = hash * prime + this.evtId.hashCode();
		
		return hash;
    }
}