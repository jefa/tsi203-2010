package manager;

import javax.ejb.Local;

@Local
public interface managerFactoryLocal {

	public ICacheManager getICacheManager();
	public ILogManager getILogManager();
	public IWebserviceManager getIWebserviceManager();
}
