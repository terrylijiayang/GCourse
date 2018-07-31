package servlet.back;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import org.json.JSONObject;

import db.back.BackDao;
import db.back.BackDaoImpl;
import entity.Admin;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		if(methods.equals("frontHome")){
			String name = request.getParameter("username");  
		    String pwd = request.getParameter("userpass");
		    Admin admin= new Admin();
		    admin.setUser(name);
		    admin.setPass(pwd);
		    BackDao backDao=new BackDaoImpl();
		    PrintWriter pw = response.getWriter();  
		    admin=backDao.login(admin);
		    if(admin==null){
		    	 pw.println("0".toString());//登录失败
		    }else{
		    	JSONObject jsonobj = new JSONObject(); 
		 		jsonobj.put("id", admin.getId());
		 		jsonobj.put("name", admin.getName());
		 		jsonobj.put("models", admin.getRole().getModels());
		         pw.println(jsonobj);
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
