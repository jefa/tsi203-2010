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

public class GetTagsForContent extends HttpServlet {

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

		Integer id = 0;
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		
		String username = (String) session.getAttribute("username");
		
		DatatypeContent content = getServicesEvent().getContentDetails(id, username);
		PrintWriter stream = response.getWriter();
		stream.write("[");
		Iterator<DatatypeTag> it = content.getTags().iterator();
		while (it.hasNext()) {
			DatatypeTag datatypeTag = (DatatypeTag) it.next();
			stream.write("{");
			stream.write("\"top\":" + datatypeTag.getPosY() + ", ");
			stream.write("\"left\":" + datatypeTag.getPosX() + ", ");
			stream.write("\"width\":" + 100 + ", ");
			stream.write("\"height\":" + 100 + ", ");
			stream.write("\"text\":\"" + datatypeTag.getName() + "\", ");
			stream.write("\"id\":\"" + (datatypeTag.getIsRealUser() ? datatypeTag.getUserName() :
				"__unr_" + datatypeTag.getName()) + "\", ");
			stream.write("\"editable\":true");
			stream.write("},");
		}
		
		stream.write("]");
	}
}
