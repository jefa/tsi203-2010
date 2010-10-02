package partuzabook.datos.persistencia.beans;

import java.util.List;

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
public class Admin extends User{
	private static final long serialVersionUID = 1L;
	
	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="creator")
	private List<Event> eventsCreated;

	public List<Event> getEventsCreated() {
		return this.eventsCreated;
	}

	public void setEventsCreated(List<Event> eventsCreated) {
		this.eventsCreated = eventsCreated;
	}

	
}