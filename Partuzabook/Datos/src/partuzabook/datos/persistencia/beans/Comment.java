package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Comment.findAll", query = "SELECT o FROM Comment o")
	})
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CommentPK id;

	@Column(name="reg_date")
	private Timestamp regDate;

	private String text;
	
	//bi-directional many-to-one association to Content
    @ManyToOne
	@JoinColumn(name="cnt_id", insertable=false, updatable=false)
	private Content content;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="usr_id", insertable=false, updatable=false)
	private NormalUser user;

    public Comment() {
    }

	public CommentPK getId() {
		return this.id;
	}

	public void setId(CommentPK id) {
		this.id = id;
	}
	
	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
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