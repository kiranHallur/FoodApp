package food.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import food.daoImp.MenuDAOImpl;
import food.moduels.Menu;

/**
 * Servlet implementation class User
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MenuDAOImpl mdi;

	@Override
	public void init() throws ServletException {
		mdi = new MenuDAOImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String action = request.getParameter("action");
//		System.out.println("action:" + action);
//		if (action != null) {
//			if (action.equals("register")) {
//				register(request, response);
//			}else if(action.equals("login")) {
//				login(request, response);
//			}
				
//			
//		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("action:" + action);
		if (action != null) {
			if (action.equals("menu")) {
				menuGetByRest(request, response);
			}
//			else if(action.equals("menuGetById")){
//				getMenu(request, response);
//			}
		}
	}

	private void menuGetByRest(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // Retrieve menu information (you can replace this with your actual logic)
		String restaurantId = request.getParameter("restaurantId");
		
		HttpSession session = request.getSession();
		Object user = session.getAttribute("User");
		if(user != null) {
			System.out.println("menu user got");
		List<Menu> menuList = mdi.getAllMenuByRestaurant(Integer.parseInt(restaurantId));

	    // Set the menuList as a request attribute
	    request.setAttribute("menuList", menuList);

	    // Forward the request to a JSP page to display the menu
	    RequestDispatcher dispatcher = request.getRequestDispatcher("menu.jsp");
	    dispatcher.include(request, response);
		}else {
			response.sendRedirect("login.jsp");
		}
	}
	
	private void menuget(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // Retrieve menu information (you can replace this with your actual logic)
		String restaurantId = request.getParameter("restaurantId");
		
		HttpSession session = request.getSession();
		Object user = session.getAttribute("User");
		
		if(user != null) {
			System.out.println("menu user got");
		List<Menu> menuList = mdi.getAllMenuByRestaurant(Integer.parseInt(restaurantId));

	    // Set the menuList as a request attribute
	    request.setAttribute("menuList", menuList);

	    // Forward the request to a JSP page to display the menu
	    RequestDispatcher dispatcher = request.getRequestDispatcher("menu.jsp");
	    dispatcher.include(request, response);
		}else {
			response.sendRedirect("login.jsp");
		}
	}
	

}
