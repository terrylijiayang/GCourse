/**
 * 
 */
package db.back;

import java.util.List;

import entity.Admin;
import entity.Course;
import entity.Dynamic;
import entity.Source;


public interface sourseDao {
	//验证管理员身份
	public boolean login(Admin admin);
	//新增教师信息
	public boolean addSourse(String img, String name,String opString);
	//修改教师信息
	public boolean editSourse(int id,String img,String name);
	//删除教师信息
	public boolean delSourse(int id);
	//查看所有教师信息
	public List<Source> query();
		
	//修改时获取一行数据
	public List<Source> getRow(int id);
	
}
