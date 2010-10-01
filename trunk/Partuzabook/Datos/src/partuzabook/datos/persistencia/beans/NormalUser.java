package partuzabook.datos.persistencia.beans;

import java.util.Set;

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
	private Set<Comment> comments;
	
	//bi-directional many-to-one association to Rating
	@OneToMany(mappedBy="user")
	private Set<Rating> ratings;
	
	//bi-directional many-to-one association to Tag
	@OneToMany(mappedBy="user1")
	private Set<Tag> tags1;

	//bi-directional many-to-one association to Tag
	@OneToMany(mappedBy="user2")
	private Set<Tag> tags2;
	
	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="userTo") 
	private Set<Notification> notificationsReceived;
	
	//bi-directional many-to-one association to Participant
	@OneToMany(mappedBy="user")
	private Set<Participant> participants;
	
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	public Set<Notification> getNotificationsReceived() {
		return this.notificationsReceived;
	}

	public void setNotificationsReceived(Set<Notification> notificationsReceived) {
		this.notificationsReceived = notificationsReceived;
	}
	
	public Set<Participant> getParticipants() {
		return this.participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}
	
	public Set<Rating> getRatings() {
		return this.ratings;
	}

	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}
	
	public Set<Tag> getTags1() {
		return this.tags1;
	}

	public void setTags1(Set<Tag> tags1) {
		this.tags1 = tags1;
	}
	
	public Set<Tag> getTags2() {
		return this.tags2;
	}

	public void setTags2(Set<Tag> tags2) {
		this.tags2 = tags2;
	}
	
}
