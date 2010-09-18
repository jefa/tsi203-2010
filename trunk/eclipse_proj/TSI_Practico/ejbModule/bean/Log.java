package bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the log database table.
 * 
 */
@Entity
public class Log implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LogPK id;

	private String outcome;

	//bi-directional many-to-one association to Webservice
    @ManyToOne
	@JoinColumn(name="idws", insertable=false, updatable=false)
	private Webservice webservice;

    public Log() {
    }

	public LogPK getId() {
		return this.id;
	}

	public void setId(LogPK id) {
		this.id = id;
	}
	
	public String getOutcome() {
		return this.outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public Webservice getWebservice() {
		return this.webservice;
	}

	public void setWebservice(Webservice webservice) {
		this.webservice = webservice;
	}
	
}