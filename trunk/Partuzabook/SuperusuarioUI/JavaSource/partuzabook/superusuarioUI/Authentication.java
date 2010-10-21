package partuzabook.superusuarioUI;

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

		boolean isPublic = false;
		for (int i = 0; i < publicURLs.size(); i++) {
			if (url.contains(publicURLs.get(i))) {
				isPublic = true;
				break;
			}
		}

		if (!isPublic && (session == null || session.getAttribute("username") == null)) {
			System.out.println("Authentication.doFilter(): ACCESO NO AUTORIZADO");
			resp.sendRedirect("login.jsf");
			
			return;
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		publicURLs = new ArrayList<String>();
		publicURLs.add("/login.jsf");
		publicURLs.add("/images");
		publicURLs.add("/styles");
		publicURLs.add("/scripts");
		publicURLs.add("/a4j/");
	}

}
