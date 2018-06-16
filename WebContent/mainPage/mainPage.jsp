<%@page import="db.BoardBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String id = null;
	if(session.getAttribute("id")!=null){
		// 현재 사용자 id 받아오기
		id = (String)session.getAttribute("id");
	}
	ArrayList boardList = (ArrayList) request.getAttribute("boardlist");
%>

<html>
<head>
	<% String ip = "localhost"; %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="mainPage/mainPage_CSS.css">
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="mainPage/mainPage_JS.js" charset='UTF-8'></script>
	<script type="text/javascript" src="mainPage/menu_JS.js" charset='UTF-8'></script>
	<script type="text/javascript" src="mainPage/board_JS.js" charset='UTF-8'></script>
	<title> Main Page </title>
</head>

<body>
	 
	<div id="toolbar"> <input type="hidden" value="<%= id %>"> </div>
	<div id="wrapper">
	<div id="map"> </div>
	
	<div id="list">
	<!-- 게시판 부분   -->
	<%
		if( boardList != null)
		{
			for( int i = 0; i < boardList.size(); i++ )
			{
				BoardBean bl = (BoardBean)boardList.get(i);
				String time = bl.getDateBoard().substring(0, 10);
	%>
	<div class="item">
		<table>
			<tr>
				<td class="user">
					<div>
						<img src="mainPage/icon.jpg" class="member_icon">
						<div class="id"> <%= bl.getId() %> </div>
					</div>				
				</td>
				<td colspan="2" id="text">
					<div class="ellipsis">
					<%= bl.getBoardContent() %>
					</div>
				</td>
			</tr>	
			<tr class="bottomBox">
				<td> <%= time %> </td>
				<td class="bottom"> <img src="mainPage/comment.png"> </td>
				<td class="bottom"> 댓글 </td>
				<td class="bottom"> <img src="mainPage/heart.png"> </td>
				<td class="bottom"> 좋아요 </td>			
			</tr>		
		</table>
	</div>
		<% 	  }
		}
		%>
	
	</div>
	</div>
</body>
</html>