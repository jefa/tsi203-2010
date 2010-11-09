package partuzabook.superusuarioUI;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionUtils {
	static private HttpSession getSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpSession) context.getExternalContext()
				.getSession(true);
	}
	
	static public String getUsername() {
		if (getSession().getAttribute("username") == null) {
			return "";
		}
		return (String)getSession().getAttribute("username");
	}
	
	static public Object getAttribute(String attribute) {
		return getSession().getAttribute(attribute);
	}
}
