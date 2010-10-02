package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the content database table.
 * 
 */
@Embeddable
public class ContentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cnt_id_auto")
	private Integer cntIdAuto;

	@Column(name="evt_id")
	private Integer evtId;

    public ContentPK() {
    }
	public Integer getCntIdAuto() {
		return this.cntIdAuto;
	}
	public void setCntIdAuto(Integer cntIdAuto) {
		this.cntIdAuto = cntIdAuto;
	}
	public Integer getEvtId() {
		return this.evtId;
	}
	public void setEvtId(Integer evtId) {
		this.evtId = evtId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ContentPK)) {
			return false;
		}
		ContentPK castOther = (ContentPK)other;
		return 
			this.cntIdAuto.equals(castOther.cntIdAuto)
			&& this.evtId.equals(castOther.evtId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cntIdAuto.hashCode();
		hash = hash * prime + this.evtId.hashCode();
		
		return hash;
    }
}