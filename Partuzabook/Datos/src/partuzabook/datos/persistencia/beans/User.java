package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "flags", discriminatorType = DiscriminatorType.STRING)
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String username;

	private String flags;

	private String password;

	@Column(name="reg_date")
	private Timestamp regDate;

	//bi-directional many-to-one association to Content
	@OneToMany(mappedBy="user")
	private List<Content> contentsCreated;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="creator")
	private List<Event> eventsCreated;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="userFrom")
	private List<Notification> notificationsCreated;

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
	private List<Tag> myTags;

	//bi-directional many-to-many association to Event
    @ManyToMany
	@JoinTable(name="Mods", 
	          joinColumns=@JoinColumn(name="usr_id"),
	          inverseJoinColumns=@JoinColumn(name="evt_id"))
	private List<Event> myModeratedEvents;

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(name="Participants", 
          joinColumns=@JoinColumn(name="usr_id"),
          inverseJoinColumns=@JoinColumn(name="evt_id"))
	private List<Event> myEvents;
	
    public User() {
    }

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFlags() {
		return this.flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public List<Content> getContentsCreated() {
		return this.contentsCreated;
	}

	public void setContentsCreated(List<Content> contentsCreated) {
		this.contentsCreated = contentsCreated;
	}
	
	public List<Event> getEventsCreated() {
		return this.eventsCreated;
	}

	public void setEventsCreated(List<Event> eventsCreated) {
		this.eventsCreated = eventsCreated;
	}
	
	public List<Notification> getNotificationsCreated() {
		return this.notificationsCreated;
	}

	public void setNotificationsCreated(List<Notification> notificationsCreated) {
		this.notificationsCreated = notificationsCreated;
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
	
	public List<Tag> getTags2() {
		return this.myTags;
	}

	public void setMyTags(List<Tag> myTags) {
		this.myTags = myTags;
	}
	
	public List<Event> getMyModeratedEvents() {
		return this.myModeratedEvents;
	}

	public void setMyModeratedEvents(List<Event> myModeratedEvents) {
		this.myModeratedEvents = myModeratedEvents;
	}
	
	public List<Event> getMyEvents() {
		return this.myEvents;
	}

	public void setMyEvents(List<Event> myEvents) {
		this.myEvents = myEvents;
	}	
}