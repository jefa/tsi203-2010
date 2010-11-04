package partuzabook.usuarioUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Authentication
 */
public class Authentication implements Filter {
	private List<String> publicURLs;

	/**
	 * Default constructor.
	 */
	public Authentication() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		String url = req.getServletPath();
		//System.out.println("MIERDA:" + url);
		boolean isPublic = false;
		for (int i = 0; i < publicURLs.size(); i++) {
			if (url.contains(publicURLs.get(i))) {
				isPublic = true;
				break;
			}
		}
		// TODO comentar cuando la autenticacion este funcionando
		//isPublic = true;

		/*
		 * if (isPublic) { if (url.endsWith("login.jsf")) { if (session == null)
		 * { session = req.getSession(true); } if
		 * (session.getAttribute("username") == null) { //TODO Realizar la
		 * verificacion del usuario if(resp.toString() == "success") {
		 * System.out.println("USUARIO AUTENTICADO");
		 * session.setAttribute("username", true); } else {
		 * System.out.println("ERROR DE AUTENTICACION"); } } else {
		 * resp.sendRedirect("index.jsf"); return; } } } else {
		 */
		if (!isPublic && (session == null || session.getAttribute("username") == null)) {
			System.out.println("Authentication.doFilter(): ACCESO NO AUTORIZADO");
			resp.sendRedirect("login.jsf");
			
			return;
		}
		// }
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		publicURLs = new ArrayList<String>();
		publicURLs.add("/paginaInicio.jsf");
		publicURLs.add("/busquedaEventos.jsf");
		publicURLs.add("/login.jsf");
		publicURLs.add("/evento.jsf");
		publicURLs.add("/register.jsf");
		publicURLs.add("/ContentFeeder");
		publicURLs.add("/UserImageFeeder");
		publicURLs.add("/images");
		publicURLs.add("/styles");
		publicURLs.add("/scripts");
		publicURLs.add("/a4j/");
		publicURLs.add("/index.jsp");
		publicURLs.add("/xd_receiver.htm");
		publicURLs.add("/FacebookConnect");
	}

}
