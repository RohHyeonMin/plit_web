package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.*;
import login.LoginUserAction;
import mainPage.BoardListAction;

public class Controller extends javax.servlet.http.HttpServlet 
	implements javax.servlet.Servlet {
	
	// doGet, doPost 요청 모두 이 메서드를 수행
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String RequestURI = request.getRequestURI();  // 프로젝트 & 파일 경로
		String contextPath = request.getContextPath();// 프로젝트 경로
		String command = RequestURI.substring(contextPath.length()); // 파일명
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
			try
			{
				forward = action.execute(request, response);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
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
				// 현재  request에 담고 있는 정보를 저장하고 있다가 그 다음페이지, 다음페이지에서도
				// 해당 정보를 볼 수 있게 저장하는 기능
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
