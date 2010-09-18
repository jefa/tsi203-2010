package manager;

import java.util.Properties;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class managerFactoryBean implements managerFactoryLocal{

	@PersistenceContext(unitName="TSI_Practico")
	private EntityManager em;
	
	private Properties props;
	private InitialContext ic;
	
	private static ICacheManager cm;
	private static final String cmJNDI = "TSI_Practico_EAR/CacheManager" + "/local";
	private static ILogManager lm;
	private static final String lmJNDI = "TSI_Practico_EAR/LogManager" + "/local";
	private static IWebserviceManager wm;
	private static final String wmJNDI = "TSI_Practico_EAR/WebserviceManager" + "/local";

	public managerFactoryBean() {
		props = new Properties();
		props.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
		props.setProperty( "java.naming.provider.url", "127.0.0.1:1099" );
	
		try {
			ic = new InitialContext(props);
		} catch (NamingException e) {
			System.out.println("[managerFactoryBean]Excepcion en la creacion del managerFactoryBean, no esta pudiendo obtener el InitialContext");
			e.printStackTrace();
		}
	}
	
	public ICacheManager getICacheManager() {
		if (cm == null) { 
			if(em==null){
				try {
					cm = (ICacheManager)ic.lookup(cmJNDI);
				} catch (NamingException e) {
					System.out.println("[managerFactoryBean]Excepcion en la creacion del ICacheManager, no esta pudiendo obtener la inerfaz local a partir de 'lookup'");
					e.printStackTrace();
				}
			} else {
				cm = new CacheManager(em);
			}
		}
		return cm;
	}

	public ILogManager getILogManager() {
		if (lm == null) 
			if(em == null) {
				try {
					lm = (ILogManager)ic.lookup(lmJNDI);
				} catch (NamingException e) {
					System.out.println("[managerFactoryBean]Excepcion en la creacion del ILogManager, no esta pudiendo obtener la inerfaz local a partir de 'lookup'");
					e.printStackTrace();
				}
			} else {
				lm = new LogManager(em);
			}
		return lm;
	}

	public IWebserviceManager getIWebserviceManager() {
		if (wm == null) 
			if(em == null) {
				try {
					wm = (IWebserviceManager)ic.lookup(wmJNDI);
					wm.create("ALOHOMORA DESDE EL MANAGER FACTORY", "ASD");
				} catch (NamingException e) {
					System.out.println("[managerFactoryBean]Excepcion en la creacion del IWebserviceManager, no esta pudiendo obtener la inerfaz local a partir de 'lookup'");
					e.printStackTrace();
				}
			} else { 
				wm = new WebserviceManager(em);
			}
		return wm;
	}
	
}
