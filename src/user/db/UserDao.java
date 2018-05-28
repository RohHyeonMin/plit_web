package user.db;

import static dao.DBConnection.*;

import java.sql.*;
import java.util.ArrayList;
import javax.sql.DataSource;

public class UserDao 
{
	Connection con;
	PreparedStatement pstmt; // 한번의 쿼리문장 분석, 컴파일 후 캐시에 담아 재사용
	ResultSet rs; // select쿼리 실행 시 executeQuery() 메서드를 사용하며, 실행 결과로 java.sql.ResultSet형으로 리턴
	DataSource ds;
	public UserDao(Connection con) 
	{
		super();
		pstmt = null; 
		rs = null;
		this.con = con;
	}
		
	/* 사용자 로그인 정보 확인 */
	public UserBean getUserLogin(String id, String pw) 
	{
		// TODO Auto-generated method stub	
		UserBean user = null;
			
		try{
			String sql="select * from user where id=? and password=?"; // 사용자 정보 조회
			pstmt = con.prepareStatement(sql); // 쿼리문 저장
			pstmt.setString(1, id); // 첫번째 ?의 값 지정
			pstmt.setString(2, pw); // 두번째 ?의 값 지정
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				// TODO 조건에 일치하는 사용자 정보를 User 객체에 저장
				user = new UserBean();
				user.setId(rs.getString("id"));
				user.setPw(rs.getString("password"));
				user.setBirth(rs.getString(2));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
			close(rs);
		}	
		return user;
	}
	
	/* 사용자 정보 저장 : 회원 가입 ( INSERT ) */
	public boolean joinUser(UserBean user) throws SQLException
	{
		// ( ?, ?, ? )  id, password, date_birth
		String sql="INSERT INTO USER VALUES (?,?,?)";
		int count = 0;
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPw());
			pstmt.setString(3, user.getBirth());
			count = pstmt.executeUpdate();
			
			if( count != 0 )
			{
				return true;
			}
		}
		catch(Exception ex)
		{
			System.out.println("joinUser 에러: " + ex);			
		}finally{
			close(pstmt);
			close(rs);
			close(con);
		}	
		return false;
	}
	
	/* 전체 사용자 정보 조회 */
	public ArrayList getUserList() throws SQLException
	{
		String sql = "SELECT * FROM USER";
		ArrayList<UserBean> userlist = new ArrayList();
		
		try{
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				UserBean user = new UserBean();
				user.setId(rs.getString("id"));
				user.setPw(rs.getString("password"));
				user.setBirth(rs.getString("date_birth"));		
				userlist.add(user);
			}
			
			return userlist;
		}
		catch(Exception ex)
		{
			System.out.println("getUserList 에러: " + ex);			
		}
		finally{
			close(pstmt);
			close(rs);
			close(con);
		}
		return null;
	}
	
	/* 사용자 정보 조회 */
	public UserBean getUserInfo( String id ) throws SQLException
	{
		// 넘겨받은 해당 아이디의 사용자 정보 조회
		String sql = "SELECT * FROM USER WHERE id = ?";
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			rs.next();
			
			UserBean user = new UserBean();
			user.setId(rs.getString("id"));
			user.setPw(rs.getString("password"));
			user.setBirth(rs.getString("date_birth"));		

			return user;
		}
		catch(Exception ex)
		{
			System.out.println("getUserInfo 에러: " + ex);			
		}
		finally
		{
			close(pstmt);
			close(rs);
			close(con);
		}	
		return null;
	}
	
	/* 사용자 정보 삭제 ( 회원 탈퇴 ) */
	public boolean deleteUserInfo(String id) throws SQLException
	{
		String sql="DELETE FROM USER WHERE id= ? ";
		boolean isSuccess = false;
		int result1=0;
		boolean result =false;
		System.out.println("deleteId = " + id);
		try{
			con=ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			result1=pstmt.executeUpdate();

			if(result1 > 0)
			{
				result = true;
			}
			isSuccess=true;
		}catch(Exception ex)
		{
			System.out.println("deleteMember 에러: " + ex);	
		}
		finally
		{
			try
			{
				if(isSuccess){
					con.commit();
				}
				else{
					con.rollback();
				}
			}
			catch(Exception e){};
			close(pstmt);
			close(rs);
			close(con);
		}
		
		return result;
	}
}

