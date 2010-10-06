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
public abstract class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@GeneratedValue(strategy=GenerationType.AUTO)
	private TagPK id;

/*	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tag_id_auto")
	private Integer tagIdAuto;

	@Id
	@Column(name="cnt_id")
	private Integer cntId;

	@Id
	@Column(name="evt_id")
	private Integer evtId;

	public void setCntId(int cntId) {
		this.cntId = cntId;
	}
	public void setEvtId(int evtId) {
		this.evtId = evtId;
	}
	
*/	
	
	@Column(name="\"posX\"")
	private Integer _posX_;

	@Column(name="\"posY\"")
	private Integer _posY_;

	@Column(name="reg_date")
	private Timestamp regDate;

	//bi-directional many-to-one association to Content
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cnt_id", referencedColumnName="cnt_id_auto", insertable=false, updatable=false),
		@JoinColumn(name="evt_id", referencedColumnName="evt_id", insertable=false, updatable=false)
		})
	private Content content;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="creator")
	private NormalUser creator;


    public Tag() {
    }

	public TagPK getId() {
		return this.id;
	}

	public void setId(TagPK id) {
		this.id = id;
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

	public Content getContent() {
		return this.content;
	}

	public void setContent(Content content) {
		this.content = content;
	}
	
	public NormalUser getCreator() {
		return this.creator;
	}

	public void setCreator(NormalUser creator) {
		this.creator = creator;
	}
		
}