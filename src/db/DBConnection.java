package db;

import java.sql.*;

// DB�� ������ ����ϴ� �޼ҵ�
// DB �۾��� �� �� �ʿ��� �۾����� ����


/*
 * 1) JDBC ����̹��� ����
 * 2) �����ͺ��̽� ����
 * 3) SQL ���� �ۼ� �� ����
 * 4) ��� ���� ��� �� ���� ����
 */
public class DBConnection {

	// JDBC ����̹��� ����
    static{
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		// Class   : �ڹ� API���� �����Ǵ� Ŭ����
    		// forName : �Ķ���ͷ� ������ Ŭ������ �ν��Ͻ� �޸𸮿� �ø��� ������ �ϴ� �޼ҵ�
    		// DB�� ���ø����̼��� �����ϱ� ���� DB ����̹��� �޸𸮷� �ø��� �κ�
    	}
    	catch(ClassNotFoundException e){
    		e.printStackTrace();
    		System.out.println("����̹��� ã�� �� �����ϴ�.");
    	}
    }

    // �����ͺ��̽� ����
    public static Connection getConnection(){
    	Connection con=null;
    	try{
    		// getConnection( url, id, pw );
    		con = DriverManager.
    				getConnection("jdbc:mysql://localhost:3306/plit?testWhileIdle=true&validationQuery=select 1", "root", "root");
    		con.setAutoCommit(true);
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    		System.out.println(" �����ͺ��̽��� ���� �� �� �����ϴ�.");
    	}
    	return con;
    }

    public static void close(Connection con){
    	try{
    		if( con != null )
    		{
    			con.close();
    		}		
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }

    public static void close(Statement stmt){
    	try{
    		if( stmt != null )
    			stmt.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }

    public static void close(ResultSet rs){
    	try{
    		if( rs != null )
    			rs.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	} 	  
    }
      
    // commit
    // �۾� ����� ������ ��ũ�� ���� �ϰ�
    // ���� �۾��� ���������� �Ϸ� �Ǿ����� �����ڿ��� �˷���
    public static void commit(Connection con){
    	try{
    		con.commit();
    		System.out.println("commit success");
    	}
    	catch(Exception e){
    		System.out.println("commit fail");
    	}
    }
    
    // rollback : ���� ���·� ����		  
    public static void rollback(Connection con){
    	  try{
    		  con.rollback();
    		  System.out.println("rollback success");
    	  }
    	  catch(Exception e){
    		  System.out.println("rollback fail");
    	  }
      }
}