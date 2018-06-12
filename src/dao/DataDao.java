package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.xml.ws.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataDao {
	
	Connection con;
	PreparedStatement pstmt; // �ѹ��� �������� �м�, ������ �� ĳ�ÿ� ��� ����
	ResultSet rs;  // select���� ���� �� executeQuery() �޼��带 ����ϸ�, ���� ����� java.sql.ResultSet������ ����
	DataSource ds; // Connection pool ����
	
	public DataDao() { // 
		try{
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/MysqlDB");	  	
		}catch(Exception ex){
			System.out.println("DB connection failed : " + ex);
			return;
		}
	}
	
	public JSONArray getFriendList( String id, int index , int count ) {
		
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
		catch(Exception ex)
		{
			System.out.println("getFriendList 실패: " + ex);
			
			return null;
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

	}
}
