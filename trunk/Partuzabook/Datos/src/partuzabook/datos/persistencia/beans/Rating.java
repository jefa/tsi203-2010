package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the ratings database table.
 * 
 */
@Entity
@Table(name="ratings")
@NamedQueries({
	@NamedQuery(name = "Rating", query = "SELECT o FROM Rating o")
	})
public class Rating implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RatingPK id;

	@Column(name="reg_date")
	private Timestamp regDate;

	private Integer score;

	//bi-directional many-to-one association to Content
    @ManyToOne
	@JoinColumn(name="cnt_id", insertable=false, updatable=false)
	private Content content;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="usr_id", insertable=false, updatable=false)
	private NormalUser user;

    public Rating() {
    }

	public RatingPK getId() {
		return this.id;
	}

	public void setId(RatingPK id) {
		this.id = id;
	}
	
	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Content getContent() {
		return this.content;
	}

	public void setContent(Content content) {
		this.content = content;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(NormalUser user) {
		this.user = user;
	}
	
}