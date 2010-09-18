
package session;

import javax.ejb.EJBHome;
import javax.ejb.Remote;


/**
 * This is the business interface for HelloWorld enterprise bean.
 */
@Remote
public interface TestRemote {
    String sayHello(String str);
    
}
