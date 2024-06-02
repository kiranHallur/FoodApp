package food.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import food.daoImp.UserDAOImpl;
import food.moduels.User;
import food.utility.OtpGenrater;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAOImpl udi;

    @Override
    public void init() throws ServletException {
        udi = new UserDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("action:" + action);
        if (action != null) {
            if (action.equals("register")) {
                register(request, response);
            } else if (action.equals("login")) {
                login(request, response);
            } 
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Get method action" + action);
        if (action != null) {
            if (action.equals("logout")) {
                logout(request, response);
            }
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String role = request.getParameter("role");

        User u = new User(username, password, email, address, role);
        int i = udi.addUser(u);
        System.out.println(i);

        if (i > 0) {
            request.setAttribute("status", "success");
        } else {
            request.setAttribute("status", "failed");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        // Assuming you have a UserDAOImpl instance named udi
        UserDAOImpl udi = new UserDAOImpl();

        // Create a User object with the provided username and password
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);

        // Perform login
        User loginResult = udi.loginUser(username, password);
        System.out.println("Login Result: " + loginResult);

        if (loginResult != null) {
            // Create a session
            HttpSession session = request.getSession();
            // Set user information in the session
            session.setAttribute("User", loginResult);
            session.setAttribute("username", username);
            request.setAttribute("status", "success");
            System.out.println("Login successful");
        } else {
            // Failed login, set an attribute for displaying an error message
            System.out.println("Login failed");
            request.setAttribute("status", "failed");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Invalidate the current session to log the user out
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            System.out.println("User logged out");
        }

        // Redirect to the login page after logout
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

  
}
	