package org.nhnnext.filter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SimpleFilter implements Filter {
	PrintWriter writer;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter init");
		String logfile = this.getClass().getClassLoader().getParent().toString() + "testlog.log";
		//filterConfig.getInitParameter("LOGFILE_NAME");
//		if (logfile == null) {
//			throw new ServletException("logfile not found.");
//		}
		try {
			writer = new PrintWriter(new FileWriter(logfile, true), true);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServletException("cannot open the logfile.");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		GregorianCalendar now = new GregorianCalendar();
		String remoteAddr = request.getRemoteAddr();
		System.out.println("dofilter 1");
		writer.printf("현재일시 : %TF %TT %n", now, now);
		writer.println("remote address : " + remoteAddr);
		chain.doFilter(request, response);
		String contentType = response.getContentType();
		writer.println("response content type : " + contentType);
		writer.println("=======================");
		System.out.println("dofilter 2");
	}

	@Override
	public void destroy() {
		System.out.println("filter destroyed");
		writer.close();
	}

}
