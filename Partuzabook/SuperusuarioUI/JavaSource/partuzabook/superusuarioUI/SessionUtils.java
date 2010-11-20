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
	
	static public Integer getEventId() {
		HttpSession session = getSession();
		if (session.getAttribute("evtid") == null) {
			return -1;
		}
		Integer eventId = (Integer) session.getAttribute("evtid");
		session.removeAttribute("evtid");
		return  eventId;
	}
	
	static public Object getAttribute(String attribute) {
		return getSession().getAttribute(attribute);
	}
}
