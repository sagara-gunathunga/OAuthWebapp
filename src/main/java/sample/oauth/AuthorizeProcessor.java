package sample.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.amber.oauth2.client.request.OAuthClientRequest;
import org.apache.amber.oauth2.common.exception.OAuthSystemException;

/**
 * The Class AuthorizeProcessor.
 * 
 * @author Sagara Gunathunga
 * 
 */
@WebServlet(value = "/authorize", loadOnStartup = 1)
public class AuthorizeProcessor extends OAuthProcessor {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Configuration data
		String authzEndpoint = getConfigProperty("authzEndpoint");
		String callbackurl = getConfigProperty("callbackurl");

		// Form data specific to the application.
		String clientId = request.getParameter("client_id");
		String responseType = request.getParameter("response_type");
		String scope = request.getParameter("scope");
		String state = request.getParameter("state");

		OAuthClientRequest authzRequest;
		try {

			authzRequest = OAuthClientRequest
					.authorizationLocation(authzEndpoint).setClientId(clientId)
					.setRedirectURI(callbackurl).setResponseType(responseType)
					.setScope(scope).buildQueryMessage();

			System.out
					.println("LocationUri : " + authzRequest.getLocationUri());
			response.sendRedirect(authzRequest.getLocationUri());
			return;
		} catch (OAuthSystemException e) {
			throw new ServletException(e);
		}
	}

}
