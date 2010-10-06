package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tags database table.
 * 
 */
@Embeddable
public class TagPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tag_id_auto")
	private Integer tagIdAuto;

	@Column(name="cnt_id")
	private Integer cntId;

	@Column(name="evt_id")
	private Integer evtId;

    public TagPK() {
    }
	public Integer getTagIdAuto() {
		return this.tagIdAuto;
	}
	public void setTagIdAuto(Integer tagIdAuto) {
		this.tagIdAuto = tagIdAuto;
	}
	public Integer getCntId() {
		return this.cntId;
	}
	public void setCntId(Integer cntId) {
		this.cntId = cntId;
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
		if (!(other instanceof TagPK)) {
			return false;
		}
		TagPK castOther = (TagPK)other;
		return 
			this.tagIdAuto.equals(castOther.tagIdAuto)
			&& this.cntId.equals(castOther.cntId)
			&& this.evtId.equals(castOther.evtId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tagIdAuto.hashCode();
		hash = hash * prime + this.cntId.hashCode();
		hash = hash * prime + this.evtId.hashCode();
		
		return hash;
    }
}