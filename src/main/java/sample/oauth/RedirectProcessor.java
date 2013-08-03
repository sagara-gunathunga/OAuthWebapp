package sample.oauth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Class RedirectProcessor.
 * 
 * @author Sagara Gunathunga
 * 
 */
@WebServlet(value = "/oauth", loadOnStartup = 1)
public class RedirectProcessor extends OAuthProcessor {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("oauth2.jsp");
		dispatcher.forward(request, response);
	}

}
