package partuzabook.superusuarioUI;

import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import partuzabook.serviciosUI.multimedia.ServicesUploadRemote;

public class ContentFeeder extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Context getContext() throws NamingException {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
		properties.put("java.naming.provider.url", "jnp://localhost:1099");
		Context ctx = new InitialContext(properties);
		return ctx;
	}

	public ServicesUploadRemote getServicesUpload() {
		try {
			Context ctx = getContext();
			return (ServicesUploadRemote)ctx.lookup("PartuzabookEAR/ServicesUpload/remote");
		}
		catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int id = 0;
		HttpSession session = (HttpSession) request.getSession(true);

		String thumbnail = "";
		if (request.getParameter("thb") != null) {
			try {
				thumbnail = request.getParameter("thb");
			}
			catch(NumberFormatException e) {

			}
		}
		int pos = 1;
		if (request.getParameter("pos") != null) {
			try {
				pos = Integer.parseInt(request.getParameter("pos"));
			}
			catch (NumberFormatException e) {
				
			}
		}
		byte[] data = null; 
		if (request.getParameter("id") != null) {
			String username = (String) session.getAttribute("username");
			try {
				id = Integer.parseInt(request.getParameter("id"));
			}
			catch (NumberFormatException e) {

			}

			data = getServicesUpload().getContent(username, id, thumbnail);
		}
		else if (request.getParameter("bestRanked") != null) {
			data = getServicesUpload().getPublicContent("bestRanked", pos, thumbnail);
		}
		else if (request.getParameter("mostCommented") != null) {
			data = getServicesUpload().getPublicContent("mostCommented", pos, thumbnail);
		}
		else {
			return;
		}

		ServletOutputStream stream = response.getOutputStream();
		stream.write(data);
	}
}
