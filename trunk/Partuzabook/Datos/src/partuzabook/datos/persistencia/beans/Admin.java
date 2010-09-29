package partuzabook.datos.persistencia.beans;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("A")
public class Admin extends User {
	private static final long serialVersionUID = 1L;
	
	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="admin")
	private Set<Event> events;
	
	public Set<Event> getEvents() {
		return this.events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	

}
