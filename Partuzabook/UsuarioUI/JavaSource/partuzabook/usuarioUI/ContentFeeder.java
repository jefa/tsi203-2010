package partuzabook.usuarioUI;

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

		String username = (String) session.getAttribute("username");
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}
		catch (NumberFormatException e) {
			
		}

		int thumbnail = 0;
		if (request.getParameter("thb") != null) {
			try {
				thumbnail = Integer.parseInt(request.getParameter("thb"));
				if (thumbnail < 50) {
					thumbnail = 50;
				}
			}
			catch(NumberFormatException e) {
				
			}
		}
		byte[] data = getServicesUpload().getContent(username, id, thumbnail);
		
		ServletOutputStream stream = response.getOutputStream();
		stream.write(data);
	}
}
