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
import java.util.List;

import db.DBUtil;
import entity.Admin;
import entity.Course;
import entity.Model;
import entity.Role;


/**
 * @author 佳阳
 * 
 */
public class BackDaoImpl implements BackDao{

	/**
	 * 
	 */
	public BackDaoImpl() {
		// TODO Auto-generated constructor stub
	}


	/* (non-Javadoc)
	 * @see db.back.BackDao#login(entity.Admin)
	 */
	@Override
	public Admin login(Admin admin) {
		DBUtil util= new DBUtil();
		Connection conn=util.openConnection();	
		List<Model> models= new ArrayList<Model>();
		try {	
			String sql="select id,name,username,userpass from operator where username =? and userpass=?";
			String sql1="select id,name,type,img,sref from model WHERE id in (SELECT model_id from model_role WHERE role_id=(select id from role WHERE id in (SELECT role_id from role_operator WHERE operator_id=?)))";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,admin.getUser());
			pstmt.setString(2,admin.getPass());
			
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{	
				admin.setId(rs.getInt("id"));
				admin.setName(rs.getString("name".trim()));
				admin.setUser(rs.getString("username").trim());
				admin.setPass(rs.getString("userpass").trim());
				
				PreparedStatement pstmt1=conn.prepareStatement(sql1);
				pstmt1.setInt(1,admin.getId());
				ResultSet rs1=pstmt1.executeQuery();
				while(rs1.next()){
					Model model=new Model();
					model.setId(rs1.getInt("id"));
					model.setName(rs1.getString("name"));
					model.setType(rs1.getInt("type"));
					model.setImg(rs1.getString("img"));
					model.setSref(rs1.getString("sref"));
					models.add(model);
				}
				Role role = new Role();
				role.setModels(models);
				admin.setRole(role);
				return admin;
			}
			pstmt.close();
			conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}

		return null;		
	}
	
	
	/* (non-Javadoc)
	 * @see db.TeacherDao#addTeacher(org.cqut.sm.teacher.entity.Teacher)
	 */
	@Override
	public boolean addCourse(String add_course,String add_course_person) {
		boolean flag= false;
		DBUtil util=new DBUtil();
		Connection conn= util.openConnection();
		
		String sqlString="insert into course(intro,principal) values('"+add_course+"','"+add_course_person+"')";
				
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
	public boolean editCourse( int id,String intro,String principal) {
		boolean flag= false;
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql = "update course set intro='"+intro+"',principal='"+principal+"' where id='"+id+"'";
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
	public boolean delCourse(int id) {
		boolean flag= false;
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql = "delete from Course where id= ?";
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
	public List<Course> query() {
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql = "select id,intro,principal from course";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List<Course> list = new ArrayList<Course>();
			while(rs.next()){
				int id=rs.getInt(1);
				String intro = rs.getString(2);
				String principal = rs.getString(3);
				Course course = new Course();
				course.setId(id);
				course.setIntro(intro);
				course.setPrincipal(principal);
				list.add(course);
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
	public List<Course> getRow( int id) {
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql = "select id,intro,principal from course where id='"+id+"'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List<Course> list = new ArrayList<Course>();
			while(rs.next()){
				int id1=rs.getInt(1);
				String intro=rs.getString(2);
				String principal = rs.getString(3);
				Course course = new Course();
				course.setId(id1);
				course.setIntro(intro);
				course.setPrincipal(principal);
				list.add(course);
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
