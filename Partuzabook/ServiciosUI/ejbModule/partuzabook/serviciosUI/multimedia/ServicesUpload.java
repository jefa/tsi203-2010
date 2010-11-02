package partuzabook.serviciosUI.multimedia;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DataTypeFile;
import partuzabook.datatypes.DatatypeCategorySummary;
import partuzabook.datatypes.DatatypeYoutubeToken;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.servicioDatos.usuarios.ServicesUserRemote;

import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.geo.impl.GeoRssWhere;
import com.google.gdata.data.media.MediaFileSource;
import com.google.gdata.data.media.mediarss.MediaCategory;
import com.google.gdata.data.media.mediarss.MediaDescription;
import com.google.gdata.data.media.mediarss.MediaKeywords;
import com.google.gdata.data.media.mediarss.MediaTitle;
import com.google.gdata.data.youtube.FormUploadToken;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.data.youtube.YouTubeMediaGroup;
import com.google.gdata.data.youtube.YouTubeNamespace;
import com.google.gdata.data.youtube.YtPublicationState;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;


/**
 * Session Bean implementation class ServicesUpload
 */
@Stateless
public class ServicesUpload implements ServicesUploadRemote {
	
	private ServicesEventRemote servicesEvent;
	private ServicesUserRemote servicesUser;
	
	private static final String developer_key = "AI39si4Cue6KlZ4JCGoznzd54O0kgXpDR6d6eHkQEF7HNDELjc9eJ739iiN5PghEDhwARQGF7u2HWXZMSI5hilJlWfI9o4Dp0A";
	
    /**
     * Default constructor. 
     */
    public ServicesUpload() {
    }

    @PostConstruct
    public void postConstruct() {
        try {
			Context ctx = getContext();
			servicesEvent = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");  
			servicesUser = (ServicesUserRemote) ctx.lookup("PartuzabookEAR/ServicesUser/remote");
		}
        catch (NamingException e) {
			e.printStackTrace();
		}
    }
    
    @PreDestroy
    public void preDestroy() {
    	servicesEvent = null;
    }
    
	private Context getContext() throws NamingException {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
		properties.put("java.naming.provider.url", "jnp://localhost:1099");
		Context ctx = new InitialContext(properties);
		return ctx;
	}
	
	public List<String> uploadMultimedia(int eventID, String username, List<DataTypeFile> files) {
		return servicesEvent.uploadContent(eventID, username, files);
	}

	public byte[] getContent(String username, int contentID, String thumbnail) {
		return servicesEvent.getContent(username, contentID, thumbnail);
	}
	
	public byte[] getUserAvatar(String userID, String thumbnail) {
		return servicesUser.getUserAvatar(userID, thumbnail);
	}

	public byte[] getPublicContent(String type, int pos, String thumbnail) {
		return servicesEvent.getPublicContent(type, pos, thumbnail);
	}
	
	public byte[] getPublicAvatar(String type, int pos, String thumbnail) {
		return servicesUser.getPublicAvatar(type, pos, thumbnail);
	}
	
	public DatatypeYoutubeToken getYoutubeToken() {
		return uploadVideoFromBrowser();
	}
	
	public void checkVideoState(VideoEntry entry) {
		if(entry.isDraft()) {
			  System.out.println("Video is not live");
			  YtPublicationState pubState = entry.getPublicationState();
			  if(pubState.getState() == YtPublicationState.State.PROCESSING) {
			    System.out.println("Video is still being processed.");
			  }
			  else if(pubState.getState() == YtPublicationState.State.REJECTED) {
			    System.out.print("Video has been rejected because: ");
			    System.out.println(pubState.getDescription());
			    System.out.print("For help visit: ");
			    System.out.println(pubState.getHelpUrl());
			  }
			  else if(pubState.getState() == YtPublicationState.State.FAILED) {
			    System.out.print("Video failed uploading because: ");
			    System.out.println(pubState.getDescription());
			    System.out.print("For help visit: ");
			    System.out.println(pubState.getHelpUrl());
			  }
			}

	}
	
	public DatatypeYoutubeToken uploadVideoFromBrowser() {
		
		//String myvideos = "http://gdata.youtube.com/feeds/api/users/default/uploads";
		
		System.out.println("Antes de inicializar youtube");
		DatatypeYoutubeToken res = new DatatypeYoutubeToken();
		try { 
			YouTubeService service = new YouTubeService("Partuzabook", developer_key);
			//String feedUrl = "http://www.youtube.com/watch?v=06w6poJzZXE";
			//String feedUrl = "http://gdata.youtube.com/feeds/api/standardfeeds/most_viewed";

			service.setUserCredentials("partuzabook", "departuza");

			VideoEntry newEntry = new VideoEntry();
			newEntry.setGeoCoordinates(new GeoRssWhere(-34.831,-56.2055));
			
			newEntry.setLocation("Montevideo, Uruguay");
			YouTubeMediaGroup mg = newEntry.getOrCreateMediaGroup();

			//TODO: Categoria
			mg.addCategory(new MediaCategory(YouTubeNamespace.CATEGORY_SCHEME, "Autos"));
			mg.addCategory(new MediaCategory(YouTubeNamespace.DEVELOPER_TAG_SCHEME, "xyzzy"));
			
			mg.setTitle(new MediaTitle());
			mg.setPrivate(false);
			
			Date d = new Date();
			String time_id = "" + d.getTime();
			
			mg.getTitle().setPlainTextContent(time_id);
			mg.setKeywords(new MediaKeywords());
			
			mg.getKeywords().addKeyword("Partuzabook");
			
			mg.setDescription(new MediaDescription());
			mg.getDescription().setPlainTextContent("Partuzabook content added at " + time_id);

			URL uploadUrl = new URL("http://gdata.youtube.com/action/GetUploadToken");
			FormUploadToken token = service.getFormUploadToken(uploadUrl, newEntry);
			
			//System.out.println(token.getUrl() + "?nexturl=http://localhost:8080/paginaInicio.jsf");
			//System.out.println(token.getToken());
			
			res.setId(time_id);
			res.setPost_url(token.getUrl());
			res.setToken_id(token.getToken());
			
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			System.out.println("Error de autenticacion");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error de generacion de url");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error de io");
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			System.out.println("Error del servicio - Youtube");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	private void uploadFromFile() {
		try { 
		
			YouTubeService service = new YouTubeService("Partuzabook", developer_key);
			service.setUserCredentials("partuzabook", "departuza");
			
			VideoEntry newEntry = new VideoEntry();
			newEntry.setGeoCoordinates(new GeoRssWhere(37.0,-122.0));
			newEntry.setLocation("Montevideo, Uruguay");
	
			YouTubeMediaGroup mg = newEntry.getOrCreateMediaGroup();
			//TODO: Ver que categorias deberia setearle a los videos
			mg.addCategory(new MediaCategory(YouTubeNamespace.CATEGORY_SCHEME, "Autos"));
			mg.addCategory(new MediaCategory(YouTubeNamespace.DEVELOPER_TAG_SCHEME, "xyzzy"));
			
			mg.setPrivate(false);
			mg.setTitle(new MediaTitle());
			mg.getTitle().setPlainTextContent("testing");
			mg.setKeywords(new MediaKeywords());
			mg.getKeywords().addKeyword("foo");
			mg.setDescription(new MediaDescription());
			mg.getDescription().setPlainTextContent("my description");
			File f = new File("C:/test.avi");
			MediaFileSource ms = new MediaFileSource(f, "video/quicktime");
			newEntry.setMediaSource(ms);
	
			String uploadUrl = "http://uploads.gdata.youtube.com/feeds/api/users/default/uploads";
	
			VideoEntry createdEntry = service.insert(new URL(uploadUrl), newEntry);
			
			checkVideoState(createdEntry);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*
	public void test(String post_url, String token_value){
		
		System.out.println("Antes de inicializar youtube");
		try { 
			YouTubeService service = new YouTubeService("Partuzabook", developer_key);
			//String feedUrl = "http://www.youtube.com/watch?v=06w6poJzZXE";
			//String feedUrl = "http://gdata.youtube.com/feeds/api/standardfeeds/most_viewed";
			String feedUrl = "http://gdata.youtube.com/feeds/api/users/partuzabook/uploads";
			service.setUserCredentials("partuzabook", "departuza");
			
			
			String videoEntryUrl = "http://gdata.youtube.com/feeds/api/videos/06w6poJzZXE";
			VideoEntry videoEntry = service.getEntry(new URL(videoEntryUrl), VideoEntry.class);
			System.out.println("Title: " + videoEntry.getTitle().getPlainText());
			System.out.println(videoEntry.getMediaGroup().getDescription().getPlainTextContent());
			if(videoEntry.getMediaSource() == null) {
				System.out.println("MediaSource es null");
			} else {
				System.out.println(videoEntry.getMediaSource().getContentLength());
				System.out.println(videoEntry.getMediaSource().getContentType());
			}
			
			
			VideoFeed videoFeed = service.getFeed(new URL(feedUrl), VideoFeed.class);
			for(VideoEntry entry : videoFeed.getEntries() ) {
				  System.out.println("ID del video: " + entry.getId());
				  System.out.println("Title: " + entry.getTitle().getPlainText());
				  System.out.println(entry.getMediaGroup().getDescription().getPlainTextContent());
				  
			}

			
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
	public int confirmUploadVideo(int eventId, String creator,
			String youtube_id, String description) {
		return servicesEvent.uploadYoutubeVideo(eventId, creator, youtube_id, description);
		
	}	
}
