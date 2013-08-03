<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

String accessToken = (String) session.getAttribute("accessToken");
String refreshToken = (String) session.getAttribute("refreshToken");
String type = (String) session.getAttribute("type");
String expires = (String) session.getAttribute("expires");

 

 %>
<center>

 <a href="/oauth2" > Home </a>

<H2>Token Response</H2> 
<table  border="1" bgcolor="#ABCEEE">
		<tr>
			<td><h3>
					<%
						out.println("access_token = " + accessToken);
					%>
				</h3>
			</td>
		</tr>
		<tr>
			<td><h3>
					<%
						out.println("refresh_token= " + refreshToken);
					%>
				</h3>
			</td>
		</tr>
		<tr>
			<td><h3>
					<%
						out.println("token_type= " + type);
					%>
				</h3>
			</td>
		</tr>
		<tr>
			<td><h3>
					<%
						out.println("expires_in= " + expires);
					%>
				</h3>
			</td>
		</tr>
	</table>
	</center>
	<br> <br>