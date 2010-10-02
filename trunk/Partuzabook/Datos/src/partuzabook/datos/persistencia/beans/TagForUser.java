package partuzabook.datos.persistencia.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("U")
public class TagForUser extends Tag{
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="usr_tag")
	private NormalUser userTagged;

	public NormalUser getUserTagged() {
		return this.userTagged;
	}

	public void setUserTagged(NormalUser userTagged) {
		this.userTagged = userTagged;
	}

}
