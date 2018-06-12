<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title> SIGN IN FORM </title>
	<link rel="stylesheet" type="text/css" 
		href="SignIn/SignIn_CSS.css">
		
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
		$(document).ready(function(){
			if( <%=request.getAttribute("join") != null%>)
				{
				alert('회원가입 성공')
				}
			$('#signIn').click(function(){
				$('form').attr("action", "LoginUserAction.me");
			});
			$('#signUp').click(function(){
				$('form').attr("action", "SignUp.me");
			});
		})
	</script>
</head>
<body>
<center>
<div id="loginForm">
	<div> <img id="logo" src="SignIn/logo.png"/> </div>
	<div> <label id="label"> P L I T </label> </div>
	<div> <label> You can stick the post-it on place ! </label> </div> <br>

    <form method = "post">
	<table>
            <tr>
                <td colspan="2"> <input id="id" type="text" name="id"/> </td>
            </tr>
            <tr>
            	<td colspan="2"> <input id="pw" type="password" name="pw"/> </td>
            </tr>
            <tr>
            	<td> <div class="wrap"> <input type="submit" class="button" value="SIGN UP" id ="signUp"> </input></td>
            	<td> <div class="wrap"> <input type="submit" class="button" value="SIGN IN" id ="signIn"> </input> </td>
            </tr>
    </table>
    </form>
</div>
<%= request.getAttribute("join") %>
</center>
</body>
</html>