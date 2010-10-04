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
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private String username;

	private String password;

	@Column(name="reg_date")
	private Timestamp regDate;

	//bi-directional many-to-one association to Content
	@OneToMany(mappedBy="user")
	private List<Content> contentsCreated;

	
	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="userFrom")
	private List<Notification> notificationsCreated;
	
    public User() {
    }

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
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
		
	public List<Notification> getNotificationsCreated() {
		return this.notificationsCreated;
	}

	public void setNotificationsCreated(List<Notification> notificationsCreated) {
		this.notificationsCreated = notificationsCreated;
	}

	
}