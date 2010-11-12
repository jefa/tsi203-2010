package partuzabook.usuarioUI;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.datatypes.DatatypeTag;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;

public class DeleteTagsForContent extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Context getContext() throws NamingException {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
		properties.put("java.naming.provider.url", "jnp://localhost:1099");
		Context ctx = new InitialContext(properties);
		return ctx;
	}

	public ServicesEventRemote getServicesEvent() {
		try {
			Context ctx = getContext();
			return (ServicesEventRemote)ctx.lookup("PartuzabookEAR/ServicesEvent/remote");
		}
		catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		HttpSession session = (HttpSession) request.getSession(true);

		Integer id = Integer.parseInt(request.getParameter("contentId"));
		String userId = request.getParameter("id");
		String username = (String) session.getAttribute("username");
		Boolean userToRemoveIsReal = !userId.startsWith("__unr_");
		String userToRemove;
		if (!userToRemoveIsReal) {
			userToRemove = userId.split("__unr_")[1];
		}
		else {
			userToRemove = userId;
		}
		
		try {
			getServicesEvent().removeTagInContent(id, username, userToRemoveIsReal, userToRemove);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
