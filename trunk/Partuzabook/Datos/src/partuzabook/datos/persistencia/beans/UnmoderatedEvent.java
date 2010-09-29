package partuzabook.datos.persistencia.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("U")
public class UnmoderatedEvent extends Event {
	private static final long serialVersionUID = 1L;
}
