import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("P")
@NamedQueries({
	@NamedQuery(name = "Photo.findAll", query = "SELECT o FROM Photo o")
	})
public class Photo extends SelfContent {
	private static final long serialVersionUID = 1L;
}
