/**
 * 
 */
package db.back;

import java.util.List;

import entity.Admin;
import entity.Course;


/**
 * @author 佳阳
 * 
 */
public interface BackDao {
	//用户登录
	public Admin login(Admin admin);
	//新增course信息
	public boolean addCourse(String add_course, String add_course_person);
	//修改course信息
	public boolean editCourse(int id,String intro,String principal);
	//删除course信息
	public boolean delCourse(int id);
	//查看所有course信息
	public List<Course> query();
	//修改时获取一行数据
	public List<Course> getRow(int id);
	
	
	
	
	
	
}
