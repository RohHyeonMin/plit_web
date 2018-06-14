package mainPage;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.BoardDao;

// 게시글 가져오기
public class BoardListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward= new ActionForward();
		HttpSession session = request.getSession();
		
		// login에서 사용자 아이디 받아오기
		String id = (String)session.getAttribute("id");
   		if(id==null){
   			// TODO 아디이 받아 오지 못했을 경우 로그인 화면으로 간다.
			forward.setRedirect(true);
			forward.setPath("./SignIn.me");
			System.out.println("로그인 실패. - BoardListAction -");
			return forward;
   		}
   		
		BoardDao boardDao = new BoardDao();
		ArrayList boardlist = new ArrayList();
		
	  	int page = 1;
		int limit = 5; // 한번에 불러올 글 갯수
		
		boardlist = boardDao.getBoardList(page, limit); //리스트를 받아 옴
   				
   		request.setAttribute("page", page); // 현재 페이지
		request.setAttribute("boardlist", boardlist); // return ArrayList;
		
	   	forward.setRedirect(false);
   		forward.setPath("/mainPage/mainPage.jsp");
   		return forward;
	}

}
