package com.koitt.www.dispatch;

/**
 * 
 * @author 송은혁
 * @since 2019-11-25
 * @version 1.0
 * @see
 * 
 */

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.koitt.www.controller.*;

public class Dispatch extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Properties prop = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		prop = new Properties();
		
		FileReader reader = null;
		
		try {
			String path = this.getClass().getResource("").getPath();
			
			reader = new FileReader(path + "../resources/Example.properties");
			
			prop.load(reader);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		
		Object controller = getMainController(prop, uri);
		
		req.setAttribute("isRd", false);
		
		String view = "";
		
		if (controller != null) {
			MainController con = (MainController) controller;
			view = con.execute(req, resp);
		}
		
		boolean isRd = (boolean) req.getAttribute("isRd");
		
		if (isRd) {
			resp.sendRedirect(view);
		} else {
			RequestDispatcher rd = req.getRequestDispatcher(view);
			
			rd.forward(req, resp);
			
		}
		
	}

	public MainController getMainController(Properties prop, String uri) {
		MainController main = null; 
		try {
			main = (MainController) Class.forName(prop.getProperty(uri)).getDeclaredConstructor().newInstance();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return main;
	}
	
}
