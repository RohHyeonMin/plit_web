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
		<!-- float: right ( ���� ���� ���� ���������� ��ġ�� )-->
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
					����ڰ� �ۼ��� ������ ǥ�õ� �κ��Դϴ�. <br>
					�׽�Ʈ�� �ؽ�Ʈ�Դϴ�. <br>
					�ȳ��ϼ��� :) <br>
					1�׽�Ʈ�� �ؽ�Ʈ�Դϴ�. <br>
					2�׽�Ʈ�� �ؽ�Ʈ�Դϴ�. 
					3�׽�Ʈ�� �ؽ�Ʈ�Դϴ�. 
					</div>
				</td>
			</tr>	
			<tr>
				<td> yyyy-mm-dd </td>
				<td class="bottom"> ��� </td>
				<td class="bottom"> <img src="http://localhost:8080/plit/SignIn/logo.png/heart.png"> </td>
				<td class="bottom"> ���ƿ� </td>			
			</tr>		
		</table>
	</div>
</body>
</html>