/**
 * 
 */
package db.back;

import java.util.List;

import entity.Admin;
import entity.Model;
import entity.Role;

/**
 * @author 佳阳
 * 
 */
public interface OperatorDao {
	//操作员查看模块
	public List<Admin> queryaAdmins();
	//角色查看模块
	public List<Role> queryrRoles();
	//获取所有模块
	public List<Model> queryModels();
	//新增角色
	public boolean addRole(String name,String[] str);
	//修改角色
	public boolean editRole(int id,String name,String[] str);
	//删除角色
	public boolean delRole(int id);
	//删除操作员
	public boolean delOperator(int id);
	//新增操作员
	public boolean addOperator(Admin admin);
	//修改操作员
	public boolean editOperator(Admin admin);
	
}
