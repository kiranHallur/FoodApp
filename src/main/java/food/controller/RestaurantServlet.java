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

import food.daoImp.RestaurantDAOImpl;
import food.moduels.Restaurant;

@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 System.out.println("RestaurantServlet doGet method called");
        RestaurantDAOImpl rdi = new RestaurantDAOImpl();
        
        List<Restaurant> restaurants = rdi.getAllRestaurants();
        
        
        	
        	request.setAttribute("restaurantList", restaurants);
        	HttpSession session = request.getSession();
        	session.setAttribute("restaurantList", restaurants);
        	RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        	rd.include(request, response);
			

        
    }
}
