package partuzabook.usuarioUI;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import partuzabook.serviciosNotificaciones.email.PartuzaMailer;

public class MailController extends HttpServlet {

	private static final long serialVersionUID = 3492838554584566607L;

	/** static final HTML setting for content type */
	private static final String HTML = "text/html";
 
	/** static final HTML setting for content type */
	private static final String PLAIN = "text/plain";
 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request, response);
	}
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setContentType(PLAIN);
		PrintWriter out = response.getWriter();
		String mailToken = "token";
		
		HttpSession session = (HttpSession) request.getSession(true);
		String username = (String) session.getAttribute("username");
		String toAddr = request.getParameter("to");
		
		String body = "Hello there, " + "\n\n"
		          + "Wanna play a game of golf?" + "\n\n"
			  + "Please confirm: https://localhost:8443/myapp/confirm?token="
			  + mailToken + "\n\n" + "-Golf USA";
		 new PartuzaMailer().sendMail(null, "javier.fradiletti@sabre.com", null, null, "subject prueba",
				body, PLAIN);
		//mailer.sendMsg("recipient@gmail.com", "Golf Invitation!", body);
		out.println("Message Sent");
	}
}
