package user.action;

import static dao.DBConnection.getConnection;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import user.db.UserBean;
import user.db.UserDao;

/* ȸ������  */
public class JoinUserAction implements Action
{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception
	{
		request.setCharacterEncoding("euc-kr");
			 	
		ActionForward forward = new ActionForward();
		Connection con = getConnection();
		boolean result = false; // ȸ������ ���� ����
		
		UserDao userDao = new UserDao(con);
		UserBean user = new UserBean();
		
		// SignUp.jsp�� form�±��� ������ ������ ����
		user.setId(request.getParameter("id"));
		user.setPw(request.getParameter("pw"));
		user.setBirth(request.getParameter("birth"));   
		
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");
		
		// ȸ�� ���� ����Ʈ
		ArrayList<UserBean> userList = userDao.getUserList();
		for( int i = 0; i < userList.size(); i++ )
		{
			// ���̵� �ߺ� �˻�
			if( user.getId().equals( userList.get(i).getId() ) )
			{
				System.out.println("�ߺ� ���̵� �߻�");
				// TODO ����� �α��� ���� ( ���â )
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('�̹� �����ϴ� ���̵��Դϴ�.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return null;
			}
		}
		
		con = getConnection();
		userDao = new UserDao(con);
		// ��� ������ ���̵� INSERT
		result = userDao.joinUser(user);
		   		  		
		if( result == false)
		{
			System.out.println("ȸ������ ����");
			// TODO ����� �α��� ���� ( ���â )
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('ȸ������ ����.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		
		//ȸ������ ����.
		System.out.println("ȸ������ ����");
		forward.setRedirect(true);
		forward.setPath("./SignIn.me");
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('ȸ������ ����.');");
		out.println("history.back();");
		out.println("</script>");
		out.close();
		
		System.out.println("ȸ�������� ���̵� : " + user.getId() );
		return forward;
	}
}
