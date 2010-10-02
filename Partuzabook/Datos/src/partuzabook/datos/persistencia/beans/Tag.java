package partuzabook.datos.persistencia.beans;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the tags database table.
 * 
 */
@Entity
@Table(name="tags")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "flags", discriminatorType = DiscriminatorType.STRING)
@NamedQueries({
	@NamedQuery(name = "Tag.findAll", query = "SELECT o FROM Tag o")
	})
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tag_id_auto")
	private Integer tagIdAuto;

	@Column(name="cnt_id")
	private Integer cntId;

	@Column(name="\"posX\"")
	private Integer _posX_;

	@Column(name="\"posY\"")
	private Integer _posY_;

	@Column(name="reg_date")
	private Timestamp regDate;


	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="creator")
	private NormalUser user1;

    public Tag() {
    }

	public Integer getTagIdAuto() {
		return this.tagIdAuto;
	}

	public void setTagIdAuto(Integer tagIdAuto) {
		this.tagIdAuto = tagIdAuto;
	}

	public Integer getCntId() {
		return this.cntId;
	}

	public void setCntId(Integer cntId) {
		this.cntId = cntId;
	}

	public Integer get_posX_() {
		return this._posX_;
	}

	public void set_posX_(Integer _posX_) {
		this._posX_ = _posX_;
	}

	public Integer get_posY_() {
		return this._posY_;
	}

	public void set_posY_(Integer _posY_) {
		this._posY_ = _posY_;
	}

	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUserCreator(NormalUser user1) {
		this.user1 = user1;
	}
	
}