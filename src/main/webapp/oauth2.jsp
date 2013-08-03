<%@page import="java.util.Enumeration"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.Map"%>
<br>
<br>

<%
	String code = request.getParameter("code");
	String state = request.getParameter("state");
%>
<script type="text/javascript">
 function getAcceesToken() 
 {
    var fragment = window.location.hash.substring(1);  
     if (fragment.indexOf("&") > 0)
     {
        var arrParams = fragment.split("&");         

        var i = 0;
        for (i=0;i<arrParams.length;i++)
        {
         	var sParam =  arrParams[i].split("=");

         	if (sParam[0] == "access_token"){
        	 return sParam[1];
         	}
      	}
     }
     return "";
 }
 </script>


<center>

	<a href="/oauth2"> Home </a>

	<%
		if (code != null) {
	%>


	<form action="/" id="resault"></form>

	<table bgcolor="#ABCEEE">
		<tr>
			<td><h3>
					<%
						out.println("Authorization Code = " + code);
					%>
				</h3></td>
		</tr>
		<tr>
			<td><h3>
					<%
						out.println("state = " + state);
					%>
				</h3></td>
		</tr>
	</table>
	<br> <br>



	<form action="/oauth2/token">
		<h2>Token Request Form</h2>
		<table>
			<tr>
				<td>client_id
				<td>
				<td><input type="text" name="client_id">
				<td>
			</tr>
			<tr>
				<td>client_secret
				<td>
				<td><input type="text" name="client_secret">
				<td>
			</tr>
			<tr>
				<td>code
				<td>
				<td><input type="text" name="code" value="<%=code%>">
				<td>
			</tr>
			<tr>
				<td>grant_type
				<td>
				<td><input type="text" name="grant" value="authorization_code"
					disabled="disabled">
				<td>
			</tr>
			<tr>
				<td>
				<td>
				<td><input type="hidden" name="grant_type"
					value="authorization_code">
				<td>
			</tr>
			<tr>
				<td>
				<td>
				<td><input type="submit" value="Get Access token">
				<td>
			</tr>
		</table>
	</form>
</center>
<br/>
<br/>
	<table>
		<tr>
			<td>
				<h2>Token Request Format</h2>
				<table border="1" bgcolor="#C0C0C0">
					<tbody>
						<tr>
							<td><code>grant_type</code>
							</td>
							<td>Required. Must be set to <code>authorization_code</code>
								.</td>
						</tr>
						<tr>
							<td><code>code</code>
							</td>
							<td>Required. The authorization code received by the
								authorization server.</td>
						</tr>
						<tr>
							<td><code>redirect_uri</code>
							</td>
							<td>Required, if the request URI was included in the
								authorization request. Must be identical then.</td>
						</tr>
					</tbody>
				</table>
				<h2>Token Response Foramt</h2>
				<table border="1" bgcolor="#C0C0C0">
					<tbody>
						<tr>
							<td><code>{ "access_token" : "...", "token_type" :
									"...", "expires_in" : "...", "refresh_token" : "...", } </code></td>

						</tr>
					</tbody>
				</table>
			<td>
			
		</tr>
	</table>





	<%
		} else if (code == null) {
	%>


	<H2>Token Response Foramt</H2>
	<form action="/"></form>
	<table border="1" bgcolor="#ABCEEE">
		<tr>
			<td><label>Access Token :</label></td>
			<td><input id="accessToken" name="accessToken"
				disabled="disabled" /> <script type="text/javascript">
                                document.getElementById("accessToken").value = getAcceesToken();                        
                </script>
		</tr>
	</table>
	</form>

	<%
		}
	%>
</center>
