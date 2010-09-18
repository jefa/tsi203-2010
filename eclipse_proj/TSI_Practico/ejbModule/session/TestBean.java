/*
 * HelloWorldBean.java
 *
 * Created on April 29, 2008, 4:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package session;

import java.util.Date;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import manager.IWebserviceManager;
import manager.managerFactoryLocal;
import bean.Webservice;

/**
 *
 * @author bijul
 */
@Stateless
public class TestBean implements TestRemote, TestLocal {
    
	private Properties props;
	private InitialContext ic;
	
	@PersistenceContext
	EntityManager em;
	
    /** Creates a new instance of HelloWorldBean */
    public TestBean() {
    	props = new Properties();
		props.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
		props.setProperty( "java.naming.provider.url", "127.0.0.1:1099" );
	
		try {
			ic = new InitialContext(props);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.out.println("[JPAClient]Excepcion en la creacion del JPAClient, no esta pudiendo obtener el InitialContext");
			e.printStackTrace();
		}
		
    }

    public String sayHello(String str) {
        //TODO implement sayHello
    	//IWebserviceManager ws = new managerFactoryBean().getIWebserviceManager();
    	try {
    		
    		managerFactoryLocal mFL = (managerFactoryLocal) ic.lookup("TSI_Practico_EAR/managerFactoryBean" + "/local");
    	
    		IWebserviceManager iwsm = mFL.getIWebserviceManager();
    		iwsm.create("my webservice", "URL1");
    		
    		//ws.create("jojojo", "url");
    		/*
    		int size = em.createQuery("SELECT w FROM Webservice w").getResultList().size();
    		
    		Webservice ws2 = new Webservice();
    		ws2.setName("names");
    		ws2.setUrl("url1");
    		ws2.setId(size);
    		em.persist(ws2);
    		*/
			//ws.create("name1", "URL1");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return "OKS";
    }

}
