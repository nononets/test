package dispatch;

/** 
* @author 송은혁
* @since 2019.12.03
*/

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import controller.*;

@WebServlet("*.test")
public class MainDispatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Properties prop = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		prop = new Properties();
		FileInputStream is = null;
		
		try {
			String path = this.getClass().getResource("").getPath();
			String pName = "main.properties";
			String src = "../resources/" + pName;
			is = new FileInputStream(path + src);
			
			prop.load(is);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String context = req.getContextPath();
		String path = uri.substring(context.length());
		
		Object controller = getMainController(prop, path);
		
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
			main = (MainController) 
					Class.forName(prop.getProperty(uri)).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return main;
	}
	
}