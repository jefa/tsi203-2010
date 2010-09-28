package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String username;

	private String flags;

	private String password;

	@Column(name="reg_date")
	private Timestamp regDate;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="user")
	private Set<Comment> comments;

	//bi-directional many-to-one association to Content
	@OneToMany(mappedBy="user")
	private Set<Content> contents;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="user")
	private Set<Event> events;

	//bi-directional many-to-one association to Mod
	@OneToMany(mappedBy="user")
	private Set<Mod> mods;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="user")
	private Set<Notification> notifications;

	//bi-directional many-to-one association to Participant
	@OneToMany(mappedBy="user")
	private Set<Participant> participants;

	//bi-directional many-to-one association to Rating
	@OneToMany(mappedBy="user")
	private Set<Rating> ratings;

	//bi-directional many-to-one association to Tag
	@OneToMany(mappedBy="user1")
	private Set<Tag> tags1;

	//bi-directional many-to-one association to Tag
	@OneToMany(mappedBy="user2")
	private Set<Tag> tags2;

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

	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	public Set<Content> getContents() {
		return this.contents;
	}

	public void setContents(Set<Content> contents) {
		this.contents = contents;
	}
	
	public Set<Event> getEvents() {
		return this.events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	
	public Set<Mod> getMods() {
		return this.mods;
	}

	public void setMods(Set<Mod> mods) {
		this.mods = mods;
	}
	
	public Set<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
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