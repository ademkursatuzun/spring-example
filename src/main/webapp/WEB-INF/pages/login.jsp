<%-- 
    Document   : login
    Created on : Dec 21, 2015, 5:05:44 PM
    Author     : akursat
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
       
    </head>
    <body onload='document.loginForm.username.focus();'>

        

        <div id="login-box">

            <h2>Login with Username and Password</h2>
            
            <c:if test="${not empty error}">
			<div class="error">${error}</div>
            </c:if>

            <form name='loginForm'
                  action="<c:url value='/j_spring_security_check' />" method='POST'>

                <table>
                    <tr>
                        <td>User:</td>
                        <td><input type='text' name='username' value=''></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type='password' name='password' /></td>
                    </tr>
                    <tr>
                        <td colspan='2'><input name="submit" type="submit"
                                               value="submit" /></td>
                    </tr>
                </table>

            </form>
                  <a href="registration">Register</a>
        </div>

    </body>
</html>
