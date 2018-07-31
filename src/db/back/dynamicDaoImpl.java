/**
 * 
 */
package db.back;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.DBUtil;
import entity.Admin;
import entity.Course;
import entity.Dynamic;


/**
 * @author 佳阳
 * 
 */
public class dynamicDaoImpl implements dynamicDao{

	/**
	 * 
	 */
	String timeString;
	public dynamicDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see db.TeacherDao#login(org.cqut.sm.teacher.entity.Admin)
	 */
	
	
	
	@Override
	public boolean login(Admin admin) {
		boolean flag= false;
		DBUtil util= new DBUtil();
		Connection conn=util.openConnection();	
		try {
			
			String sql="select * from admin where name =? and pass=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,admin.getUser());
			pstmt.setString(2,admin.getPass());
			
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{	
				admin.setUser(rs.getString("name").trim());
				admin.setPass(rs.getString("pass").trim());
				flag=true;
			}
			pstmt.close();
			conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}

		return flag;		
	}

	/* (non-Javadoc)
	 * @see db.TeacherDao#addTeacher(org.cqut.sm.teacher.entity.Teacher)
	 */
	@Override
	public boolean addDynamic(String content,String time,String oper) {
		boolean flag= false;
		DBUtil util=new DBUtil();
		Connection conn= util.openConnection();
		
		String sqlString="insert into dynamic(content,time,operate) values('"+content+"','"+getTime()+"','"+oper+"')";
				
		try {
			PreparedStatement pstmt=conn.prepareStatement(sqlString);
			
			if(pstmt.executeUpdate()>0)
			{
				flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	/* (non-Javadoc)
	 * @see db.TeacherDao#editTeacher(org.cqut.sm.teacher.entity.Teacher)
	 */
	@Override
	public boolean editDynamic( int id,String content,String time) {
		boolean flag= false;
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql = "update dynamic set content='"+content+"',time='"+time+"' where id='"+id+"'";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			if(pstmt.executeUpdate()>0)
			{
				flag=true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.closeConnection(conn);
			return flag;
		}
	}

	/* (non-Javadoc)
	 * @see db.TeacherDao#delTeacher(int)
	 */
	@Override
	public boolean delDynamic(int id) {
		boolean flag= false;
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql = "delete from dynamic where id='"+id+"'  ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
		/*	pstmt.setInt(1, id);*/
			
			if(pstmt.executeUpdate()>0)
			{
				flag=true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			util.closeConnection(conn);
			return flag;
		}
	}

	/* (non-Javadoc)
	 * @see db.TeacherDao#selTeacher(int)
	 */

	/* (non-Javadoc)
	 * @see db.TeacherDao#query()
	 */
	@Override
	public List<Dynamic> query() {
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql = "select id,content, time,operate from dynamic";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List<Dynamic> list = new ArrayList<Dynamic>();
			while(rs.next()){
				int id=rs.getInt(1);
				String content = rs.getString(2);
				String time=rs.getString(3);
				String operate=rs.getString(4);
				Dynamic dynamic = new Dynamic();
				dynamic.setId(id);
				dynamic.setContent(content);
				dynamic.setTime(time);
				dynamic.setOperate(operate);
				list.add(dynamic);
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			util.closeConnection(conn);
		}
		return null;
	}

	@Override
	public List<Dynamic> getRow( int id) {
		// TODO Auto-generated method stub
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql = "select id,content,time from dynamic where id='"+id+"'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List<Dynamic> list = new ArrayList<Dynamic>();
			while(rs.next()){
				int id1=rs.getInt(1);
				String content=rs.getString(2);
				String time = rs.getString(3);
				Dynamic dynamic = new Dynamic();
				dynamic.setId(id1);
				dynamic.setContent(content);
				dynamic.setTime(time);
				list.add(dynamic);
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			util.closeConnection(conn);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see db.back.dynamicDao#selDynamic(int)
	 */
	
	
	
	public  String getTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		timeString=df.format(new Date());
		return timeString;
		
	}

}
