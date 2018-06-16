package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.*;
import action.BoardListAction;
import myPage.action.MyPageAction;

public class Controller extends javax.servlet.http.HttpServlet 
	implements javax.servlet.Servlet {
	
	// doGet, doPost 占쏙옙청 占쏙옙占� 占쏙옙 占쌨쇽옙占썲를 占쏙옙占쏙옙
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8"); // 諛쏅뒗嫄� UTF-8 �씤肄붾뵫
		response.setContentType("text/html;charset=UTF-8"); // 蹂대궪 �븣�룄 UTF-8濡� �씤肄붾뵫
		
		String RequestURI = request.getRequestURI();  // 占쏙옙占쏙옙占쏙옙트 & 占쏙옙占쏙옙 占쏙옙占�
		String contextPath = request.getContextPath();// 占쏙옙占쏙옙占쏙옙트 占쏙옙占�
		String command = RequestURI.substring(contextPath.length()); // 占쏙옙占싹몌옙
		ActionForward forward = null;
		Action action = null;
	
		if( command.equals("/mainPage.bo") )
		{
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/mainPage/mainPage.jsp");
		}		
		else if( command.equals("/mainPageAction.bo") )
		{
			action = new BoardListAction();
			forward = action.execute(request, response);
		}		
		else if( command.equals("/myPage.bo") )
		{
			action = new MyPageAction();
			forward = action.execute(request, response);
		}
		
		/*********************************************************************/
		
		if(forward != null)
		{
			if(forward.isRedirect())
			{
				response.sendRedirect(forward.getPath());
			}
			else
			{
				// RequestDispatcher
				// 占쏙옙占쏙옙  request占쏙옙 占쏙옙占� 占쌍댐옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹곤옙 占쌍다곤옙 占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙, 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙
				// 占쌔댐옙 占쏙옙占쏙옙占쏙옙 占쏙옙 占쏙옙 占쌍곤옙 占쏙옙占쏙옙占싹댐옙 占쏙옙占�
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
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
