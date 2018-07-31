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
public class OperatorDaoImpl implements OperatorDao{

	/* (non-Javadoc)
	 * @see db.back.OperatorDao#query()
	 */
	@Override
	public List<Admin> queryaAdmins() {
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql = "SELECT id,name,username from operator";
		String sql1= "SELECT operator.id,operator.`name` as operator,operator.username,role.`name` as rolename,role.id as roleid,model.`name` as modelname FROM operator,role,role_operator,model,model_role WHERE operator.id = ? AND operator.id = role_operator.operator_id AND role.id = role_operator.role_id AND model.id = model_role.model_id AND role.id = model_role.role_id";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List<Admin> list = new ArrayList<Admin>();
			while(rs.next()){
				Admin admin = new Admin();
				int id=rs.getInt(1);
				String name = rs.getString(2);
				String username = rs.getString(3);
				admin.setId(id);
				admin.setName(name);
				admin.setUser(username);
				PreparedStatement pstmt=conn.prepareStatement(sql1);
				pstmt.setInt(1, id);
				ResultSet rs1=pstmt.executeQuery();
				List<Model> models= new ArrayList<Model>();
				Role role=new Role();
				while(rs1.next()){
					role.setId(rs1.getInt("roleid"));
					role.setRole(rs1.getString("rolename"));				
					Model model=new Model();
					model.setName(rs1.getString("modelname"));
					models.add(model);
				}
				role.setModels(models);
				admin.setRole(role);
				//admin.setModels(models);

				
				list.add(admin);
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
	 * @see db.back.OperatorDao#queryModels()
	 */
	@Override
	public List<Role> queryrRoles() {
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql = "SELECT id,name from role";
		String sql1="SELECT model.`name` as modelname from role,model,model_role where role.id=model_role.role_id and model.id=model_role.model_id and role.id=?";
		List<Role> roles=new ArrayList<Role>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Role role= new Role();
				int id=rs.getInt("id");
				String name= rs.getString("name");
				role.setId(id);
				role.setRole(name);
				
				PreparedStatement pstmt=conn.prepareStatement(sql1);
				pstmt.setInt(1, id);
				ResultSet rs1=pstmt.executeQuery();
				List<Model> models=new ArrayList<Model>();
				while(rs1.next()){
					Model model= new Model();
					model.setName(rs1.getString("modelname"));
					models.add(model);
				}
				role.setModels(models);
				roles.add(role);
			}
			return roles;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see db.back.OperatorDao#queryModels()
	 */
	@Override
	public List<Model> queryModels() {
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql= "select id,name,type,img,sref from model";
		List<Model> models=new ArrayList<Model>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Model model= new Model();
				model.setId(rs.getInt("id"));
				model.setName(rs.getString("name"));
				models.add(model);
			}
			return models;
		} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
	}

	/* (non-Javadoc)
	 * @see db.back.OperatorDao#delRole(int)
	 */
	@Override
	public boolean delRole(int id) {
		Boolean flag=false;
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql= "DELETE from role WHERE role.id=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
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
	 * @see db.back.OperatorDao#delOperator(int)
	 */
	@Override
	public boolean delOperator(int id) {
		Boolean flag=false;
		DBUtil	util = new DBUtil();
		Connection conn = util.openConnection();
		String sql= "DELETE from operator WHERE operator.id=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
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
	 * @see db.back.OperatorDao#addRole(java.lang.String, java.lang.String[])
	 */
	@Override
	public boolean addRole(String name, String[] str) {
		boolean flag= false;
		DBUtil util=new DBUtil();
		Connection conn= util.openConnection();
		
		String sql_role="insert into role(name) values(?)";//新增用户
		String sql_id = "select  MAX(id)as id from role";//查询新增用户id
		String sql_model_role="insert into model_role(model_role.model_id,model_role.role_id) values(?,?)";
		
		try {
			PreparedStatement pstmt_role=conn.prepareStatement(sql_role);
			pstmt_role.setString(1, name);
			if(pstmt_role.executeUpdate()>0)
			{
				PreparedStatement pstmt_id= conn.prepareStatement(sql_id);
				ResultSet  rs_id= pstmt_id.executeQuery();	
				if(rs_id.next()){
					int role_id=rs_id.getInt(1);
					PreparedStatement pstmt_model_role=conn.prepareStatement(sql_model_role);
					for(String s:str){
						int strId=Integer.parseInt(s);
						pstmt_model_role.setInt(1, strId);
						pstmt_model_role.setInt(2, role_id);
						pstmt_model_role.executeUpdate();
					}
					flag=true;
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	/* (non-Javadoc)
	 * @see db.back.OperatorDao#addOperator(entity.Admin)
	 */
	@Override
	public boolean addOperator(Admin admin) {
		boolean flag= false;
		DBUtil util=new DBUtil();
		Connection conn= util.openConnection();
		String img="front/member.png";
		String sql_operator="insert into operator(name,username,userpass,img) values(?,?,?,?)";//新增操作员
		String sql_id = "select  MAX(id)as id from operator";//查询新增操作员id
		String sql_operator_role="insert into role_operator(role_operator.operator_id,role_operator.role_id) values(?,?)";
		PreparedStatement pstmt_operator;
		try {
			pstmt_operator = conn.prepareStatement(sql_operator);
			pstmt_operator.setString(1, admin.getName());
			pstmt_operator.setString(2, admin.getUser());
			pstmt_operator.setString(3, admin.getPass());
			pstmt_operator.setString(4, img);			
			if(pstmt_operator.executeUpdate()>0){
				PreparedStatement pstmt_id= conn.prepareStatement(sql_id);
				ResultSet  rs_id= pstmt_id.executeQuery();	
				if(rs_id.next()){
					int operator_id=rs_id.getInt(1);
					PreparedStatement pstmt_operator_role=conn.prepareStatement(sql_operator_role);
					pstmt_operator_role.setInt(1, operator_id);
					pstmt_operator_role.setInt(2, admin.getRole().getId());
					pstmt_operator_role.executeUpdate();
					flag=true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return flag;
	}

	/* (non-Javadoc)
	 * @see db.back.OperatorDao#editRole(int, java.lang.String, java.lang.String[])
	 */
	@Override
	public boolean editRole(int id, String name, String[] str) {
		boolean flag= false;
		DBUtil util=new DBUtil();
		Connection conn= util.openConnection();
		String sql_role="update role set name=? where id=?";//修改用户
		String sql_model_role_delete = "delete from model_role where role_id= ?";//删除模块
		String sql_model_role_add="insert into model_role(model_role.model_id,model_role.role_id) values(?,?)";//增加模块
		try {
			PreparedStatement pstmt_role = conn.prepareStatement(sql_role);
			pstmt_role.setString(1,name);
			pstmt_role.setInt(2, id);

			if(pstmt_role.executeUpdate()>0)
			{
				PreparedStatement pstmt_model_role_delete = conn.prepareStatement(sql_model_role_delete);
				pstmt_model_role_delete.setInt(1, id);
				if(pstmt_model_role_delete.executeUpdate()>0)
				{
					PreparedStatement pstmt_model_role_add =conn.prepareStatement(sql_model_role_add);
					for(String s:str){
						int strId=Integer.parseInt(s);
						pstmt_model_role_add.setInt(1, strId);
						pstmt_model_role_add.setInt(2, id);
						pstmt_model_role_add.executeUpdate();
					}
					
				flag=true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/* (non-Javadoc)
	 * @see db.back.OperatorDao#editOperator(entity.Admin)
	 */
	@Override
	public boolean editOperator(Admin admin) {
		boolean flag= false;
		DBUtil util=new DBUtil();
		Connection conn= util.openConnection();
		String sql_operator="update operator set name=?,username=? where id=?";//修改用户
		String sql_role_operator_delete = "delete from role_operator where operator_id= ?";//删除模块
		String sql_role_operator_add="insert into role_operator(role_operator.operator_id,role_operator.role_id) values(?,?)";//增加模块
		try {
			PreparedStatement pstmt_role = conn.prepareStatement(sql_operator);
			pstmt_role.setString(1,admin.getName());
			pstmt_role.setString(2,admin.getUser());
			pstmt_role.setInt(3,admin.getId() );

			if(pstmt_role.executeUpdate()>0)
			{
				PreparedStatement pstmt_role_operator_delete = conn.prepareStatement(sql_role_operator_delete);
				pstmt_role_operator_delete.setInt(1, admin.getId());
				if(pstmt_role_operator_delete.executeUpdate()>0)
				{
					PreparedStatement pstmt_model_role_add =conn.prepareStatement(sql_role_operator_add);
			
					pstmt_model_role_add.setInt(1, admin.getId());
					pstmt_model_role_add.setInt(2, admin.getRole().getId());
					pstmt_model_role_add.executeUpdate();
					
				flag=true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
