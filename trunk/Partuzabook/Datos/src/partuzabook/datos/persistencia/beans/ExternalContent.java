import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("E")
@NamedQueries({
	@NamedQuery(name = "ExternalContent.findAll", query = "SELECT o FROM ExternalContent o")
	})
public class ExternalContent extends Content {
	private static final long serialVersionUID = 1L;

}
