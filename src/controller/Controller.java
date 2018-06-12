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
import myPage.action.MyPageAction;

public class Controller extends javax.servlet.http.HttpServlet 
	implements javax.servlet.Servlet {
	
	// doGet, doPost ��û ��� �� �޼��带 ����
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String RequestURI = request.getRequestURI();  // ������Ʈ & ���� ���
		String contextPath = request.getContextPath();// ������Ʈ ���
		String command = RequestURI.substring(contextPath.length()); // ���ϸ�
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
				// ����  request�� ��� �ִ� ������ �����ϰ� �ִٰ� �� ����������, ����������������
				// �ش� ������ �� �� �ְ� �����ϴ� ���
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
