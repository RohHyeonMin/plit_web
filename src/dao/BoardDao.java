package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.BoardBean;

public class BoardDao {
	
	Connection con;
	PreparedStatement pstmt; // �ѹ��� �������� �м�, ������ �� ĳ�ÿ� ��� ����
	ResultSet rs;  // select���� ���� �� executeQuery() �޼��带 ����ϸ�, ���� ����� java.sql.ResultSet������ ����
	
	public BoardDao() {
	}
	
	/*
	//���� ���� ���ϱ�.
	public int getListCount() {
		int x= 0;
		
		try{
			con=ds.getConnection();
			pstmt=con.prepareStatement("select count(*) from memberboard");
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				x=rs.getInt(1);
			}
		}catch(Exception ex){
			System.out.println("getListCount ����: " + ex);			
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return x;
	}
	*/
	
	//�� ��� ��������
	public ArrayList<BoardBean> getBoardList( int page, int limit )
	{
		DataDao dao = new DataDao();
		try {
			con = dao.ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		String board_list_sql = "select * from board";
				//+ "where rnum>=? and rnum<=?";
		
		ArrayList list = new ArrayList();
		
		int startrow = ( ( page - 1 ) * 10 ) + 1; //�б� ������ row ��ȣ.
		int endrow = startrow + limit - 1; //���� ������ row ��ȣ.		
		try{
			pstmt = con.prepareStatement(board_list_sql);
			//pstmt.setInt(1, startrow);
			//pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				// �ڸ�Ʈ ���� ����
				BoardBean board = new BoardBean();
				board.setBoardNum(rs.getInt("board_num"));
				board.setBoardContent(rs.getString("board_content"));
				board.setDateBoard(rs.getString("date_board"));
				board.setGood(rs.getInt("good"));
				board.setHits(rs.getInt("hits"));
				board.setBoardTag(rs.getString("board_tag"));
				board.setLatitude(rs.getDouble("board_latitude"));
				board.setLongitude(rs.getDouble("board_longitude"));
				board.setId(rs.getString("id"));
				list.add(board);
			}		
			return list;
		}catch(Exception ex){
			System.out.println("getBoardList ���� : " + ex);
		}finally{
			try {
				if(pstmt !=null)
					pstmt.close();
				if(rs !=null)
					rs.close();
				if(con !=null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	//�� ���� ����. ( �ڼ��� ���� )
	public BoardBean getBoardDetail( int num ) throws Exception{

		DataDao dao = new DataDao();
		try {
			con = dao.ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		BoardBean board = null;
		try{
			pstmt = con.prepareStatement(
					"select * from board where BOARD_NUM = ?");
			pstmt.setInt(1, num);		
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				board = new BoardBean();
				board.setBoardNum(rs.getInt("board_num"));
				board.setBoardContent(rs.getString("board_content"));
				board.setDateBoard(rs.getString("date_board"));
				board.setGood(rs.getInt("good"));
				board.setHits(rs.getInt("hits"));
				board.setBoardTag(rs.getString("board_tag"));
				board.setLatitude(rs.getDouble("board_latitude"));
				board.setLongitude(rs.getDouble("board_longitude"));
				board.setId(rs.getString("id"));
			}
			return board;
			
		}catch(Exception ex){
			System.out.println("getBoardDetail ���� : " + ex);
		}finally{
			try {
				if(pstmt !=null)
					pstmt.close();
				if(rs !=null)
					rs.close();
				if(con !=null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	// �۾����� ������ ���� ��������
	public String getWriterImg( String id ) throws SQLException
	{
		String board_list_sql = "select user_photo from user_info where id = ?";		
		String img = null;

		DataDao dao = new DataDao();
		try {
			con = dao.ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		try{
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				if( rs.getString("user_photo") == null)
				{
					img = "icon.jpg";
				}
				else
				{
					img = rs.getString("user_photo");
				}
			}
			
			

			return img;
		}catch(Exception ex){
			System.out.println("getBoardList ���� : " + ex);
		}finally{
			try {
				if(pstmt !=null)
					pstmt.close();
				if(rs !=null)
					rs.close();
				if(con !=null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}	
	/*
	//�� ���.
	public boolean boardInsert(BoardBean board){
		int num =0;
		String sql="";
		
		int result=0;
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(
					"select max(board_num) from memberboard");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num =rs.getInt(1)+1;
			else
				num=1;
			
			sql="insert into memberboard (BOARD_NUM,BOARD_ID,BOARD_SUBJECT,";
			sql+="BOARD_CONTENT, BOARD_FILE,BOARD_RE_REF,"+
				"BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,"+
				"BOARD_DATE) values(?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBOARD_ID());
			pstmt.setString(3, board.getBOARD_SUBJECT());
			pstmt.setString(4, board.getBOARD_CONTENT());
			pstmt.setString(5, board.getBOARD_FILE());
			pstmt.setInt(6, num);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			
			result=pstmt.executeUpdate();
			if(result==0)return false;
			
			return true;
		}catch(Exception ex){
			System.out.println("boardInsert ���� : "+ex);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return false;
	}
	
	//�� �亯.
	public int boardReply(BoardBean board){
		String board_max_sql="select max(board_num) from memberboard";
		String sql="";
		int num=0;
		int result=0;
		
		int re_ref=board.getBOARD_RE_REF();
		int re_lev=board.getBOARD_RE_LEV();
		int re_seq=board.getBOARD_RE_SEQ();
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			if(rs.next())num =rs.getInt(1)+1;
			else num=1;
			
			sql="update memberboard set BOARD_RE_SEQ=BOARD_RE_SEQ+1 ";
			sql+="where BOARD_RE_REF=? and BOARD_RE_SEQ>?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,re_ref);
			pstmt.setInt(2,re_seq);
			result=pstmt.executeUpdate();
			
			re_seq = re_seq + 1;
			re_lev = re_lev+1;
			
			sql="insert into memberboard (BOARD_NUM,BOARD_ID,BOARD_SUBJECT,";
			sql+="BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,";
			sql+="BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE) ";
			sql+="values(?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBOARD_ID());
			pstmt.setString(3, board.getBOARD_SUBJECT());
			pstmt.setString(4, board.getBOARD_CONTENT());
			pstmt.setString(5, ""); //���忡�� ������ ���ε����� ����.
			pstmt.setInt(6, re_ref);
			pstmt.setInt(7, re_lev);
			pstmt.setInt(8, re_seq);
			pstmt.setInt(9, 0);
			pstmt.executeUpdate();
			return num;
		}catch(SQLException ex){
			System.out.println("boardReply ���� : "+ex);
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return 0;
	}
	
	//�� ����.
	public boolean boardModify(BoardBean modifyboard) throws Exception{
		String sql="update memberboard set BOARD_SUBJECT=?,";
		sql+="BOARD_CONTENT=? where BOARD_NUM=?";
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, modifyboard.getBOARD_SUBJECT());
			pstmt.setString(2, modifyboard.getBOARD_CONTENT());
			pstmt.setInt(3, modifyboard.getBOARD_NUM());
			pstmt.executeUpdate();
			return true;
		}catch(Exception ex){
			System.out.println("boardModify ���� : " + ex);
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
			}
		return false;
	}
	
	//�� ����.
	public boolean boardDelete(int num){
		String board_delete_sql=
			"delete from memberboard where BOARD_num=?";
		
		int result=0;
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
			if(result==0)return false;
			
			return true;
		}catch(Exception ex){
			System.out.println("boardDelete ���� : "+ex);
		}finally{
			try{
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception ex) {}
		}
		
		return false;
	}
	
	//��ȸ�� ������Ʈ.
	public void setReadCountUpdate(int num) throws Exception{
		String sql="update memberboard set BOARD_READCOUNT = "+
			"BOARD_READCOUNT+1 where BOARD_NUM = "+num;
		
		try{
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch(SQLException ex){
			System.out.println("setReadCountUpdate ���� : "+ex);
		}
		finally{
			try{
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception ex) {}
		}
	}
	
	//�۾������� Ȯ��.
	public boolean isBoardWriter(int num,String id){
		System.out.println("id="+id);
		String board_sql=
			"select * from memberboard where BOARD_NUM=?";
		
		try{
			con=ds.getConnection();
			pstmt=con.prepareStatement(board_sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			rs.next();
			
			if(id.equals(rs.getString("BOARD_ID"))){
				return true;
			}
		}catch(SQLException ex){
			System.out.println("isBoardWriter ���� : "+ex);
		}
		finally{
			try{
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception ex) {}
		}
		return false;
	}
	*/
}
