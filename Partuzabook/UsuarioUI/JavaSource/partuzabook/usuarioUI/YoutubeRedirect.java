package partuzabook.usuarioUI;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import partuzabook.datatypes.DatatypeYoutubeToken;
import partuzabook.serviciosUI.multimedia.ServicesUploadRemote;

public class YoutubeRedirect extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ERROR_PAGE = "http://localhost:8080/UsuarioUI/subirVideo.jsf";
	private static final String FINISH_UPLOAD_PAGE = "http://localhost:8080/UsuarioUI/videoAddDescription.jsf";
	private static final String NEXT_URL = "http://localhost:8080/UsuarioUI/YoutubeRedirect?username=#{loginMB.userName}";
	private static final String SERVICE_UPLOAD = "PartuzabookEAR/ServicesUpload/remote";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getSession().getAttribute("username");
		String action = (String) request.getParameter("action");
		//Se retorno del upload del browser		
		String redirect = ERROR_PAGE;
		if(username != null && !username.equals("")){
			//Redirigo a la pagina de inicio, seteando el id que paso youtube
			String status = request.getParameter("status");
			if(status != null && !status.equals("")){
				request.getSession().setAttribute("youtube_eror", status);			
				if(status.equals("200")) {
					//Subida de video ok
					request.getSession().setAttribute("youtube_id", request.getParameter("id"));	
					redirect = FINISH_UPLOAD_PAGE;
				}		
			}
		}
		response.sendRedirect(redirect);
	
	}
	
	public String getYoutubeFormToken() {
		String youtubeFormToken;
	
		ServicesUploadRemote service = getServicesUpload(); 
		DatatypeYoutubeToken token = service.getYoutubeToken();
		youtubeFormToken = "<form action=\"";
		youtubeFormToken += token.getPost_url();
		youtubeFormToken +=	"?nexturl=" + NEXT_URL;
		youtubeFormToken +=	"\" method =\"post\" enctype=\"multipart/form-data\"><input type=\"file\" name=\"file\"/><input type=\"hidden\" name=\"token\" value=\"";
		youtubeFormToken +=	token.getToken_id();
		youtubeFormToken +=	"\"/><input type=\"submit\" value=\"go\" /></form>";
		
		return youtubeFormToken;
	}
	
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
			return (ServicesUploadRemote)ctx.lookup(SERVICE_UPLOAD);
		}
        catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
}
