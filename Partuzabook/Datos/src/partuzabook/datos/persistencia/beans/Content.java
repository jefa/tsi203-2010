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
			query = "SELECT c FROM Content c WHERE c.event = :event AND c.cntIdAuto = :content"),
	@NamedQuery(name = "Content.findAllInAlbumOfEvent",
			query = "SELECT c FROM Content c WHERE c.event = :event AND c.posAlbum IS NOT NULL"),
	@NamedQuery(name = "Content.findByPosInEvent",
			query = "SELECT c FROM Content c WHERE c.event = :event AND c.posGallery = :pos"),
	@NamedQuery(name = "Content.findByPosAlbum",
			query = "SELECT c FROM Content c WHERE c.event = :event AND c.posAlbum = :pos"),
	@NamedQuery(name = "Content.findNextPosInGalleryEvent",
			query = "SELECT c FROM Content c WHERE c.event = :event"),
	@NamedQuery(name = "Content.findNextPosInAlbumEvent",
			query = "SELECT c FROM Content c WHERE c.event = :event AND c.posAlbum IS NOT NULL"),			
	@NamedQuery(name = "Content.getBestRanked",
			query = "SELECT c.cntIdAuto FROM Content c, IN(c.ratings) r "
				+"GROUP BY c.cntIdAuto ORDER BY AVG(r.score) DESC"),
	@NamedQuery(name = "Content.getMostCommented",
			query = "SELECT c.cntIdAuto FROM Content c, IN(c.comments) m "
				+"GROUP BY c.cntIdAuto ORDER BY COUNT(*) DESC")
})
public abstract class Content implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cnt_id_auto")
	private Integer cntIdAuto;

	@Column(name="reg_date")
	private Timestamp regDate;

	@Column(name="url")
	private String url;

	@Column(name="pos_gallery")
	private int posGallery;

	@Column(name="pos_album")
	private Integer posAlbum;
	
	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="content")
	private List<Comment> comments;

	//bi-directional many-to-one association to Event
    @ManyToOne
	@JoinColumn(name="evt_id")
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
	
	//bi-directional many-to-many association to CntCategory
	@ManyToMany(mappedBy="contents") 
	@JoinTable(name="\"contentCntCategory\"", 
          joinColumns=@JoinColumn(name="cat_id"),
          inverseJoinColumns=@JoinColumn(name="cnt_id"))
	private List<CntCategory> cntCategories;

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

	public int getPosGallery() {
		return this.posGallery;
	}
	
	public void setPosGallery(int pos){
		this.posGallery = pos;
	}
	
	public Integer getPosAlbum() {
		return this.posAlbum;
	}
	
	public void setPosAlbum(Integer pos) {
		this.posAlbum = pos;
	}
	
	public List<CntCategory> getCntCategories() {
		return this.cntCategories;
	}

	public void setCntCategories(List<CntCategory> cntCategories) {
		this.cntCategories = cntCategories;
	}
	
}