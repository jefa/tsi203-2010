package partuzabook.datos.persistencia.beans;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@DiscriminatorValue("N")
@NamedQueries({
	@NamedQuery(name = "NormalUser.findAll", query = "SELECT o FROM NormalUser o")
	})
public class NormalUser extends User {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="user")
	private List<Comment> comments;
	
	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
