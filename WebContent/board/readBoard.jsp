<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="board/readBoard_CSS.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js" charset='UTF-8'></script>
<script type="text/javascript" src="board/readBoard_JS.js" charset='UTF-8'></script>
<script type="text/javascript" src="mainPage/menu_JS.js" charset='UTF-8'></script>
</head>
<body>

	<div id="toolbar"> </div>

<div id="box">
	<div id="right">
		<div id="profile">
		<img src="board/userIcon.jpg" id="profileImg">
		<div id="profileId"> user id </div>
		</div>

		<div class="sub"> 대구 북구 복현동 </div>
		<div class="sub"> YYYY-MM-DD </div>
		<div id="imgBox"> </div>
	</div>

	<div>
		<div id="boardBox">
			<div id="content"> 내용 </div>
			<img src="board/heart.png">
		</div>
	</div>

	<div id="reply"></div>
</div>
</body>
</html>