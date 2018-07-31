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








import sun.awt.SunHints.Value;
import db.back.*;
import db.FrontDao;
import db.FrontDaoImpl;


import entity.Course;
import entity.Dynamic;
import entity.Source;

/**
 * Servlet implementation class BackServlet
 */
@WebServlet("/BackServlet")
public class BackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");	 	
		String methods=request.getParameter("name");
		if(methods.equals("Cou_Dispaly")){
			BackDao backDao=new BackDaoImpl();
			List<Course> list =  backDao.query();
			 JSONArray obj = JSONArray.fromObject(list);
			 response.getWriter().write(obj.toString());
		}
//		if(methods.equals("delete")){
//			String id1=request.getParameter("id");
//			int id = Integer.valueOf(id1).intValue();
//			System.out.println("delete....");
//			BackDao backDao=new BackDaoImpl();
//			backDao.delCourse(id);
//			response.getWriter().write("1");
//		}
		
		if(methods.equals("Cou_GetRow")){
			System.out.println("getRow....");
			String id1=request.getParameter("id");
			int id = Integer.valueOf(id1).intValue();
			BackDao backDao=new BackDaoImpl();
			List<Course> list =  backDao.getRow(id);
			JSONArray obj = JSONArray.fromObject(list);
			response.getWriter().write(obj.toString());
			
		}
		if(methods.equals("Co_change")){
			System.out.println("change....");
			String id1=request.getParameter("id");
			String intro=request.getParameter("intro");
			String principal=request.getParameter("principal");
			int id = Integer.valueOf(id1).intValue();
			BackDao backDao=new BackDaoImpl();
			backDao.editCourse(id, intro, principal);
			
		}
		if(methods.equals("Cou_add")){
			String add_course=request.getParameter("add_course");
			String add_course_person=request.getParameter("add_course_person");
			BackDao backDao=new BackDaoImpl();
			backDao.addCourse(add_course,add_course_person);
			
		}
		
		if(methods.equals("Cou_data_row")){
			String id1=request.getParameter("id");
			int id = Integer.valueOf(id1).intValue();
			BackDao backDao=new BackDaoImpl();
			List<Course> list =  backDao.getRow(id);
			JSONArray obj = JSONArray.fromObject(list);
			response.getWriter().write(obj.toString());
			
		}
		//动态课程
		if(methods.equals("Dyhome")){
			dynamicDao dynamic=new dynamicDaoImpl();
			List<Dynamic> list = dynamic.query();
			 JSONArray obj = JSONArray.fromObject(list);
			 response.getWriter().write(obj.toString());
		}
		if(methods.equals("Dydelete")){
			String id1=request.getParameter("id");
			int id = Integer.valueOf(id1).intValue();
			System.out.println("delete....");
			dynamicDao dynamic=new dynamicDaoImpl();
			dynamic.delDynamic(id);
			response.getWriter().write("1");
		}
		if(methods.equals("DygetRow")){
			System.out.println("madi....");
			String id1=request.getParameter("id");
			int id = Integer.valueOf(id1).intValue();
			dynamicDao dynamic=new dynamicDaoImpl();
			List<Dynamic> list =  dynamic.getRow(id);
			JSONArray obj = JSONArray.fromObject(list);
			response.getWriter().write(obj.toString());
			
		}
		if(methods.equals("Dychange")){
			String id1=request.getParameter("id");
			String content=request.getParameter("content");
			String time=request.getParameter("time");
			int id = Integer.valueOf(id1).intValue();
			dynamicDao dynamic=new dynamicDaoImpl();
			dynamic.editDynamic(id, content, time);
			
		}
		if(methods.equals("dy_add")){
			String adddy=request.getParameter("content");
			String adddytime=request.getParameter("time");
			String operString=request.getParameter("oper");
			dynamicDao dynamic=new dynamicDaoImpl();
			dynamic.addDynamic(adddy, adddytime,operString);
			response.getWriter().write("1");
		}
		
		if(methods.equals("Dydata_row")){
			System.out.println("data_row....");
			String id1=request.getParameter("id");
			int id = Integer.valueOf(id1).intValue();
			dynamicDao dynamic=new dynamicDaoImpl();
			List<Dynamic> list =  dynamic.getRow(id);
			JSONArray obj = JSONArray.fromObject(list);
			response.getWriter().write(obj.toString());
			
		}
		if (methods.equals("getdy_Row")) {
			String id1=request.getParameter("id");
			int id = Integer.valueOf(id1).intValue();
			dynamicDao dynamic=new dynamicDaoImpl();
			List<Dynamic> list =  dynamic.getRow(id);
			JSONArray obj = JSONArray.fromObject(list);
			response.getWriter().write(obj.toString());
		}
		
		
		if(methods.equals("Sohome")){
			sourseDao sourse=new sourseDaoImpl();
			List<Source> list = sourse.query();
			 JSONArray obj = JSONArray.fromObject(list);
			 response.getWriter().write(obj.toString());
		}
		if(methods.equals("Sodelete")){
			String id1=request.getParameter("id");
			int id = Integer.valueOf(id1).intValue();
			System.out.println("delete....");
			sourseDao sourse=new sourseDaoImpl();
			sourse.delSourse(id);
			response.getWriter().write("1");
		}
		if(methods.equals("SogetRow")){
			System.out.println("getRow....");
			String id1=request.getParameter("id");
			int id = Integer.valueOf(id1).intValue();
			sourseDao sourse=new sourseDaoImpl();
			List<Source> list =  sourse.getRow(id);
			JSONArray obj = JSONArray.fromObject(list);
			response.getWriter().write(obj.toString());
			
		}
		if(methods.equals("reso_change")){
			String id1=request.getParameter("id");
			String file=request.getParameter("file");
			String imgString="../../images/front/"+file;
			String name=request.getParameter("finame");
			int id = Integer.valueOf(id1).intValue();
			sourseDao sourse=new sourseDaoImpl();
			sourse.editSourse(id, imgString, name);
			
		}
		if(methods.equals("res_add")){
			String img=request.getParameter("img");
			String img_urlString="../../images/front/"+img;
			String name=request.getParameter("rename");
			String oper=request.getParameter("oper");
			sourseDao sourse=new sourseDaoImpl();
			sourse.addSourse(img_urlString,name,oper);
		}
		
		if(methods.equals("reso_data_row")){
			String id1=request.getParameter("id");
			int id = Integer.valueOf(id1).intValue();
			sourseDao sourse=new sourseDaoImpl();
			List<Source> list =  sourse.getRow(id);
			JSONArray obj = JSONArray.fromObject(list);
			response.getWriter().write(obj.toString());
			
		}
		
		
		
	}

}
