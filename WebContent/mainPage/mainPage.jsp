<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" type="text/css" href="mainPage_CSS.css">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="mainPage_JS.js"></script>
	<title> Main Page </title>
</head>
<body>
<div id="toolbar" role="banner">
		<!-- float: right ( 윗줄 부터 가장 오른쪽으로 위치함 )-->
		<img src="http://localhost:8080/plit/SignIn/logo.png/setting.png" class="menu">
		<img src="http://localhost:8080/plit/SignIn/logo.png/friend_list.png" class="menu">
		<img src="http://localhost:8080/plit/SignIn/logo.png/home.png" class="menu">
		<span id="userId"> user id </span>
		<img src="icon.jpg" id="http://localhost:8080/plit/SignIn/logo.png/userIcon"/>
	</div>
	<div id="map"> MAP </div>
	<div id="list">
		<table>
			<tr>
				<td id="user">
					<div>
						<img src="http://localhost:8080/plit/SignIn/logo.png/icon.jpg" id="member_icon">
						<div> user id </div>
					</div>				
				</td>
				<td colspan="2" id="text">
					<div class="ellipsis">
					사용자가 작성한 내용이 표시될 부분입니다. <br>
					테스트용 텍스트입니다. <br>
					안녕하세요 :) <br>
					1테스트용 텍스트입니다. <br>
					2테스트용 텍스트입니다. 
					3테스트용 텍스트입니다. 
					</div>
				</td>
			</tr>	
			<tr>
				<td> yyyy-mm-dd </td>
				<td class="bottom"> 댓글 </td>
				<td class="bottom"> <img src="http://localhost:8080/plit/SignIn/logo.png/heart.png"> </td>
				<td class="bottom"> 좋아요 </td>			
			</tr>		
		</table>
	</div>
</body>
</html>