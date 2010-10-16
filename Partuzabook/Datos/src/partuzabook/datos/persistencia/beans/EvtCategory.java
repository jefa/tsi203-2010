package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the evtCategory database table.
 * 
 */
@Entity
@Table(name="\"evtCategory\"")
public class EvtCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String category;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="evtCategory")
	private List<Event> events;

    public EvtCategory() {
    }

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
}