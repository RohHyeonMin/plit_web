<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Sign Up Form</title>
	<link rel="stylesheet" type="text/css" href="http://localhost:8080/plit/SignUp/SignUp_CSS.css">
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
		$(document).ready(function(){
			$('#signUp').click(function(){
				var pw1 = $('#pw').val();
				var pw2 = $('#check').val();
				
				if( pw1 == pw2 )
				{
					$('form').attr("action", "http://localhost:8080/plit/JoinUserAction.me");
				}
				else
				{
					alert('비밀번호를 확인 하세요.')
				}
			});
		})
	</script>
</head>
<body>
<center>
	<div id="signUpForm"> 
        <div id="button"> <a href="http://localhost:8080/plit/SignIn.me"> <img src="http://localhost:8080/plit/SignUp/close.png"></a></div>
		<div id="context"> SIGN UP WITH PLIT </div>
		<div> <label> You can stick the post-it on place ! </label> </div>
		<div> <label> Let's play XD </label> </div> <br>
		<form>
		<table>
			<tr>
                <td> <input id="birth" type="text" placeholder="yyyymmdd" name="birth"></input> </td>
            </tr>
            <tr>
                <td> <input id="id" type="text" placeholder="choose your id" name="id"></input> </td>
            </tr>
            <tr>
            	<td> <input id="pw" type="password" placeholder="password" name="pw" /> </td>
            </tr>
            <tr>
            	<td> <input id="check" type="password" placeholder="check the password" /> </td>
            </tr>
    	</table>
    	<input type="submit" id="signUp" value="SIGN UP"> </input>
    	</form>
	</div>
</center>
</body>
</html>