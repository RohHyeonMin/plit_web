package myPage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;

/*
 *  Action Ŭ������ ��ӹ޾� execute �޼ҵ� ������ �� ActionForward Ŭ������ ��ȯ�Ͽ� forward�� ������ ��ȯ
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
