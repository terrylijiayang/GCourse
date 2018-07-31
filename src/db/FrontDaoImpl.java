/**
 * 
 */
package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.back.OperatorDao;
import entity.Admin;
import entity.Course;
import entity.Dynamic;
import entity.Source;

/**
 * @author 佳阳
 * 
 */
public class FrontDaoImpl implements FrontDao{

	/* (non-Javadoc)
	 * @see db.FrontDao#query()
	 */
	@Override
	public Course query() {
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		Course course = new Course();
		List<Dynamic> dynamics=new ArrayList<Dynamic>();
		List<Source> sources= new ArrayList<Source>();
		List<Admin> admins= new ArrayList<Admin>();
		String sql = "select intro,principal from course";
		String sql1 = "select id,content,time from dynamic ORDER BY time DESC";
		String sql2 = "select id, name,img from source";
		String sql3 = "select id, name,img from operator";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				String intro=rs.getString(1);
				String principal = rs.getString(2);
				ResultSet rs1 = stmt.executeQuery(sql1);
				while(rs1.next()){
					int id=rs1.getInt(1);
					String content = rs1.getString(2);
					String time= rs1.getString(3);
					Dynamic dynamic=new Dynamic();
					dynamic.setId(id);
					dynamic.setContent(content);
					dynamic.setTime(time);
					dynamics.add(dynamic);
				}
				ResultSet rs2 = stmt.executeQuery(sql2);
				while(rs2.next()){
					int id=rs2.getInt(1);
					String name = rs2.getString(2);
					String img = rs2.getString(3);
					Source source=new Source();
					source.setId(id);
					source.setImg(img);
					source.setName(name);
					sources.add(source);
				}
				ResultSet rs3 = stmt.executeQuery(sql3);
				while(rs3.next()){
					int id=rs3.getInt(1);
					String name = rs3.getString(2);
					String img = rs3.getString(3);
					Admin admin = new Admin();
					admin.setId(id);
					admin.setImg(img);
					admin.setName(name);
					admins.add(admin);
				}
				course.setDynamics(dynamics);
				course.setSources(sources);
				course.setAdmins(admins);
				course.setIntro(intro);
				course.setPrincipal(principal);
			}
			return course;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			util.closeConnection(conn);
		}
		return null;
	}



}
