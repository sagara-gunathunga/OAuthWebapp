package sample.oauth;

import java.io.IOException;
import java.util.Properties;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Class AuthorizeProcessor.
 * 
 * @author Sagara Gunathunga
 * 
 */
public abstract class OAuthProcessor extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Properties properties;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ignoreHostVerification();
		initProperties(config);

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected String getConfigProperty(String key) {
		return properties.getProperty(key);
	}

	abstract protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	private void initProperties(ServletConfig config) {
		properties = new Properties();
		try {
			properties.load(config.getServletContext().getResourceAsStream(
					"/WEB-INF/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void ignoreHostVerification() throws ServletException {

		/*
		 * 
		 * All the code below is to overcome host name verification failure we
		 * get in certificate validation due to self-signed certificate. This
		 * code should not be used in a production setup.
		 */
		try {

			SSLContext sc;

			sc = SSLContext.getInstance("SSL");

			// Create empty HostnameVerifier
			HostnameVerifier hv = new HostnameVerifier() {
				public boolean verify(String urlHostName, SSLSession session) {
					return true;
				}
			};

			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(
						java.security.cert.X509Certificate[] certs,
						String authType) {
				}

				public void checkServerTrusted(
						java.security.cert.X509Certificate[] certs,
						String authType) {
				}
			} };

			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			SSLContext.setDefault(sc);
			HttpsURLConnection.setDefaultHostnameVerifier(hv);

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
