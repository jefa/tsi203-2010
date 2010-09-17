package bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the webservice database table.
 * 
 */
@Entity
public class Webservice implements Serializable {
	private static final long serialVersionUID = 1L;

	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Integer id;

	private String name;

	private String url;

	//bi-directional many-to-one association to Cache
	@OneToMany(mappedBy="webservice")
	private Set<Cache> caches;

	//bi-directional many-to-one association to Log
	@OneToMany(mappedBy="webservice")
	private Set<Log> logs;

    public Webservice() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Cache> getCaches() {
		return this.caches;
	}

	public void setCaches(Set<Cache> caches) {
		this.caches = caches;
	}
	
	public Set<Log> getLogs() {
		return this.logs;
	}

	public void setLogs(Set<Log> logs) {
		this.logs = logs;
	}
	
}