package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the cntCategory database table.
 * 
 */
@Entity
@Table(name="cntCategory")
public class CntCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cat_id_auto")
	private Integer catIdAuto;

	private String category;

	//bi-directional many-to-one association to Event
    @ManyToOne
	@JoinColumn(name="evt_id")
	private Event event;
    
	//bi-directional many-to-many association to Content
    @ManyToMany
	@JoinColumn(name="cat_id_auto")
	private Set<Content> contents;

    public CntCategory() {
    }

	public Integer getCatIdAuto() {
		return this.catIdAuto;
	}

	public void setCatIdAuto(Integer catIdAuto) {
		this.catIdAuto = catIdAuto;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
}