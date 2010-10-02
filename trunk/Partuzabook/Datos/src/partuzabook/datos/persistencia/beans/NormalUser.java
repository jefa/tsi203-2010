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
	private Set<Tag> tagsMade;

	//bi-directional many-to-one association to Tag
	@OneToMany(mappedBy="user_tagged")
	private Set<TagForUser> myTags;
	
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
	
	public Set<Tag> getTagsMade() {
		return this.tagsMade;
	}

	public void setTagsMade(Set<Tag> tagsMade) {
		this.tagsMade = tagsMade;
	}
	
	public Set<TagForUser> getMyTags() {
		return this.myTags;
	}

	public void setMyTags(Set<TagForUser> myTags) {
		this.myTags = myTags;
	}
	
}
