import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("V")
@NamedQueries({
	@NamedQuery(name = "Video.findAll", query = "SELECT o FROM Video o")
	})
public class Video extends SelfContent {
	private static final long serialVersionUID = 1L;
	

}
