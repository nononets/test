package www.goo.gle.controller.test;

import javax.servlet.http.*;
import www.goo.gle.controller.*;

public class Test implements MainController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/main.jsp";
		
		return view;
	}

}
