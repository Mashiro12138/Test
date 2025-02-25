package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.GradeDao;
import Model.Grade;

/**
 * Servlet implementation class UpdScoreServlet
 */
public class UpdScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charcet=utf-8");
		
		String stuId = request.getParameter("stuId");
		String stuName = request.getParameter("stuName");
		String courseId = request.getParameter("courseId");
		String tcId = request.getParameter("tcId");
		String classId = request.getParameter("classId");
		String grade = request.getParameter("grade");
		
		Grade grades = new Grade();
		grades.setStuId(stuId);
		grades.setStuName(stuName);
		grades.setCourseId(courseId);
		grades.setTcId(tcId);
		grades.setClassId(classId);
		grades.setCourseGrade(grade);
		
		HttpSession session = request.getSession();
		try{
			GradeDao gradeDao = new GradeDao();
			if (gradeDao.update(grades)) {
				ArrayList<Grade> list = (ArrayList<Grade>) gradeDao.find(stuId);
				session.setAttribute("grade", list);
				response.sendRedirect(request.getContextPath() + "/Jsp/UpdDelScore.jsp");
			} else {
				request.setAttribute("errerMsg", "����ʧ��");
				request.getRequestDispatcher("/Jsp/UpdDelScore.jsp").forward(request, response);
			}
		} catch(Exception ee) {
			ee.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
