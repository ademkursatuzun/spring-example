<%-- 
    Document   : list-users
    Created on : Dec 21, 2015, 5:08:13 PM
    Author     : akursat
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
         pageEncoding="ISO-8859-1"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Users</title>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

    </head>
    <body>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div id="userList-box">

            <h2>Users</h2>
            <form:form id="form" method="post" action="generateReport">
            <table border="1">
                <tr>
                    <td>Username</td>
                    <td>Email</td>
                    <td>Birthday</td>
                    <td>Sex</td>
                </tr>
                <c:forEach items="${listPersons}" var="person">
                    <tr>
                        <td>${person.username}</td>
                        <td>${person.email}</td>
                        
                        <td><fmt:formatDate value="${person.birthday}" pattern="MMM dd,yyyy" /></td>
                    <c:choose>
                        <c:when test="${person.sex=='0'}">
                            <td>male</td>
                        </c:when>    
                        <c:otherwise>
                           <td>female</td>
                        </c:otherwise>
                    </c:choose>
                        
                        

                    </tr>
                </c:forEach>
                <tr>
                    <td><input type="submit" value="Print Table" ></input></td>

                </tr>
            </table>
        </form:form>
        </div>
    </sec:authorize>

</body>
</html>
