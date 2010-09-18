package bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the cache database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Cache.findByIdwsParams",
			query="SELECT c FROM Cache c, IN (c.webservice) ws " +
					"WHERE ws.id = :idws AND c.params = :params"),
	@NamedQuery(name="Cache.findAll",
			query="SELECT c FROM Cache c")
})
public class Cache implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String params;

	private String result;
	
    @Temporal( TemporalType.TIMESTAMP)
	private Date reg_date;

	//bi-directional many-to-one association to Webservice
    @ManyToOne
	@JoinColumn(name="idws")
	private Webservice webservice;

    public Cache() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParams() {
		return this.params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public Date getReg_date() {
		return this.reg_date;
	}

	public void setReg_date(Date date) {
		this.reg_date = date;
	}

	public Webservice getWebservice() {
		return this.webservice;
	}

	public void setWebservice(Webservice webservice) {
		this.webservice = webservice;
	}
	
}