package practicoParte3;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Log database table.
 * 
 */
@Entity
@Table(name="Log")
public class Log implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LogPK id;

	private String outcome;

	//bi-directional many-to-one association to WebService
    @ManyToOne
	@JoinColumn(name="idWS")
	private WebService webService;

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

	public WebService getWebService() {
		return this.webService;
	}

	public void setWebService(WebService webService) {
		this.webService = webService;
	}
	
}