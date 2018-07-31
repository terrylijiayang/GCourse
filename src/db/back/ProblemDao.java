/**
 * 
 */
package db.back;

import java.util.List;

import entity.Problem;

/**
 * @author 佳阳
 * 
 */
public interface ProblemDao {
	//新增问题信息
	public boolean addProblem(Problem problem);
	//修改问题信息
	public boolean editProblem(Problem problem);
	//删除问题信息
	public boolean delProblem(int id);
	//查看所有问题信息
	public List<Problem> query();
}
