package login;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.DataDao;
import dao.UserDao;
import db.UserBean;


public class LoginUserAction implements Action
{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	{

		try
		{
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			ActionForward forward = new ActionForward();
			DataDao dao = new DataDao();
			Connection con = dao.ds.getConnection();;
			
			UserDao userDao = new UserDao(con);
			UserBean user = new UserBean();   		
		   	
			// SignIn.jsp占쏙옙 form占승깍옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
			user.setId(request.getParameter("id"));
			user.setPw(request.getParameter("pw"));
	 
			user = userDao.getUserLogin(user.getId(), user.getPw());
		   		
			if( user == null )
			{
				// TODO 占싸깍옙占쏙옙 占쏙옙占쏙옙 ( 占쏙옙占시� )
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('占쏙옙占싱듸옙, 占쏙옙橘占싫� 확占쏙옙 占실댐옙 회占쏙옙占쏙옙占쏙옙 占쏙옙占쌍시깍옙 占쌕띰옙占싹댐옙.');");
				out.println("location.href='./SignIn.me';");
				out.println("</script>");
				out.close();

				return null;
			}
		   		
			//로그인 성공
			session.setAttribute("id", user.getId());
			session.setAttribute("user", user);
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