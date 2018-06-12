package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import dao.DataDao;

public class DataController extends javax.servlet.http.HttpServlet 
implements javax.servlet.Servlet {
	
// doGet, doPost ��û ��� �� �޼��带 ����
protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
	
	String dataType = request.getParameter("type");
	DataDao dataDao = new DataDao();

	if( dataType.equals("friendList") ) // 친구목록 불러오기
	{
		String id = request.getParameter("id");
		JSONArray json = dataDao.getFriendList( id, 0, 10 );
		
		if( json != null )
			response.getWriter().println( json );
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
