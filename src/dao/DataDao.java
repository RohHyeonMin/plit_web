package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;

public class DataDao {
	
	public Connection con;
	PreparedStatement pstmt; // �ѹ��� �������� �м�, ������ �� ĳ�ÿ� ��� ����
	ResultSet rs;  // select���� ���� �� executeQuery() �޼��带 ����ϸ�, ���� ����� java.sql.ResultSet������ ����
	public DataSource ds; // Connection pool ����
	
	public DataDao() { // 
		try{
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/MysqlDB");	  	
		}catch(Exception ex){
			System.out.println("DB connection failed : " + ex);
			return;
		}
	}
	
	// 친구리스트 불러오기
	public JSONArray getFriendList( String id, int index , int count )
	{
		ArrayList<String> friendNames = new ArrayList<String>();
		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement("SELECT id_respondent FROM friends WHERE id_applicant= ? ");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while( rs.next() )
			{
				friendNames.add( rs.getString( "id_respondent" ) ) ; // id에 대한 친구들 id를 다 얻어온다.
			}
			
			if( friendNames.size() > 0 ) // 1명이라도 친구가 있으면
			{
				// 자원 반환해주고
				pstmt.close();
				pstmt = null;
				rs.close();
				rs = null;
				
				String sql = "SELECT * FROM user_info WHERE id=" + friendNames.get(0); // 첫번째꺼 넣어주고
				for( int i=1; i<friendNames.size(); i++) // 친구 갯수만큼 인덱스 1부터 시작, 위에 0 넣어줬으니까
				{
					sql += " OR ";
					sql += "id=" + friendNames.get(i);
				}
				pstmt = con.prepareStatement( sql );
				rs = pstmt.executeQuery();
				
				JSONArray jsonArray = new JSONArray();
				while( rs.next() )
				{
					JSONObject object = new JSONObject();
					
					object.put( "user_photo", rs.getString("user_photo") );
					object.put( "id", rs.getString("id") );
					object.put( "message", rs.getString("massage") );
					
					jsonArray.add( object );
				}
	
				return jsonArray;
			}
		}
		catch(Exception ex)
		{
			System.out.println("getFriendList 실패: " + ex);
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(pstmt!=null) 
					pstmt.close();
				if(con!=null) 
					con.close();
			}
			catch(SQLException ex){}
		}

		return null;
	}

	// 상태메세지 변경하기
	public int setStateMessage( String id, String message ) 
	{
		int result = 0;
		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement("UPDATE user_info SET massage= ? WHERE id= ?");
			pstmt.setString(1, message);
			pstmt.setString(2, id);
			
			int rs = pstmt.executeUpdate();
			
			if( rs != 0 ) // 업데이트 성공 시
				result = 1;

		}
		catch(Exception ex)
		{
			System.out.println("setStateMessage 실패: " + ex);
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(pstmt!=null) 
					pstmt.close();
				if(con!=null) 
					con.close();
			}
			catch(SQLException ex){}
		}
		
		return result;
	}
	// 상태메세지 변경하기
	public int setProfileImage( String imagePath, String id ) 
	{
		int result = 0;
		try
		{			
			con = ds.getConnection();
			pstmt = con.prepareStatement("UPDATE user_info SET user_photo= ? WHERE id= ?");
			pstmt.setString(1, imagePath);
			pstmt.setString(2, id);
			
			int rs = pstmt.executeUpdate();
			
			if( rs != 0 ) // 업데이트 성공 시
				result = 1;
		}
		catch(Exception ex)
		{
			System.out.println("setProfileImage 실패: " + ex);
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(pstmt!=null) 
					pstmt.close();
				if(con!=null) 
					con.close();
			}
			catch(SQLException ex){}
		}
		
		return result;

	}
	
	 public int setBoard( String Content, String user_name, String tag, double latitude ,double longitude, ArrayList<String> photos )
	 {
	    	int result = 0; // 0 fail, 1 success

	    	try 
	    	{                    
	    		double board_latitude = latitude;
	    		double board_longitude = longitude;
	    		
	    		// 기존에 50m안에 있는 코멘트로 위도,경도 일치시켜주는 작업 
				con = ds.getConnection();
	    		pstmt = con.prepareStatement("SELECT board_latitude,board_longitude FROM board WHERE board_latitude >= ? - 0.0001 AND  board_latitude <= ? + 0.0001 AND board_longitude >= ? - 0.0001 AND board_longitude <= ? + 0.0001 LIMIT 1");
	    		pstmt.setDouble(1, latitude);
	    		pstmt.setDouble(2, latitude);
	    		pstmt.setDouble(3, longitude);
	    		pstmt.setDouble(4, longitude);
	    		
	    		rs = pstmt.executeQuery();
	    		
	    		if(rs.next())
	    		{
	    			board_latitude = rs.getDouble(1);
	    			board_longitude = rs.getDouble(2);
	    		}	
	    		 		
	    		if( rs != null )
	    			rs.close();
	    		if( pstmt != null )
	    			pstmt.close();
	    		rs = null;
	    		pstmt = null;
	    		
	    		pstmt = con.prepareStatement("INSERT INTO board( board_content, date_board, good,hits, board_tag, board_latitude, board_longitude, id ) VALUES (?,sysdate(),0,0,?,?,?,?)"); // mysql : sysdate(), cubrid : sysdatetime()
	    		pstmt.setString(1, Content);
	    		pstmt.setString(2, tag);
	    		pstmt.setDouble(3, board_latitude);
	    		pstmt.setDouble(4, board_longitude);
	    		pstmt.setString(5, user_name);
	    		result = pstmt.executeUpdate();

	    		if( rs != null )
	    			rs.close();
	    		if( pstmt != null )
	    			pstmt.close();
	    		rs = null;
	    		pstmt = null;
	    		
	    		pstmt = con.prepareStatement("SELECT board_num FROM board WHERE id = ? ORDER BY board_num DESC LIMIT 1");
	    		pstmt.setString(1, user_name);
	    		rs = pstmt.executeQuery();
	    		
	    		int board_num = 0;
	    		
	    		if(rs.next())
	    		{
	    			board_num = rs.getInt("board_num");
	    		}
	    		else
	    		{
	    			result = 0; // 실패
	    			return result;
	    		}
	    		

	    		for( int i=0; i<photos.size(); i++)
	    		{
		    		pstmt = con.prepareStatement("INSERT INTO board_photo VALUES (?,?)");
		    		pstmt.setInt(1, board_num);
		    		pstmt.setString(2, photos.get(i));
		    		int success = pstmt.executeUpdate();
		    		
		    		if( pstmt != null )
		    			pstmt.close();
	    		}
	    		
	    		result = 1;
	    	}
	    	catch ( Exception e ) 
	    	{
	    		e.printStackTrace();
	    	} 
	    	finally 
	    	{
	    		try
				{
					if(rs!=null)
						rs.close();
					if(pstmt!=null) 
						pstmt.close();
					if(con!=null) 
						con.close();
				}
				catch(SQLException ex){}
	    	}
	    	
	    	return result;
	    }   
}
