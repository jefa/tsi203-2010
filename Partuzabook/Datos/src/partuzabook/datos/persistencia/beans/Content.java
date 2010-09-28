package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the content database table.
 * 
 */
@Entity
public class Content implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cnt_id_auto")
	private Integer cntIdAuto;

	private String description;

	private String flags;

	@Column(name="reg_date")
	private Timestamp regDate;

	private Integer size;

	private String url;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="content")
	private Set<Album> albums;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="content")
	private Set<Comment> comments;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="creator")
	private User user;

	//bi-directional many-to-one association to Rating
	@OneToMany(mappedBy="content")
	private Set<Rating> ratings;

    public Content() {
    }

	public Integer getCntIdAuto() {
		return this.cntIdAuto;
	}

	public void setCntIdAuto(Integer cntIdAuto) {
		this.cntIdAuto = cntIdAuto;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFlags() {
		return this.flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Integer getSize() {
		return this.size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}
	
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Set<Rating> getRatings() {
		return this.ratings;
	}

	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}
	
}