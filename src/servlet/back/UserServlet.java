package servlet.back;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.json.JSONObject;

import db.back.OperatorDao;
import db.back.OperatorDaoImpl;
import entity.Admin;
import entity.Model;
import entity.Role;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");	
		String methods=request.getParameter("name");
		if(methods.equals("users")){
			//人员信息
			OperatorDao operatorDao=new OperatorDaoImpl();
			List<Admin> admins= operatorDao.queryaAdmins();
			JSONArray obj = JSONArray.fromObject(admins);
			response.getWriter().write(obj.toString());
		}else if(methods.equals("role")){
			//角色信息
			OperatorDao operatorDao=new OperatorDaoImpl();
			List<Role> roles= operatorDao.queryrRoles();
			JSONArray obj = JSONArray.fromObject(roles);
			response.getWriter().write(obj.toString());
		}else if(methods.equals("model")){
			//所有模板
			OperatorDao operatorDao=new OperatorDaoImpl();
			List<Model> models= operatorDao.queryModels();
			JSONArray obj = JSONArray.fromObject(models);
			response.getWriter().write(obj.toString());
		}else if(methods.equals("delRole")){
			//删除角色
			OperatorDao operatorDao=new OperatorDaoImpl();
			int id=Integer.parseInt(request.getParameter("roleid"));
			if(operatorDao.delRole(id)){
				response.getWriter().write("1".toString());
			}else{
				response.getWriter().write("0".toString());
			}
		}else if(methods.equals("delOperator")){
			//删除用户
			OperatorDao operatorDao=new OperatorDaoImpl();
			int id=Integer.parseInt(request.getParameter("operatorid"));
			if(operatorDao.delOperator(id)){
				response.getWriter().write("1".toString());
			}else{
				response.getWriter().write("0".toString());
			}
		}else if(methods.equals("addModel")){
			//新增模块
			String roleName=request.getParameter("roleName");
			String str=request.getParameter("chooseArr");
			String[] arr=str.split(",");
			for(String s:arr){
				System.out.print(s+"  ");
			}
			OperatorDao operatorDao=new OperatorDaoImpl();
			if(operatorDao.addRole(roleName, arr)){
				System.out.println("新增角色成功");
			}
		}else if(methods.equals("editModel")){
			int id=Integer.parseInt(request.getParameter("chooseId"));
			String roleName=request.getParameter("roleName");
			String str=request.getParameter("chooseArr");
			String[] arr=str.split(",");
			OperatorDao operatorDao=new OperatorDaoImpl();
			if(operatorDao.editRole(id,roleName, arr)){
				System.out.println("编辑角色成功");
			}
			
		}else if(methods.equals("addOperator")){
			//用户信息
			String operatorName= request.getParameter("operatorname");
			String username= request.getParameter("username");
			String userpass= request.getParameter("userpass");
			int operatorRoleid=Integer.parseInt(request.getParameter("operatorRoleid"));
			Admin admin= new Admin();
			admin.setName(operatorName);
			admin.setUser(username);
			admin.setPass(userpass);
			Role role= new Role();
			role.setId(operatorRoleid);
			admin.setRole(role);
			//新增用户
			OperatorDao operatorDao=new OperatorDaoImpl();
			if(operatorDao.addOperator(admin)){
				PrintWriter pw = response.getWriter();
				 pw.println("1".toString());//登录成功
				 System.out.println("新增操作员成功");
			}
		}else if(methods.equals("eidtOperator")){
			//用户信息
			int id=Integer.parseInt(request.getParameter("chooseId"));
			String operatorName= request.getParameter("operatorname");
			String username= request.getParameter("username");
			int operatorRoleid=Integer.parseInt(request.getParameter("operatorRoleid"));
			Admin admin= new Admin();
			admin.setId(id);
			admin.setName(operatorName);
			admin.setUser(username);
			Role role= new Role();
			role.setId(operatorRoleid);
			admin.setRole(role);
			//更新用户
			OperatorDao operatorDao=new OperatorDaoImpl();
			if(operatorDao.editOperator(admin)){
	/*			PrintWriter pw = response.getWriter();
				 pw.println("1".toString());//登录成功
*/				 System.out.println("新增操作员成功");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
