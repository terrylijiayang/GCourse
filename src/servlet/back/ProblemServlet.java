package servlet.back;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import db.back.OperatorDao;
import db.back.OperatorDaoImpl;
import db.back.ProblemDao;
import db.back.ProblemDaoIml;
import entity.Problem;

/**
 * Servlet implementation class ProblemServlet
 */
@WebServlet("/ProblemServlet")
public class ProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProblemServlet() {
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
		if(methods.equals("addProblem")){
			Problem problem= new Problem();
			String name= request.getParameter("questionName");
			String content= request.getParameter("content");
			String operatorName= request.getParameter("operatorName");
			problem.setName(name);
			problem.setContent(content);
			problem.setOperatorName(operatorName);
			ProblemDao problemDao=new ProblemDaoIml();
			if(problemDao.addProblem(problem)){
				System.out.println("问题新增成功");
			}
		}else if(methods.equals("eidtProblem")){
			Problem problem= new Problem();
			int id=Integer.parseInt(request.getParameter("id"));
			String name= request.getParameter("questionName");
			String content= request.getParameter("content");
			String operatorName= request.getParameter("operatorName");
			problem.setId(id);
			problem.setName(name);
			problem.setContent(content);
			problem.setOperatorName(operatorName);
			ProblemDao problemDao=new ProblemDaoIml();
			if(problemDao.editProblem(problem)){
				System.out.println("问题修改成功");
			}
		}else if(methods.equals("problemsAll")){
			ProblemDao problemDao=new ProblemDaoIml();
			List<Problem> problems= problemDao.query();
			JSONArray obj = JSONArray.fromObject(problems);
			response.getWriter().write(obj.toString());
		}else if(methods.equals("delProblem")){
			int id=Integer.parseInt(request.getParameter("problemId"));
			ProblemDao problemDao=new ProblemDaoIml();
			if(problemDao.delProblem(id)){
				System.out.println("问题删除成功");
				response.getWriter().write("1".toString());
			}else{
				response.getWriter().write("0".toString());
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
