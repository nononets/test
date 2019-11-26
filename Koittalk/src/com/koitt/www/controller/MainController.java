package com.koitt.www.controller;

/**
 * 
 * @author 송은혁
 * @since 2019-11-25
 * @version 1.0
 * @see
 * 
 */

import javax.servlet.http.*;

public interface MainController {
	String execute(HttpServletRequest req, HttpServletResponse resp);
}
