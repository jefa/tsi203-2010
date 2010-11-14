package partuzabook.usuarioUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.code.facebookapi.FacebookException;
import com.google.code.facebookapi.FacebookJsonRestClient;
import com.google.code.facebookapi.ProfileField;

import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;
import partuzabook.serviciosUI.multimedia.ServicesUploadRemote;

public class FacebookConnect extends HttpServlet {

	private static final long serialVersionUID = 1L;

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

	public static String getCookieValue(String cookiekey,
			HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookiekey)) {
				return cookie.getValue();
			}
		}
		return null;
	}

	public static FacebookJsonRestClient getFacebookJsonRestClient(
			HttpServletRequest req) {
		String sessionKey = getCookieValue("70ba156f811176d021c2162f5c4c0150"
				+ "_session_key", req);

		return new FacebookJsonRestClient("70ba156f811176d021c2162f5c4c0150",
				"14481b067e1307dc53b6101604bc78e3", sessionKey);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FacebookJsonRestClient fbc = getFacebookJsonRestClient(request);
		long userId = 0;
		String userName = "";
		
		try {
			userId = fbc.users_getLoggedInUser();
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("facebook userId:" + userId);
		
		ServicesUserRemote servicesUser = null;
		try {
			Context ctx = getContext();
			servicesUser = (ServicesUserRemote) ctx
					.lookup("PartuzabookEAR/ServicesUser/remote");
			if (!servicesUser.existsFacebookUser(userId)) 
			{
				List<ProfileField> fields=new ArrayList<ProfileField>();
				fields.add(ProfileField.FIRST_NAME);
				fields.add(ProfileField.LAST_NAME);
				fields.add(ProfileField.EMAIL_HASHES);
				fields.add(ProfileField.PROXIED_EMAIL);

				List<Long> userIds=new ArrayList<Long>();
				userIds.add(userId);
				JSONArray ja=(JSONArray)fbc.users_getInfo(userIds,fields);
				JSONObject jo=null;

				if(ja!=null)
				 jo=ja.getJSONObject(0);

				if(jo!=null){
				  System.out.println("facebook response:"+jo.toString(2));
				  String firstName=jo.getString(ProfileField.FIRST_NAME.toString());
				  String lastName=jo.getString(ProfileField.LAST_NAME.toString());
				  userName = (firstName.substring(0,2) + lastName).toLowerCase();
				  // buscar una forma de que no se repitan los username
				  servicesUser.createNormalUser(userName,String.valueOf(UUID.randomUUID()), "", firstName + " " + lastName, userId); // uso uuid para que no se puedan loguear
				}
			}
			else
			{
				NormalUser usuario = servicesUser.getNormalUserByFacebookId(userId);
				userName = usuario.getUsername();
			}
			
			HttpSession session = request.getSession(false);
			session.setAttribute("username", userName); 


		} catch (NamingException ne) {
			// TODO: Redirigir a p�gina de error
			ne.printStackTrace();
		} catch (Exception e) {
			// TODO: Reedirigir a una p�gina de error
			e.printStackTrace();
		}

		response.sendRedirect(request.getParameter("back"));
	}
}
