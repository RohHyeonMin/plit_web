package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import user.action.JoinUserAction;
import user.action.LoginUserAction;

/* 로그인, 로그아웃, 회원 가입 */
public class LoginController extends javax.servlet.http.HttpServlet 
implements javax.servlet.Servlet{

	// doGet, doPost 요청 모두 이 메서드를 수행
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			
		String RequestURI = request.getRequestURI();  // 프로젝트 & 파일 경로
		String contextPath = request.getContextPath();// 프로젝트 경로
		String command = RequestURI.substring(contextPath.length()); // 파일명
		ActionForward forward = null;
		Action action = null;
		
		if( command.equals("/SignIn.me") )
		{
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./SignIn/SignIn.jsp");
		}
		else if( command.equals("/LoginUserAction.me") )
		{
			action = new LoginUserAction();
			try
			{
				forward = action.execute(request, response);		
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if( command.equals("/SignUp.me") )
		{
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./SignUp/SignUp.jsp");
		}
		else if( command.equals("/JoinUserAction.me") )
		{
			action = new JoinUserAction();
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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}

	
}
