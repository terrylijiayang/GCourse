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
import entity.Problem;

/**
 * @author 佳阳
 * 
 */
public class ProblemDaoIml implements ProblemDao{

	/* (non-Javadoc)
	 * @see db.back.problemDao#addProblem(entity.Problem)
	 */
	@Override
	public boolean addProblem(Problem problem) {
		boolean flag= false;
		DBUtil util=new DBUtil();
		Connection conn= util.openConnection();
		
		String sqlString="insert into problem(name,content,operatorName)"
				+ "values(?,?,?)";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sqlString);
			int i=1;
			pstmt.setString(i++, problem.getName());
			pstmt.setString(i++, problem.getContent());
			pstmt.setString(i++, problem.getOperatorName());
			if(pstmt.executeUpdate()>0)
			{
				flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		util.closeConnection(conn);//关闭
		return flag;
	}

	/* (non-Javadoc)
	 * @see db.back.problemDao#editProblem(entity.Problem)
	 */
	@Override
	public boolean editProblem(Problem problem) {
		boolean flag= false;
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql = "update problem set name=?,content=?,operatorName=? where id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int i=1;
			pstmt.setString(i++, problem.getName());
			pstmt.setString(i++, problem.getContent());
			pstmt.setString(i++, problem.getOperatorName());
			pstmt.setInt(i++, problem.getId());
			if(pstmt.executeUpdate()>0)
			{
				flag=true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		util.closeConnection(conn);
			return flag;
	}

	/* (non-Javadoc)
	 * @see db.back.problemDao#delProblem(int)
	 */
	@Override
	public boolean delProblem(int id) {
		boolean flag= false;
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql = "delete from problem where id= ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			if(pstmt.executeUpdate()>0)
			{
				flag=true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			util.closeConnection(conn);
			return flag;

	}

	/* (non-Javadoc)
	 * @see db.back.problemDao#query()
	 */
	@Override
	public List<Problem> query() {
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql = "select id,name,content,operatorName from problem";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List<Problem> list = new ArrayList<Problem>();
			while(rs.next()){
				int id=rs.getInt(1);
				String name = rs.getString(2);
				String content = rs.getString(3);
				String operatorName=rs.getString(4);
				
				Problem problem = new Problem();
				problem.setId(id);
				problem.setName(name);
				problem.setContent(content);
				problem.setOperatorName(operatorName);
				list.add(problem);
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
