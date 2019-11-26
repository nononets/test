package com.koitt.www.controller.test;

import javax.servlet.http.*;
import com.koitt.www.controller.*;

public class Test implements MainController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/main.jsp";
		
		return view;
	}

}
