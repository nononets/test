package controller;

/**
 * @author 송은혁
 * @since 2019.12.03
 */

import javax.servlet.http.*;

public interface MainController {
	String execute(HttpServletRequest req, HttpServletResponse resp);
}
