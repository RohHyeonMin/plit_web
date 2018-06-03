package login;

import static db.DBConnection.getConnection;

import java.io.PrintWriter;
import java.sql.Connection;

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
			throws Exception
	{
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		Connection con = getConnection();
		
		UserDao userDao = new UserDao(con);
		UserBean user = new UserBean();   		
	   		
		// SignIn.jsp의 form태그의 데이터 가져와 저장
		user.setId(request.getParameter("id"));
		user.setPw(request.getParameter("pw"));
 
		user = userDao.getUserLogin(user.getId(), user.getPw());
	   		
		if( user == null )
		{
			// TODO 로그인 실패 ( 경고창 )
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('아이디, 비밀번호 확인 또는 회원가입 해주시기 바랍니다.');");
			out.println("location.href='./SignIn.me';");
			out.println("</script>");
			out.close();

			return null;
		}
	   		
		//로그인 성공
		session.setAttribute("id", user.getId());
		forward.setPath("/mainPageAction.bo");
		return forward;
	}
}