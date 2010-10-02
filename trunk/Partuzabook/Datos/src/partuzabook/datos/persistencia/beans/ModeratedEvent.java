import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("M")
@NamedQueries({
	@NamedQuery(name = "ModeratedEvent.findAll", query = "SELECT o FROM ModeratedEvent o")
	})
public class ModeratedEvent extends Event {
	private static final long serialVersionUID = 1L;

}
