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
	private NormalUser user_tagged;
	
	public User getUser2() {
		return this.user_tagged;
	}

	public void setUserTag(NormalUser user_tagged) {
		this.user_tagged = user_tagged;
	}

}
