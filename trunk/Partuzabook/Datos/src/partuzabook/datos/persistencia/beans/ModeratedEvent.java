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
@DiscriminatorValue("M")
@NamedQueries({
	@NamedQuery(name = "ModeratedEvent.findAll", query = "SELECT o FROM ModeratedEvent o")
	})
public class ModeratedEvent extends Event {
	private static final long serialVersionUID = 1L;
	
	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="myModeratedEvents")
	@JoinTable(name="Mods", 
          joinColumns=@JoinColumn(name="evt_id"),
          inverseJoinColumns=@JoinColumn(name="usr_id"))
	private List<Client> myMods;

	public List<Client> getMyMods() {
		return this.myMods;
	}

	public void setMyMods(List<Client> myMods) {
		this.myMods = myMods;
	}


}
