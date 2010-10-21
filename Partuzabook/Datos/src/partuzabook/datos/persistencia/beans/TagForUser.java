package partuzabook.datos.persistencia.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("U")
@NamedQueries({
	@NamedQuery(name = "TagForUser.findAll", query = "SELECT o FROM TagForUser o"),
	@NamedQuery(name = "TagForUser.getMostTagged",
			query = "SELECT u.username FROM TagForUser t, IN(t.userTagged) u "
				+ "GROUP BY u.username ORDER BY COUNT(*) DESC")
})
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
