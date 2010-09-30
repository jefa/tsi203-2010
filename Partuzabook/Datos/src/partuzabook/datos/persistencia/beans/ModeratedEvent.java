package partuzabook.datos.persistencia.beans;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("M")
@NamedQueries({
	@NamedQuery(name = "ModeratedEvent.findAll", query = "SELECT o FROM ModeratedEvent o")
	})
public class ModeratedEvent extends Event {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Mod
	@OneToMany(mappedBy="event")
	private Set<Mod> mods;
	
	public Set<Mod> getMods() {
		return this.mods;
	}

	public void setMods(Set<Mod> mods) {
		this.mods = mods;
	}
	
}
