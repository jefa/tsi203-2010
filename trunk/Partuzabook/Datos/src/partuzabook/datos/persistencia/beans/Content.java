package partuzabook.datos.persistencia.beans;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the content database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "flags", discriminatorType = DiscriminatorType.STRING)
@NamedQueries({
	@NamedQuery(name = "Content.findAll",
			query = "SELECT c FROM Content c"),
	@NamedQuery(name = "Content.findByIDInEvent",
			query = "SELECT c FROM Content c WHERE c.event = :event AND c.id = :content")
})
public abstract class Content implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ContentPK id;

	private Boolean album;

	@Column(name="reg_date")
	private Timestamp regDate;

	private String url;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="content")
	private List<Comment> comments;

	//bi-directional many-to-one association to Event
    @ManyToOne
	@JoinColumn(name="evt_id", insertable=false, updatable=false)
	private Event event;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="creator")
	private User user;

	//bi-directional many-to-one association to Rating
	@OneToMany(mappedBy="content")
	private List<Rating> ratings;

	//bi-directional many-to-one association to Tag
	@OneToMany(mappedBy="content")
	private List<Tag> tags;

    public Content() {
    }

	public ContentPK getId() {
		return this.id;
	}

	public void setId(ContentPK id) {
		this.id = id;
	}
	
	public Boolean getAlbum() {
		return this.album;
	}

	public void setAlbum(Boolean album) {
		this.album = album;
	}

	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}


	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Rating> getRatings() {
		return this.ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
	public List<Tag> getTags() {
		return this.tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
}