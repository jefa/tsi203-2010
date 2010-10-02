import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@DiscriminatorValue("A")
@NamedQueries({
	@NamedQuery(name = "Admin.findAll", query = "SELECT o FROM Admin o")
	})
public class Admin extends User{
	private static final long serialVersionUID = 1L;
	
}