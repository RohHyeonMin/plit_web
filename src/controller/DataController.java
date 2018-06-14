package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.DataDao;

public class DataController extends javax.servlet.http.HttpServlet 
implements javax.servlet.Servlet {
	
	public static final String m_serverIP = "http://192.168.0.11:8080";
	
// doGet, doPost 둘다 실행해도 doProcess 실행됨
protected void doProcess( HttpServletRequest request, HttpServletResponse response ) 
		throws ServletException, IOException {
	
	request.setCharacterEncoding("UTF-8"); // 받는거 UTF-8 인코딩
	response.setCharacterEncoding("UTF-8");; // 보낼 때도 UTF-8로 인코딩
	
	String RequestURI = request.getRequestURI();  // /plit/.data
	String contextPath = request.getContextPath();// /plit ( 현재 톰캣 폴더 )
	
	String dataType = request.getParameter("type");
	DataDao dataDao = new DataDao(); // 디비에 값을 넣거나 빼기위해서 Dao 생성
	
	if( dataType != null )
	{
		if( dataType.equals("getFriendList") ) // 친구목록 불러오기
		{
			String id = request.getParameter("id");
			JSONArray json = dataDao.getFriendList( id, 0, 10 );
			
			if( json != null )
				response.getWriter().println( json );
		}
		else if( dataType.equals("setStateMessage") ) // 친구목록 불러오기
		{
			String id = request.getParameter("id");
			String message = request.getParameter("message");
			int result = dataDao.setStateMessage( id, message ); // 0이면 false, 1이면 true
			
			response.getWriter().println( result ); // "true", "false" 둘중 하나를 보냄
		}
	}
	else // null 이라는건 MultiRequest 타입으로 보냈다는거임 그말은 즉슨 image 관련 처리다.
	{
		
		ServletContext context = request.getServletContext(); // 현재 서블릿 객체
		String savePath = context.getRealPath("PlitImage"); // 현재 톰캣 폴더에 images 경로를 받아옴
		int size = 50 * 1024 * 1024; // 다운 받을 최대 용량 50Mb 까지 가능
		
		File saveDir = new File(savePath); // 디렉토리가 없을 경우 생성
		if(!saveDir.exists()) 
		{
			saveDir.mkdirs(); 
		}
		
	    // MultipartRequest(request, 저장경로[, 최대허용크기, 인코딩케릭터셋, 동일한 파일명 보호 여부])
		MultipartRequest multiRequest = new MultipartRequest( request, savePath, size, "utf-8", new DefaultFileRenamePolicy() );
		dataType = multiRequest.getParameter("type"); // multiRequest안에 "type" 값을 가져온다.

		if( dataType.equals("setProfileImage") ) // 프로필이미지 변경하기
		{
			String id = multiRequest.getParameter("id"); // "id" 값 가져오기
			
			String fileName = multiRequest.getFile("profileImage").getName(); // 얻어온 파일 명
			String extName = fileName.substring(fileName.length()-4, fileName.length()); // 확장자명

			File isFile = new File( savePath + "\\" + id + "_profileImage" + extName ); // 이미 사용자의 이미지가 존재한다면 삭제해야한다
			if( isFile.exists() )
			{
				isFile.delete();
			}
			
			// 삭제하거나 안하거나 만들어진 파일을 사용자id_profileImage 라는 이름으로 바꾼다.
			String srcFilePath = savePath + "\\" + fileName; 
			File srcFile = new File( srcFilePath );
			String renameFilePath = savePath + "\\" + id + "_profileImage" + extName;
			File renameFile = new File( renameFilePath );

			srcFile.renameTo( renameFile ); // 이름 바꾸기
			
			String imagePath = m_serverIP + contextPath + "/PlitImage" + "/" + id + "_profileImage" + extName;
			
			/** DB에 넣기 **/
			int result = dataDao.setProfileImage( imagePath, id ); // 0이면 false, 1이면 true
			
			response.getWriter().println( result ); // "true", "false" 둘중 하나를 보냄
		}
	}
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
	doProcess(request,response);
}  	

protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
	doProcess(request,response);
}	  	 
}
