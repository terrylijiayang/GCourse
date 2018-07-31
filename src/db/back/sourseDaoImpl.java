/**
 * 
 */
package db.back;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.DBUtil;
import entity.Admin;
import entity.Course;
import entity.Dynamic;
import entity.Source;


/**
 * @author 佳阳
 * 
 */
public class sourseDaoImpl implements sourseDao{

	/**
	 * 
	 */
	public sourseDaoImpl() {
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
	public boolean addSourse(String img,String name,String oper) {
		boolean flag= false;
		DBUtil util=new DBUtil();
		Connection conn= util.openConnection();
		
		String sqlString="insert into source(img,name,operate) values('"+img+"','"+name+"','"+oper+"')";
				
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
	public boolean editSourse( int id,String img,String name) {
		boolean flag= false;
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql = "update source set img='"+img+"',name='"+name+"' where id='"+id+"'";
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
	public boolean delSourse(int id) {
		boolean flag= false;
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql = "delete from source where id= ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
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
	public List<Source> query() {
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql = "select id,img, name ,operate from source";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List<Source> list = new ArrayList<Source>();
			while(rs.next()){
				int id=rs.getInt(1);
				String img = rs.getString(2);
				String name=rs.getString(3);
				String operate=rs.getString(4);
				Source sourse = new Source();
				sourse.setId(id);
				sourse.setImg(img);
				sourse.setName(name);
				sourse.setOperate(operate);
				list.add(sourse);
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
	public List<Source> getRow( int id) {
		// TODO Auto-generated method stub
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql = "select id,img,name from source where id='"+id+"'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List<Source> list = new ArrayList<Source>();
			while(rs.next()){
				int id1=rs.getInt(1);
				String img=rs.getString(2);
				String name = rs.getString(3);
				Source source = new Source();
				source.setId(id1);
				source.setImg(img);
				source.setName(name);
				list.add(source);
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			util.closeConnection(conn);
		}
		return null;
	}
	

}
