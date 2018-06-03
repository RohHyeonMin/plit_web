<%@page import="db.BoardBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String id = null;
	if(session.getAttribute("id")!=null){
		// 현재 사용자 id 받아오기
		id = (String)session.getAttribute("id");
	}
	ArrayList boardList = (ArrayList) request.getAttribute("boardlist");
	//hgjfdffddffd
%>

<html>
<head>
	<% String ip = "localhost"; %>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" type="text/css" href="http://localhost:8080/plit/mainPage/mainPage_CSS.css">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="http://localhost:8080/plit/mainPage/mainPage_JS.js"></script>
	<title> Main Page </title>
</head>

<body>
	<div id="toolbar" role="banner"> 
		<img src="http://localhost:8080/plit/mainPage/setting.png" class="menu">
		<img src="http://localhost:8080/plit/mainPage/friend_list.png" class="menu">
		<img src="http://localhost:8080/plit/mainPage/home.png" class="menu">
		<span id="userId"> 
			<%= id %>
		</span>
		<img src="http://localhost:8080/plit/mainPage/icon.jpg" id="userIcon"/>
		<!-- 검색창 -->
   		<div class="searchBox">
      		<input type="text" class="searchTerm" placeholder="Search...">
      		<button type="submit" class="searchButton"> <img src="http://localhost:8080/plit/mainPage/search.png" id ="searchImg"> </button>
   		</div>
	</div>
	<div id="map"> MAP </div>
	
	<div id="list">
	<!-- 게시판 부분   -->
	<%
		for( int i = 0; i < boardList.size(); i++ )
		{
			BoardBean bl = (BoardBean)boardList.get(i);
			String time = bl.getDateBoard().substring(0, 10);
	%>
	<div id="item">
		<table>
			<tr>
				<td id="user">
					<div>
						<img src="http://localhost:8080/plit/mainPage/icon.jpg" id="member_icon">
						<div> <%= bl.getId() %> </div>
					</div>				
				</td>
				<td colspan="2" id="text">
					<div class="ellipsis">
					<%= bl.getBoardContent() %>
					</div>
				</td>
			</tr>	
			<tr>
				<td> <%= time %> </td>
				<td class="bottom"> <img src="http://localhost:8080/plit/mainPage/comment.png"> </td>
				<td class="bottom"> 댓글 </td>
				<td class="bottom"> <img src="http://localhost:8080/plit/mainPage/heart.png"> </td>
				<td class="bottom"> 좋아요 </td>			
			</tr>		
		</table>
	</div>
	<% } %>
	
	</div>
</body>
</html>