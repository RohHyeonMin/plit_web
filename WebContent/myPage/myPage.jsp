<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="myPage.action.MyPageAction" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String id = null;
	String user_photo = null;
	String message = null;
	if(session.getAttribute("id")!=null){
		// 현재 사용자 id 받아오기
		id = (String)session.getAttribute("id");
		new MyPageAction().getMyPage(request, id); // request에 id값에 해당하는 사용자의 프로필사진, 상태메세지를 넣는다
		user_photo = (String)request.getAttribute("user_photo");
		message = (String)request.getAttribute("message");
	}
%>
<head>
    <meta charset="UTF-8">
    <title><%= id %>님의 페이지</title>
    
    <link rel="stylesheet" href="myPage/myPage_css.css">
    
</head>
<body>
	<input id="image_url" type="hidden" value="<%= user_photo %>"> 
    <header> <!-- 검색, 사용자사진, 홈, 친구 등등 있는곳 -->
        <div id="toolbar" role="banner">
			<input type="hidden" value="<%= id %>"> 
        </div>
    </header>
    <div id="sec"> <!-- header 부분과 분리 주내용 기입 -->
        <aside> <!-- 사용자 정보 -->
            <div id="user_userInfo_box">
                <div id="user_id"><%= id %>님의 페이지</div>
                <div id="user_image""></div>
                <div id="user_state_message"><%= message %></div>
            </div>
            <div id="follow">
                <div >팔로우</div><div>팔로워</div>
                <div id="user_follow">0</div><div id="user_follower">0</div>
            </div>
            <div id="user_friend"></div>
        </aside> 
        <section>
        
        </section>
    </div>
</body>
</html>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="myPage/myPage.js"></script>
<script src="myPage/ProfileImage.js"></script>
<script src="myPage/StateMessage.js"></script>
<script src="myPage/FriendList.js"></script>