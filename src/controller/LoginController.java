package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import login.JoinUserAction;
import login.LoginUserAction;

/* �α���, �α׾ƿ�, ȸ�� ���� */
public class LoginController extends javax.servlet.http.HttpServlet 
implements javax.servlet.Servlet{

	// doGet, doPost ��û ��� �� �޼��带 ����
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8"); // 받는거 UTF-8 인코딩
		response.setContentType("text/html;charset=UTF-8"); // 보낼 때도 UTF-8로 인코딩
		
		String RequestURI = request.getRequestURI();  // ������Ʈ & ���� ���
		String contextPath = request.getContextPath();// ������Ʈ ���
		String command = RequestURI.substring(contextPath.length()); // ���ϸ�
		ActionForward forward = null;
		Action action = null;
		
		if( command.equals("/SignIn.me") )
		{
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/SignIn/SignIn.jsp");
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
			forward.setPath("/SignUp/SignUp.jsp");
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
				// ����  request�� ��� �ִ� ������ �����ϰ� �ִٰ� �� ����������, ����������������
				// �ش� ������ �� �� �ְ� �����ϴ� ���
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
