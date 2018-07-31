package servlet.front;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import db.FrontDao;
import db.FrontDaoImpl;
import entity.Course;



/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
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
				FrontDao front= new FrontDaoImpl();
				Course course=front.query();
				JSONObject jsonobj = new JSONObject(); 
				jsonobj.put("intro", course.getIntro());
				jsonobj.put("principal", course.getPrincipal());
				jsonobj.put("dynamics", course.getDynamics());
				jsonobj.put("sources", course.getSources());
				jsonobj.put("teams", course.getAdmins());
				PrintWriter pw = response.getWriter();    
		        pw.println(jsonobj);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
