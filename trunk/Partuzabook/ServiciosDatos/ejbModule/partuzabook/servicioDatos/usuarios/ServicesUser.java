package partuzabook.servicioDatos.usuarios;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;

import partuzabook.datatypes.DatatypeEventSummary;
import partuzabook.datatypes.DatatypeMostTagged;
import partuzabook.datatypes.DatatypeNotification;
import partuzabook.datatypes.DatatypeUser;
import partuzabook.datos.persistencia.DAO.AdminDAO;
import partuzabook.datos.persistencia.DAO.NormalUserDAO;
import partuzabook.datos.persistencia.DAO.NotificationDAO;
import partuzabook.datos.persistencia.DAO.TagForUserDAO;
import partuzabook.datos.persistencia.beans.Admin;
import partuzabook.datos.persistencia.beans.Event;
import partuzabook.datos.persistencia.beans.NormalUser;
import partuzabook.datos.persistencia.beans.Notification;
import partuzabook.datos.persistencia.beans.User;
import partuzabook.datos.persistencia.filesystem.FileSystemLocal;
import partuzabook.entityTranslators.TranslatorNotification;
import partuzabook.entityTranslators.TranslatorUser;
import partuzabook.servicioDatos.exception.MessageTooLongException;
import partuzabook.servicioDatos.exception.UserAlreadyExistsException;
import partuzabook.servicioDatos.exception.UserNotFoundException;
import partuzabook.servicioDatos.security.CryptUtils;
import partuzabook.utils.TranslatorCollection;

/**
 * Session Bean implementation class Usuario
 */
@Stateless
public class ServicesUser implements ServicesUserRemote {

	private static final String DEFAULT_IMAGE = "profile/avatar_default.png";
	private static final String AVATAR_PATH = "profile";

	private NormalUserDAO nUserDao;
	private AdminDAO adminDao;
	private FileSystemLocal fileSystem;
	private TagForUserDAO tagForUserDao;
	private NotificationDAO notifDao;

	/**
	 * Default constructor.
	 */
	public ServicesUser() {

	}

	@PostConstruct
	public void postConstruct() {
		try {
			Properties properties = new Properties();
			properties.put("java.naming.factory.initial",
					"org.jnp.interfaces.NamingContextFactory");
			properties.put("java.naming.factory.url.pkgs",
					"org.jboss.naming rg.jnp.interfaces");
			properties.put("java.naming.provider.url", "jnp://localhost:1099");
			Context ctx = new InitialContext(properties);
			adminDao = (AdminDAO) ctx.lookup("AdminDAOBean/local");
			nUserDao = (NormalUserDAO) ctx.lookup("NormalUserDAOBean/local");
			fileSystem = (FileSystemLocal) ctx.lookup("FileSystem/local");
			tagForUserDao = (TagForUserDAO) ctx
					.lookup("TagForUserDAOBean/local");
			notifDao = (NotificationDAO) ctx
					.lookup("NotificationDAOBean/local");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PreDestroy
	public void preDestroy() {
		adminDao = null;
		nUserDao = null;
		fileSystem = null;
		tagForUserDao = null;
		notifDao = null;
	}

	public DatatypeUser createNormalUser(String username, String password,
			String mail, String name) throws UserAlreadyExistsException {
		if (existsAdminUser(username) || existsNormalUser(username)) {
			throw new UserAlreadyExistsException();
		}
		String pass = "";
		try {
			pass = CryptUtils.encript(password);
		} catch (NoSuchAlgorithmException e) {
			// Hacemos la excepcion transparente al usuario
			System.out
					.println("Error al crear un usuario, fallo la creaci�n del algoritmo MD5 para el usuario "
							+ username + " con password" + password);
			throw new UserAlreadyExistsException();
		}
		NormalUser newUser = new NormalUser();
		newUser.setUsername(username);
		newUser.setPassword(pass);
		newUser.setEmail(mail);
		newUser.setName(name);
		newUser.setImgPath("");
		newUser.setRegDate(new Timestamp(new java.util.Date().getTime()));
		newUser.setFacebookUser(false);
		nUserDao.persist(newUser);

		return (DatatypeUser) new TranslatorUser().translate(newUser);
	}

	public DatatypeUser createNormalUser(String username, String password,
			String mail, String name, long facebookId)
			throws UserAlreadyExistsException {
		if (existsAdminUser(username) || existsNormalUser(username)) {
			throw new UserAlreadyExistsException();
		}
		NormalUser newUser = new NormalUser();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setEmail(mail);
		newUser.setName(name);
		newUser.setImgPath("");
		newUser.setRegDate(new Timestamp(new java.util.Date().getTime()));
		newUser.setFacebookId(facebookId);
		newUser.setFacebookUser(true);
		nUserDao.persist(newUser);

		return (DatatypeUser) new TranslatorUser().translate(newUser);
	}

	public DatatypeUser createAdminUser(String username, String password,
			String mail, String name) throws UserAlreadyExistsException {
		if (existsAdminUser(username) || existsNormalUser(username)) {
			throw new UserAlreadyExistsException();
		}
		String pass = "";
		try {
			pass = CryptUtils.encript(password);
		} catch (NoSuchAlgorithmException e) {
			// Hacemos la excepcion transparente al usuario
			System.out
					.println("Error al crear un usuario, fallo la creaci�n del algoritmo MD5 para el usuario "
							+ username + " con password" + password);
			throw new UserAlreadyExistsException();
		}
		Admin newUser = new Admin();
		newUser.setUsername(username);
		newUser.setPassword(pass);
		newUser.setEmail(mail);
		newUser.setName(name);
		newUser.setImgPath("");
		newUser.setRegDate(new Timestamp(new java.util.Date().getTime()));

		adminDao.persist(newUser);

		return (DatatypeUser) new TranslatorUser().translate(newUser);
	}

	public DatatypeUser updateNormalUser(String username, String password,
			String mail, String name) throws UserNotFoundException {
		if (!existsNormalUser(username))
			throw new UserNotFoundException();

		NormalUser nu = nUserDao.findByID(username);
		nu.setPassword(password);
		nu.setEmail(mail);
		nu.setName(name);
		nUserDao.persist(nu);
		return (DatatypeUser) new TranslatorUser().translate(nu);
	}

	public boolean existsNormalUser(String username) {
		return nUserDao.findByID(username) != null;
	}

	public boolean existsFacebookUser(long facebookid) {
		return nUserDao.findByFacebookId(facebookid) != null;
	}

	public String getName(String username) {
		String name = "";
		NormalUser nUser = nUserDao.findByID(username);
		if (nUser != null) {
			name = nUser.getName();
		}
		return name;
	}

	public List<Boolean> existsNormalUser(List<String> usernames) {
		List<Boolean> res = new ArrayList<Boolean>();
		for (ListIterator<String> it = usernames.listIterator(); it.hasNext();) {
			res.add(existsNormalUser(it.next()));
		}
		return res;
	}

	private NormalUser getNormalUser(String username) {
		NormalUser user = nUserDao.findByID(username);
		if (user == null) {
			throw new UserNotFoundException();
		}
		return user;
	}

	public NormalUser getNormalUserByFacebookId(long facebookId) {
		NormalUser user = nUserDao.findByFacebookId(facebookId);
		if (user == null) {
			throw new UserNotFoundException();
		}
		return user;
	}

	public boolean existsAdminUser(String username) {
		return adminDao.findByID(username) != null;
	}

	private Admin getAdminUser(String username) {
		Admin user = adminDao.findByID(username);
		if (user == null) {
			throw new UserNotFoundException();
		}
		return user;
	}

	public DatatypeUser getUserForPublicProfile(String username) {
		return (DatatypeUser) new TranslatorUser()
				.translate(getNormalUser(username));
	}

	public List<DatatypeEventSummary> getEventSummaryByUser(String user) {
		NormalUser nUser = getNormalUser(user);
		List<Event> ret = nUser.getMyEvents();
		return TranslatorCollection.translateEventSummary(ret);
	}

	public String getNormalUserPassword(String username) {
		return getNormalUser(username).getPassword();
	}

	public String getNormalUserMailAddress(String username) {
		return getNormalUser(username).getEmail();
	}

	public String getAdminUserPassword(String username) {
		return getAdminUser(username).getPassword();
	}

	public byte[] getUserAvatar(String username, String thumbnail) {
		if (username.startsWith("__unr_"))
			return fileSystem.readFile(DEFAULT_IMAGE, thumbnail);
		NormalUser user = getNormalUser(username);
		if (user.isFacebookUser()) {
			URL url = null;
			try {
				url = new URL("http://graph.facebook.com/"	+ user.getFacebookId() + "/picture");
				java.awt.Image image = ImageIO.read(url);

				BufferedImage bi = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);
				Graphics g = bi.getGraphics();
				g.drawImage(image,0,0,null);
				g.dispose();

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(bi, "jpeg", baos);
				byte[] bytesOut = baos.toByteArray();

				return bytesOut;
			} catch (Exception e) {
				e.printStackTrace();
				return fileSystem.readFile(DEFAULT_IMAGE, thumbnail);
			}

		}

		if (user.getImgPath() != null && !user.getImgPath().equals(""))
			return fileSystem.readFile(user.getImgPath(), thumbnail);
		else
			return fileSystem.readFile(DEFAULT_IMAGE, thumbnail);
	}

	public List<DatatypeUser> findAllNormalUsers() {
		return TranslatorCollection.translateNormalUser(nUserDao.findAll());
	}

	private List<String> getMostTaggedUsernames() {
		List<String> list = tagForUserDao.getMostTagged();
		return list;
	}

	public List<DatatypeMostTagged> getMostTagged(int length) {
		List<String> list = getMostTaggedUsernames();
		if (list.size() > length) {
			list = list.subList(0, length);
		}
		List<NormalUser> users = new ArrayList<NormalUser>();
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String username = (String) it.next();
			users.add(getNormalUser(username));
		}
		return TranslatorCollection.translateMostTagged(users);
	}

	public byte[] getPublicAvatar(String type, int pos, String thumbnail) {
		List<String> usernames = null;
		if (type.equals("mostTagged")) {
			usernames = getMostTaggedUsernames();
		}
		if (usernames.size() >= pos) {
			return getUserAvatar(usernames.get(pos - 1), thumbnail);
		}
		return null;
	}

	public List<DatatypeNotification> getUpdateNotificationsReceived(
			String username) {
		NormalUser nUser = getNormalUser(username);
		List<Notification> notif = nUser.getNotificationsReceived();
		return TranslatorCollection.translateNotification(notif);
	}

	public List<DatatypeNotification> getUpdateNotificationsSent(String username) {
		NormalUser nUser = getNormalUser(username);
		List<Notification> notif = nUser.getNotificationsCreated();
		return TranslatorCollection.translateNotification(notif);
	}

	public DatatypeNotification createNotification(String fromUser,
			String toUser, Integer type, String message, String subject)
			throws UserNotFoundException, MessageTooLongException {

		User fUser;
		User tUser;

		if (subject != null && subject.length() > 200)
			throw new MessageTooLongException(
					"El asunto del mensaje debe tener menos de 200 caracteres.");

		if (message.length() > 5000)
			throw new MessageTooLongException(
					"El mensaje debe tener menos de 5000 caracteres.");

		if (existsNormalUser(fromUser)) {
			fUser = nUserDao.findByID(fromUser);
		} else if (existsAdminUser(fromUser)) {
			fUser = adminDao.findByID(fromUser);
		} else {
			throw new UserNotFoundException("No existe el usuario " + fromUser);
		}

		if (existsNormalUser(toUser)) {
			tUser = nUserDao.findByID(toUser);
		} else if (existsAdminUser(toUser)) {
			tUser = adminDao.findByID(toUser);
		} else {
			throw new UserNotFoundException("No existe el usuario " + toUser);
		}

		Notification not = new Notification();
		not.setNotDate(new Timestamp((new java.util.Date()).getTime()));
		not.setRead(false);
		not.setRegDate(new Timestamp((new java.util.Date()).getTime()));
		not.setText(message);
		not.setType(type);
		not.setUserFrom(fUser);
		not.setUserTo(tUser);
		not.setSubject(subject);

		notifDao.persist(not);

		// necetiso el flush para que falle persistir notification y no se mande
		// mail en ese caso
		notifDao.flush();

		return (DatatypeNotification) new TranslatorNotification()
				.translate(not);
	}

	public DatatypeUser updateUserAvatar(String username, byte[] data,
			String mime) throws UserNotFoundException {

		if (!existsNormalUser(username))
			throw new UserNotFoundException();

		NormalUser nu = nUserDao.findByID(username);
		String imgPath = fileSystem.writeFile(data, mime, AVATAR_PATH + "/"
				+ username + "/");
		String oldImage = nu.getImgPath();
		if (oldImage != null && !oldImage.equals("")
				&& !oldImage.equals(DEFAULT_IMAGE))
			fileSystem.deleteFile(oldImage);
		nu.setImgPath(imgPath);
		nUserDao.persist(nu);
		return (DatatypeUser) new TranslatorUser().translate(nu);
	}

	public boolean isNormalUserPassword(String username, String password) {
		NormalUser user = getNormalUser(username);
		try {
			return user.getPassword().equals(CryptUtils.encript(password));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error en la autenticaci�n del usuario "
					+ username + " para la contrase�a " + password);
			e.printStackTrace();
			return false;
		}
	}

	public boolean isAdminPassword(String username, String password) {
		Admin user = getAdminUser(username);
		try {
			return user.getPassword().equals(CryptUtils.encript(password));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error en la autenticaci�n del usuario "
					+ username + " para la contrase�a " + password);
			e.printStackTrace();
			return false;
		}
	}

}
