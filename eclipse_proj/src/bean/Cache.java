package bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cache database table.
 * 
 */
@Entity
public class Cache implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String params;

	private String result;

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

	public Webservice getWebservice() {
		return this.webservice;
	}

	public void setWebservice(Webservice webservice) {
		this.webservice = webservice;
	}
	
}