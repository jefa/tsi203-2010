import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("U")
public class TagForUser extends Tag{
	private static final long serialVersionUID = 1L;

}
