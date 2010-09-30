package partuzabook.datos.persistencia.beans;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("A")
@NamedQueries({
	@NamedQuery(name = "Admin.findAll", query = "SELECT o FROM Admin o")
	})
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
