OAuth2 Sample - Using WSO2 IS/APIM
==================================
@author Sagara Gunathunga

1.) Start WSO2 IS/ WSO2 APIM and login to admin-console.

2.) In admin-console register a OAuth2 application, make sure to use "http://localhost:8080/oauth2/oauth" as the Callback Url. 

3.) Edit "config.properties" file according to your WSO2 IS/WSO2 APIM server host name and according to above registered OAuth2 application. 

3.) To run this sample with Maven use "mvn clean jetty:run"

4.) To build the WAR use "mvn clean package" and deploy on a application server such as WSO2 AS. 

NOTE
----

This OAuth2 sample application developed  using Apache Oltu ( A.K.A Apache Amber) framework and can be 
used to test OAuth2 support on WSO2 platform based products such as WSO2 IS and WSO2 API Manager. 
