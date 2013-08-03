package sample.oauth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.amber.oauth2.client.OAuthClient;
import org.apache.amber.oauth2.client.URLConnectionClient;
import org.apache.amber.oauth2.client.request.OAuthClientRequest;
import org.apache.amber.oauth2.client.response.OAuthClientResponse;
import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.apache.amber.oauth2.common.exception.OAuthSystemException;
import org.apache.amber.oauth2.common.message.types.GrantType;

/**
 * The Class TokenProcessor.
 * 
 * @author Sagara Gunathunga
 * 
 */
@WebServlet(value = "/token", loadOnStartup = 1)
public class TokenProcessor extends OAuthProcessor {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Configuration data
		String tokenEndpoint = getConfigProperty("tokenEndpoint");
		String callbackurl = getConfigProperty("callbackurl");

		// Form data specific to the application.
		String clientId = request.getParameter("client_id");
		String clientSecret = request.getParameter("client_secret");
		String code = request.getParameter("code");
		String grantType = request.getParameter("grant_type");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// create OAuth client that uses custom http client under the hood
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		OAuthClientResponse oAuthResponse = null;
		OAuthClientRequest accessRequest = null;

		try {

			if ("authorization_code".equals(grantType)) {

				accessRequest = OAuthClientRequest.tokenLocation(tokenEndpoint)
						.setGrantType(GrantType.AUTHORIZATION_CODE)
						.setClientId(clientId).setClientSecret(clientSecret)
						.setRedirectURI(callbackurl).setCode(code)
						.buildBodyMessage();

			} else if ("password".equals(grantType)) {

				accessRequest = OAuthClientRequest.tokenLocation(tokenEndpoint)
						.setGrantType(GrantType.PASSWORD).setUsername(username)
						.setPassword(password).setClientId(clientId)
						.setClientSecret(clientSecret).buildBodyMessage();

			} else if ("client_credentials".equals(grantType)) {

				accessRequest = OAuthClientRequest.tokenLocation(tokenEndpoint)
						.setGrantType(GrantType.CLIENT_CREDENTIALS)
						.setClientId(clientId).setClientSecret(clientSecret)
						.buildBodyMessage();
			}

			oAuthResponse = oAuthClient.accessToken(accessRequest);
			String accessToken = oAuthResponse.getParam("access_token");
			String tokenType = oAuthResponse.getParam("token_type");
			String expires = oAuthResponse.getParam("expires_in");
			String refreshToken = oAuthResponse.getParam("refresh_token");

			request.getSession().setAttribute("accessToken", accessToken);
			request.getSession().setAttribute("refreshToken", refreshToken);
			request.getSession().setAttribute("type", tokenType);
			request.getSession().setAttribute("expires", expires);

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("token.jsp");
			dispatcher.forward(request, response);
		} catch (OAuthProblemException e) {

			e.printStackTrace();
		} catch (OAuthSystemException e) {
			throw new ServletException(e);
		}

	}

}
