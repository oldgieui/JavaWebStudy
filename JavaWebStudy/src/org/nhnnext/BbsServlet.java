package org.nhnnext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BbsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		BbsDAO dao = new BbsDAO();
		dao.addArticle(title, content);
		ArrayList<BbsArticle> bbsList = dao.showBoard();
		for (BbsArticle bbsArticle : bbsList) {
			writer.println(bbsArticle.getName() + " | " + bbsArticle.getTitle()
					+ "|" + bbsArticle.getContents() + "<br>");
		}
		writer.close();
	}

}
