package partuzabook.datos.persistencia.beans;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@DiscriminatorValue("C")
@NamedQueries({
	@NamedQuery(name = "Client.findAll", query = "SELECT o FROM Client o")
	})
public class Client extends NormalUser{
	private static final long serialVersionUID = 1L;
	
	//bi-directional many-to-many association to Event
    @ManyToMany
	@JoinTable(name="Mods", 
	          joinColumns=@JoinColumn(name="usr_id"),
	          inverseJoinColumns=@JoinColumn(name="evt_id"))
	private List<ModeratedEvent> myModeratedEvents;

	
	public List<ModeratedEvent> getMyModeratedEvents() {
		return this.myModeratedEvents;
	}

	public void setMyModeratedEvents(List<ModeratedEvent> myModeratedEvents) {
		this.myModeratedEvents = myModeratedEvents;
	}

	
}
