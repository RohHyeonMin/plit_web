<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<% String ip = "localhost"; %>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title> SIGN IN FORM </title>
	<link rel="stylesheet" type="text/css" 
		href="http://<%=ip%>:8080/plit/SignIn/SignIn_CSS.css">
		
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
		$(document).ready(function(){
			if( <%=request.getAttribute("join") != null%>)
				{
				alert('ȸ������ ����')
				}
			$('#signIn').click(function(){
				$('form').attr("action", "http://<%=ip%>:8080/plit/LoginUserAction.me");
			});
			$('#signUp').click(function(){
				$('form').attr("action", "http://<%=ip%>:8080/plit/SignUp.me");
			});
		})
	</script>
</head>
<body>
<center>
<div id="loginForm">
	<div> <img id="logo" src="http://<%=ip%>:8080/plit/SignIn/logo.png"/> </div>
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