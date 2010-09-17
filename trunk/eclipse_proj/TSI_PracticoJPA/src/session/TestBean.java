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

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import manager.IWebserviceManager;
import manager.managerFactoryBean;
import bean.Webservice;

/**
 *
 * @author bijul
 */
@Stateless
public class TestBean implements TestRemote, TestLocal {
    
	@PersistenceContext
	EntityManager em;
	
    /** Creates a new instance of HelloWorldBean */
    public TestBean() {
    }

    public String sayHello(String str) {
        //TODO implement sayHello
    	IWebserviceManager ws = new managerFactoryBean().getIWebserviceManager();
    	try {
    		
    		ws.create("jojojo", "url");
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
