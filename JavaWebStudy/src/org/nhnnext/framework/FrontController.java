package org.nhnnext.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nhnnext.ConfigInitializer;

@WebServlet("*.do")
public class FrontController extends HttpServlet{
	private static final long serialVersionUID = -7001096326843269526L;

	@Override
	public void init() throws ServletException {
		System.out.println("Map Initialize!! " + getServletContext().getRealPath(""));
		ConfigInitializer.init();
	}

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
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println("service start.");
		String uri = req.getRequestURI();
		uri = uri.split(".do", 1)[0];
		System.out.println(uri);
		ControllerMap.getController(uri).service(req, resp);
	}
	
}
