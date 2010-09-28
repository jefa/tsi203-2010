package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the notifications database table.
 * 
 */
@Entity
@Table(name="notifications")
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="not_id_auto")
	private Integer notIdAuto;

    @Temporal( TemporalType.DATE)
	@Column(name="not_date")
	private Date notDate;

	private Boolean read;

	private String reference;

	@Column(name="reg_date")
	private Timestamp regDate;

	private String text;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="usr_id")
	private User user;

    public Notification() {
    }

	public Integer getNotIdAuto() {
		return this.notIdAuto;
	}

	public void setNotIdAuto(Integer notIdAuto) {
		this.notIdAuto = notIdAuto;
	}

	public Date getNotDate() {
		return this.notDate;
	}

	public void setNotDate(Date notDate) {
		this.notDate = notDate;
	}

	public Boolean getRead() {
		return this.read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}