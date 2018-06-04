package myPage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;

/*
 *  Action 클래스를 상속받아 execute 메소드 재정의 후 ActionForward 클래스를 반환하여 forward할 페이지 반환
 */

public class MyPageAction implements Action
{
	@Override
	public ActionForward execute( HttpServletRequest request, HttpServletResponse response )
	{
		ActionForward forward = new ActionForward();
		try
		{
			forward.setRedirect( false );
			forward.setPath("/MyPage/myPage.jsp");
			
			return forward;
		}
		catch( Exception e )
		{
			return null;
		}
	}

}
