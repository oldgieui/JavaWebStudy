package org.nhnnext;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nhnnext.framework.Controller;

public class BbsController extends HttpServlet implements Controller{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		service(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		service(req, resp);
	}
	
	//접근 제어자 문제 : 원래 HttpServlet에서 제공하는 것은 protected인데 인터페이스에서는 protected를 쓸 수 없다.. 어떻게 해야 할까... 
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String id = session.getAttribute("ID").toString();
		req.setCharacterEncoding("UTF-8");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		BbsDAO dao = new BbsDAO();
		dao.addArticle(id, title, content);
//		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
//		rd.forward(req, resp);
	}

}
