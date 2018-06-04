<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<% String ip = "localhost"; %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>누구누구님의 페이지</title>
	
    <link rel="stylesheet" href="MyPage/myPage_css.css">
    <script src="MyPage/myPage.js"></script>
</head>
<body>
    <header> <!-- 검색, 사용자사진, 홈, 친구 등등 있는곳 -->
        
    </header>
    <div id="sec"> <!-- header 부분과 분리 주내용 기입 -->
        <aside> <!-- 사용자 정보 -->
            <div id="user_userInfo_box">
                <div id="user_image"></div>
                <div id="user_state_message" style="z-index:10;">상태 메세지 입니다.</div>
            </div>
            <div id="user_friend"></div>
        </aside> 
        <section>
        
        </section>
    </div>
</body>
</html>