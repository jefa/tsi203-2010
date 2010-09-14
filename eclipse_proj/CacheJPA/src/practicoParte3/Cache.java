package practicoParte3;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Cache database table.
 * 
 */
@Entity
@Table(name="Cache")
public class Cache implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String params;

	private String result;

	//bi-directional many-to-one association to WebService
    @ManyToOne
	@JoinColumn(name="idWS")
	private WebService webService;

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

	public WebService getWebService() {
		return this.webService;
	}

	public void setWebService(WebService webService) {
		this.webService = webService;
	}
	
}