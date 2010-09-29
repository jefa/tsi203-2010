package partuzabook.datos.persistencia.beans;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("C")
public class Client extends NormalUser {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Mod
	@OneToMany(mappedBy="user")
	private Set<Mod> mods;
	
	public Set<Mod> getMods() {
		return this.mods;
	}

	public void setMods(Set<Mod> mods) {
		this.mods = mods;
	}

}
