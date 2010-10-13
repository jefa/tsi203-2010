package partuzabook.usuarioUI;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;

public class PublicProfile {

	private List<DatatypeEventSummary> eventosDelUsuario;
	private int cantEventosDelUsuario = -1;
	private DatatypeUser user;
	private String userId;
	
	public void setUserId(String userId) {
		System.out.println("Llame a setUserName");
		this.userId = userId;
		try {
			Context ctx = getContext();
			ServicesUserRemote servicesUser = (ServicesUserRemote)ctx.lookup("PartuzabookEAR/ServicesUser/remote");
			this.user = servicesUser.getUserForPublicProfile(userId);
		} catch (NamingException e) {
			e.printStackTrace();
		}		
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setEventosDelUsuario(List<DatatypeEventSummary> eventosDelUsuario) {
		this.eventosDelUsuario = eventosDelUsuario;
	}
	
	public List<DatatypeEventSummary> getEventosDelUsuario() {
			try {
				Context ctx = getContext();
				ServicesUserRemote servicesUser = (ServicesUserRemote)ctx.lookup("PartuzabookEAR/ServicesUser/remote");
				this.eventosDelUsuario = servicesUser.getEventSummaryByUser(userId);
			} catch (NamingException e) {
				e.printStackTrace();
			}	
		return this.eventosDelUsuario;
	}
	
	public void setCantEventosDelUsuario(int cantEventosDelUsuario) {
		this.cantEventosDelUsuario = cantEventosDelUsuario;
	}
	
	public int getCantEventosDelUsuario() {
		this.cantEventosDelUsuario = getEventosDelUsuario().size();
		return this.cantEventosDelUsuario;
	}
	
	public void setUser(DatatypeUser user) {
		this.user = user;
	}
	
	public DatatypeUser getUser() {
		return this.user;
	}
	
	private Context getContext() throws NamingException {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs",
				"org.jboss.naming rg.jnp.interfaces");
		properties.put("java.naming.provider.url", "jnp://localhost:1099");
		Context ctx = new InitialContext(properties);
		return ctx;
	}
	
	public String enviarPM() {
		System.out.println("Llame a enviarPM");
		return "";
	}
}
