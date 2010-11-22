package partuzabook.usuarioUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import partuzabook.datatypes.DataTypeFile;
import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeFileAux;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;

public class Profile {

	private static final String INPUT_OBLIG = "Campo obligatorio";
	private static final String PASS_NOT_EQUALS = "Las contrase�as no coinciden";
	private static final String BAD_EMAIL = "El email ingresado no es un email valido";
	private static final String SERVICES_USER_REMOTE = "PartuzabookEAR/ServicesUser/remote";

	private List<DatatypeEventSummary> eventosDelUsuario;
	private int cantEventosDelUsuario = -1;
	private DatatypeUser user;
	private String userId;

	private String newPassword;
	private String confirmNewPassword;

	// Para el cambio de avatar
	private byte[] imgData;
	private String mime;
	private int cantUploads;

	public void setUserId(String userId) {
		System.out.println("Llame a setUserName");
		this.userId = userId;
		try {
			Context ctx = getContext();
			ServicesUserRemote servicesUser = (ServicesUserRemote) ctx
					.lookup("PartuzabookEAR/ServicesUser/remote");
			this.user = servicesUser.getUserForPublicProfile(userId);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: Redirigir a p�gina de error
			e.printStackTrace();
		}
	}

	public String getUserId() {
		return this.userId;
	}

	public void setEventosDelUsuario(
			List<DatatypeEventSummary> eventosDelUsuario) {
		this.eventosDelUsuario = eventosDelUsuario;
	}

	public List<DatatypeEventSummary> getEventosDelUsuario() {
		try {
			Context ctx = getContext();
			ServicesUserRemote servicesUser = (ServicesUserRemote) ctx
					.lookup(SERVICES_USER_REMOTE);
			this.eventosDelUsuario = servicesUser.getEventSummaryByUser(userId);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: Redirigir a pagina de error
			e.printStackTrace();
			this.eventosDelUsuario = new ArrayList<DatatypeEventSummary>();
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

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword() {
		return this.newPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	public String getConfirmNewPassword() {
		return this.confirmNewPassword;
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

	public void listener(UploadEvent event) throws Exception {
		UploadItem item = event.getUploadItem();
		String name = item.getFileName();
		String mime = "";
		int extDot = name.lastIndexOf('.');
		if (extDot > 0) {
			String extension = name.substring(extDot + 1);
			if ("bmp".equals(extension)) {
				mime = "image/bmp";
			} else if ("jpg".equals(extension)) {
				mime = "image/jpeg";
			} else if ("gif".equals(extension)) {
				mime = "image/gif";
			} else if ("png".equals(extension)) {
				mime = "image/png";
			} else {
				mime = "image/unknown";
			}
		}
		imgData = item.getData();
		this.mime = mime;

	}

	public String confirmAvatarChange() {
		try {
			Context ctx = getContext();
			ServicesUserRemote servicesUser = (ServicesUserRemote) ctx
					.lookup(SERVICES_USER_REMOTE);
			user = servicesUser.updateUserAvatar(user.username, imgData, mime);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "verMiPerfil";
	}

	public String userModification() {

		try {
			Context ctx = getContext();
			ServicesUserRemote servicesUser = (ServicesUserRemote) ctx
					.lookup(SERVICES_USER_REMOTE);

			DatatypeUser nu = servicesUser.updateNormalUser(user.username,
					newPassword, user.email, user.name);
			user = nu;
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "success";
	}

	public String passModification() {

		try {
			Context ctx = getContext();
			ServicesUserRemote servicesUser = (ServicesUserRemote) ctx
					.lookup(SERVICES_USER_REMOTE);

			DatatypeUser nu = servicesUser.updateNormalUser(user.username,
					newPassword, user.email, user.name);
			user = nu;

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "success";
	}

	public void setCantUploads(int cantUploads) {
		this.cantUploads = cantUploads;
	}

	public int getCantUploads() {
		// return cantUploads;
		return 5;
	}
}
