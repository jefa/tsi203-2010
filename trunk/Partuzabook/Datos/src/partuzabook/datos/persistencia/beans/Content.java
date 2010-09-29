package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 * The persistent class for the content database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "flags", discriminatorType = DiscriminatorType.STRING)
public abstract class Content implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cnt_id_auto")
	private Integer cntIdAuto;

	@Column(name="reg_date")
	private Timestamp regDate;

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

	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
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