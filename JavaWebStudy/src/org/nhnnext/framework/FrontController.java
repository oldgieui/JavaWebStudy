package org.nhnnext.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nhnnext.ControllerMap;

@WebServlet("*.do")
public class FrontController extends HttpServlet{
	public FrontController(){
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
		System.out.println("service start.");
		String uri = req.getRequestURI();
		System.out.println(uri);
		
		ControllerMap.getController(uri).service(req, resp);
//		각 컨트롤러에 있는 RequestDispatcher~라인에서 NullPointerException 발생함. getServletContext()?
	}
	
}
