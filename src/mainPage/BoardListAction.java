package mainPage;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.BoardDao;

// �Խñ� ��������
public class BoardListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward= new ActionForward();
		HttpSession session = request.getSession();
		
		// login���� ����� ���̵� �޾ƿ���
		String id = (String)session.getAttribute("id");
   		if(id==null){
   			// TODO �Ƶ��� �޾� ���� ������ ��� �α��� ȭ������ ����.
			forward.setRedirect(true);
			forward.setPath("./SignIn.me");
			System.out.println("�α��� ����. - BoardListAction -");
			return forward;
   		}
   		
		BoardDao boardDao = new BoardDao();
		ArrayList boardlist = new ArrayList();
		
	  	int page = 1;
		int limit = 5; // �ѹ��� �ҷ��� �� ����
		
		boardlist = boardDao.getBoardList(page, limit); //����Ʈ�� �޾� ��
   				
   		request.setAttribute("page", page); // ���� ������
		request.setAttribute("boardlist", boardlist); // return ArrayList;
		
	   	forward.setRedirect(false);
   		forward.setPath("/mainPage/mainPage.jsp");
   		return forward;
	}

}
