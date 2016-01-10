<%-- 
    Document   : registration
    Created on : Dec 21, 2015, 5:06:37 PM
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
        <title>Registration</title>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
        <script src="<c:url value="/resources/js/jquery-2.1.4.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.validate.js" />"></script>
        <script src="<c:url value="/resources/js/additional-methods.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.date-dropdowns.js" />"></script>
        <style>

            .null-username,.null-email,.null-password,.null-repassword{
                color: red;
                text-align: center;
            }

        </style>

        <script>
            $(function () {



                $.validator.messages.required = '';



                function validation(fieldName, className) {

                    var name = $("#" + fieldName).val();
                    var null_name = $('.' + className).length;

                    if (name.length == 0 && null_name >= 1) {
                        if (className == 'null-username') {
                            $('.' + className).text('Username Null!');
                        } else if (className == 'null-email') {
                            $('.' + className).text('Email Null!');
                        } else if (className == 'null-password') {
                            $('.' + className).text('Password Null!');
                        } else if (className == 'null-repassword') {
                            $('.' + className).text('Repassword Null!');
                        }

                    } else {

                        $('.' + className).text('');
                        return true;
                    }

                }

                $('#resisterForm').validate();
                $('#name').focus();
                $("#registerForm").validate({
                    rules: {
                        password: "required",
                        repassword: {
                            equalTo: "#password"
                        }
                    }
                });

                function doAjaxPost() {
                    var contexPath = "<%=request.getContextPath()%>";
                    // get the form values
                    var username = $('#name').val();
                    var email = $('#email').val();

                    if (username.length > 0) {
                        console.log("not null both ok!");
                        $.ajax({
                            type: "POST",
                            url: contexPath + "/registration",
                            data: "username=" + username + "&email=" + email,
                            success: function (response) {
                                // we have the response

                                if (response.status == "SUCCESS") {
                                    $('.exist-email').html("");
                                    $('.exist-username').html("");
                                    $("#submitButton").prop("disabled", false);
                                    // $('#messagePanel2').after("User has been added successfully.");
                                } else if (response.status == "FAIL1") {
                                    $("#submitButton").prop("disabled", true);
                                    $('.exist-username').html("Email exist!");
                                } else if (response.status == "FAIL2") {
                                    $("#submitButton").prop("disabled", true);
                                    $('.exist-email').html("Username exist!");
                                } else {
                                    $("#submitButton").prop("disabled", false);
                                    console.log("other Case--catch!");
                                }
                            }

                        });
                    }




                }
                $('#name').on("blur", function () {
                    console.log("blured -name!");
                    validation("name", "null-username");
                    doAjaxPost();
                });
                $('#email').on("blur", function () {
                    validation("email", "null-email");
                    doAjaxPost();
                });
                $('#password').on("blur", function () {
                    validation("password", "null-password");
                });
                $('#repassword').on("blur", function () {
                    validation("repassword", "null-repassword");
                });



            });

        </script>

    </head>
    <body>




        <div id="register-box">

            <h1>Registration</h1>



            <form:form id="registerForm" method="post" action="add" commandName="users">
                <table>

                    <tr>
                        <td>Username:</td>
                        <td><input id="name" type='text' required  name='username' value=''></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input id="email"  path="" type='email' required name='email' value=''/></td>
                    </tr>
                    <tr>

                        <td>Birthday</td>
                        <td>
                            <input type="date" id="birthday" name="birthday">
                        </td>
                    </tr>

                    <tr>
                        <td>Sex :</td>

                        <td>Male
                            <input type='radio' name='sex' value="0" checked="true"/>
                            Female
                            <input type='radio' name='sex' value="1"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type='password' id="password"  required name='password'/></td>
                    </tr>
                    <tr>
                        <td>Password Confirmation</td>
                        <td><input type='password' id="repassword" required name='repassword'/></td>
                    </tr>
                    <tr>

                        <td colspan='2'>
                            <input id="submitButton" type="submit"  value='Register'/>
                    </tr>
                    <tr>
                    </tr>
                </table>
                <div id="messagePanel">
                    <p class="null-username"></p>
                    <p class="null-email"></p>
                    <p class="null-password"></p>
                    <p class="null-repassword"></p>
                </div>
                <div id="messagePanel2">
                    <p class="exist-username"></p>
                    <p class="exist-email"></p>
                </div>                  
            </form:form>  
        </div> 
    </body>
</html>
