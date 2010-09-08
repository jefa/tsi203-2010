package edu.tsi.Practico.client;

import javax.ejb.EJB;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import edu.tsi.Practico.Session1;
import edu.tsi.Practico.Session1Remote;

public class SessionClient {
	
    //@EJB (name="Session1Remote")
    private static Session1Remote sess1;

    public SessionClient(String[] args) {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	SessionClient client = new SessionClient(args);
    	System.out.println("===== MENSAJE::: " + client.doTest());
        System.exit(0);
    }

    public String doTest() {
        try {
        	
        	//String compName = "TSI_Practico_EAR/Session1/remote";
        	//String compName = "TSI_Practico_EAR/Session1";
        	//String path = "java:comp/env/ejb/miBean";
        	//String compName = "java:DefaultDS";
        	String compName = "java:TSI_Practico_EAR/Session1Remote/remote";
        	
        	
        	Properties props = new Properties();
        	props.setProperty( "java.naming.factory.initial",
        		"org.jnp.interfaces.NamingContextFactory" );
        	props.setProperty( "java.naming.provider.url", "127.0.0.1:1099" );
        	//props.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming" );        	
        	
        	Hashtable env = new Hashtable();
        	env.put(Context.INITIAL_CONTEXT_FACTORY,
        		"com.sun.jndi.ldap.LdapCtxFactory");
  	  			//"com.sun.enterprise.naming.SerialInitContextFactory");
        	env.put(Context.PROVIDER_URL,
        	  "127.0.0.1:1099");
        	Context initialContext = new InitialContext(props);        	
        	Object obj = initialContext.lookup(compName);
        	System.out.println("SessionClient.doTest() clase es :: "+obj.getClass());
        	sess1 = (Session1Remote) obj;
        	return sess1.getMessage();

        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
            return "ERROR";
        }
    }
}
