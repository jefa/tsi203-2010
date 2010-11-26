package partuzabook.integracion.ws.contenido;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import partuzabook.datatypes.DatatypeContent;
import partuzabook.servicioDatos.eventos.ServicesEventRemote;
import partuzabook.utils.Parameters;

@Stateless
@WebService(
   name="Contenido",
   targetNamespace = "http://edu.tsi2.ws/integracion/ws/contenido",
   serviceName = "ContenidoService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class ContenidoBean {
	
	@WebMethod
	public ContentResponse getAlbumContents(ContentRequest request){
		
		ContentResponse resp = new ContentResponse();
		
		try {
			List<DatatypeContent> contents = getServicesEvent().getAlbumContents(request.getEventId());
			
			if (contents == null) {
				System.out.println("ContenidoBean.getAlbumContents(): No se han encontrado resultados");
				return resp;
			}

			resp.contenido = translate(contents);
			resp.total = new Integer(contents.size());
			
		} catch (NamingException e) {
			System.out.println("ContenidoBean.getAlbumContents(): Error haciendo la busqueda: "+e.getMessage());
			e.printStackTrace();
		}
		
		return resp;
		
	}

	private ServicesEventRemote getServicesEvent() throws NamingException {
		Context ctx = getContext();
		ServicesEventRemote srvEvent = (ServicesEventRemote) ctx.lookup("PartuzabookEAR/ServicesEvent/remote");
		return srvEvent;
	}
	
	private Context getContext() throws NamingException {
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs", "org.jboss.naming rg.jnp.interfaces");
		properties.put("java.naming.provider.url", "jnp://localhost:1099");
		Context ctx = new InitialContext(properties);
		return ctx;
	}
	
	private List<Contenido_Type> translate(List<DatatypeContent> contents){
		List<Contenido_Type> translatedCollection = new ArrayList<Contenido_Type>();
		int i=0;
		for (Iterator iterator = contents.iterator(); iterator.hasNext();) {
			DatatypeContent datatypeContent = (DatatypeContent) iterator.next();
			System.out.println("ContenidoBean.getAlbumContents(): Encotnrado contenido "+datatypeContent.getDescription());
			Contenido_Type content = new Contenido_Type();
			content.setDescripcion(datatypeContent.getDescription());
			if (datatypeContent.getUrl().contains("http://")) {
				content.setUrl(datatypeContent.getUrl());
			}
			else {
				content.setUrl("http://"+Parameters.PARTUZABOOK_IP+"/UsuarioUI/ContentFeeder?id="+datatypeContent.getUrl());
			}
			translatedCollection.add(content);
			i++;
		}
		return translatedCollection;
	}
}
