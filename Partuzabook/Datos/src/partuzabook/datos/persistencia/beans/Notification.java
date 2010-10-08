package partuzabook.datos.persistencia.beans;


import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the notifications database table.
 * 
 */
@Entity
@Table(name="notifications")
@NamedQueries({
	@NamedQuery(name = "Notification.findAll", query = "SELECT o FROM Notification o"),
	@NamedQuery(name = "Notification.findByUser", query = "SELECT o FROM Notification o " +
			"WHERE o.userTo = :user"),
	@NamedQuery(name = "Notification.findByUserUnread", query = "SELECT o FROM Notification o " +
			"WHERE o.userTo = :user AND o.read = false")		
	})
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="not_id_auto")
	private Integer notIdAuto;

	@Column(name="type")
	private Integer type;
	
  //  @Temporal( TemporalType.TIMESTAMP)
	@Column(name="not_date")
	private Timestamp notDate;

	private Boolean read;

	private String reference;

	@Column(name="reg_date")
	private Timestamp regDate;

	private String text;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="usr_frm_id")
	private User userFrom;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="usr_to_id")
	private NormalUser userTo;

    public Notification() {
    }

	public Integer getNotIdAuto() {
		return this.notIdAuto;
	}

	public void setNotIdAuto(Integer notIdAuto) {
		this.notIdAuto = notIdAuto;
	}

	public Integer getType() {
		return this.type;
	}
	
	public void setType(Integer t) {
		this.type = t;
	}
	
	public Timestamp getNotDate() {
		return this.notDate;
	}

	public void setNotDate(Timestamp notDate) {
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

	public User getUserFrom() {
		return this.userFrom;
	}

	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
	}
	
	public NormalUser getUserTo() {
		return this.userTo;
	}

	public void setUserTo(NormalUser userTo) {
		this.userTo = userTo;
	}
	
}