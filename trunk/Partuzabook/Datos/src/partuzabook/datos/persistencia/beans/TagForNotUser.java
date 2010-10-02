import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("N")
public class TagForNotUser extends Tag{
	private static final long serialVersionUID = 1L;

}
