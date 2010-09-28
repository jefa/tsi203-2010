package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CommentPK id;

	@Column(name="reg_date")
	private Timestamp regDate;

	private String text;

	//bi-directional many-to-one association to Content
    @ManyToOne
	@JoinColumn(name="cnt_id")
	private Content content;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="usr_id")
	private User user;

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

	public void setUser(User user) {
		this.user = user;
	}
	
}