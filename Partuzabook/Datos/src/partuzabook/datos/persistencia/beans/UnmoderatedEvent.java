package partuzabook.datos.persistencia.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("U")
@NamedQueries({
	@NamedQuery(name = "UnmoderatedEvent.findAll", query = "SELECT o FROM UnmoderatedEvent o")
	})
public class UnmoderatedEvent extends Event {
	private static final long serialVersionUID = 1L;
}
