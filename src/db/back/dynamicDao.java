/**
 * 
 */
package db.back;

import java.util.List;

import entity.Admin;
import entity.Course;
import entity.Dynamic;


public interface dynamicDao {
	//验证管理员身份
	public boolean login(Admin admin);
	//新增dynamic信息
	public boolean addDynamic(String add_dy, String time,String operate);
	//修改dynamic信息
	public boolean editDynamic(int id,String content,String time);
	//删除dynamic信息
	public boolean delDynamic(int id);
	//查看dynamic教师信息
	public List<Dynamic> query();
		
	//修改时获取一行数据
	public List<Dynamic> getRow(int id);
	
}
