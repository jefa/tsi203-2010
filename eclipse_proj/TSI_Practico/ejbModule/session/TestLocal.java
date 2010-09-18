
package session;

import javax.ejb.Local;


/**
 * This is the business interface for HelloWorld enterprise bean.
 */
@Local
public interface TestLocal {
    String sayHello(String str);
    
}
