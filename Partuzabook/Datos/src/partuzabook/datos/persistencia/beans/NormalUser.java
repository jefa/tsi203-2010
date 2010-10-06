package partuzabook.datos.persistencia.beans;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="userTo")
	private List<Notification> notificationsReceived;

	//bi-directional many-to-one association to Rating
	@OneToMany(mappedBy="user")
	private List<Rating> ratings;

	//bi-directional many-to-one association to Tag
	@OneToMany(mappedBy="creator")
	private List<Tag> tagsCreated;

	//bi-directional many-to-one association to Tag
	@OneToMany(mappedBy="userTagged")
	private List<TagForUser> myTags;

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(name="Participants", 
          joinColumns=@JoinColumn(name="usr_id"),
          inverseJoinColumns=@JoinColumn(name="evt_id"))
	private List<Event> myEvents;
	
	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Notification> getNotificationsReceived() {
		return this.notificationsReceived;
	}

	public void setNotifications2(List<Notification> notificationsReceived) {
		this.notificationsReceived = notificationsReceived;
	}
	public List<Rating> getRatings() {
		return this.ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
	public List<Tag> getTagsCreated() {
		return this.tagsCreated;
	}

	public void setTagsCreated(List<Tag> tagsCreated) {
		this.tagsCreated = tagsCreated;
	}
	
	public List<TagForUser> getMyTags() {
		return this.myTags;
	}

	public void setMyTags(List<TagForUser> myTags) {
		this.myTags = myTags;
	}

	public List<Event> getMyEvents() {
		return this.myEvents;
	}

	public void setMyEvents(List<Event> myEvents) {
		this.myEvents = myEvents;
	}	


}