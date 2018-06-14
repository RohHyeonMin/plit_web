package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import dao.DataDao;

public class DataController extends javax.servlet.http.HttpServlet 
implements javax.servlet.Servlet {
	
// doGet, doPost 둘다 실행해도 doProcess 실행됨
protected void doProcess( HttpServletRequest request, HttpServletResponse response ) 
		throws ServletException, IOException {
	
	request.setCharacterEncoding("UTF-8"); // 받는거 UTF-8 인코딩
	response.setContentType("text/html;charset=UTF-8"); // 보낼 때도 UTF-8로 인코딩
	
	String RequestURI = request.getRequestURI();  // ������Ʈ & ���� ���
	String contextPath = request.getContextPath();// ������Ʈ ���
	String command = RequestURI.substring(contextPath.length()); // ���ϸ�
	
	String dataType = request.getParameter("type");
	DataDao dataDao = new DataDao();

	if( dataType.equals("getFriendList") ) // 친구목록 불러오기
	{
		String id = request.getParameter("id");
		JSONArray json = dataDao.getFriendList( id, 0, 10 );
		
		if( json != null )
			response.getWriter().println( json );
	}
	else if( dataType.equals("setStateMessage") ) // 친구목록 불러오기
	{
		String id = request.getParameter("id");
		String message = request.getParameter("message");
		int result = dataDao.setStateMessage( id, message ); // 0이면 false, 1이면 true
		
		response.getWriter().println( result ); // "true", "false" 둘중 하나를 보냄
	}
	else if( dataType.equals("setProfileImage") ) // 프로필이미지 변경하기
	{
		String id = request.getParameter("id");
		int result = dataDao.setProfileImage( request, id ); // 0이면 false, 1이면 true
		
		response.getWriter().println( result ); // "true", "false" 둘중 하나를 보냄
	}
	
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
	doProcess(request,response);
}  	

protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
	doProcess(request,response);
}	  	 
}
