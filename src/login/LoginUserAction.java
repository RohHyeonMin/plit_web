package login;

import static db.DBConnection.getConnection;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import db.UserBean;
import dao.UserDao;


public class LoginUserAction implements Action
{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	{

		try
		{
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			ActionForward forward = new ActionForward();
			Connection con = getConnection();
			
			UserDao userDao = new UserDao(con);
			UserBean user = new UserBean();   		
		   	
			// SignIn.jsp�� form�±��� ������ ������ ����
			user.setId(request.getParameter("id"));
			user.setPw(request.getParameter("pw"));
	 
			user = userDao.getUserLogin(user.getId(), user.getPw());
		   		
			if( user == null )
			{
				// TODO �α��� ���� ( ���â )
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('���̵�, ��й�ȣ Ȯ�� �Ǵ� ȸ������ ���ֽñ� �ٶ��ϴ�.');");
				out.println("location.href='./SignIn.me';");
				out.println("</script>");
				out.close();

				return null;
			}
		   		
			//�α��� ����
			session.setAttribute("id", user.getId());
			forward.setPath("/mainPageAction.bo");
			
			return forward;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}