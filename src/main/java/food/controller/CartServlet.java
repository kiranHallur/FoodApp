package food.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import food.dao.MenuDAO;
import food.daoImp.MenuDAOImpl;
import food.moduels.Menu;
import food.moduels.CartItem;
import food.moduels.Cart;
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        System.out.println(action);
        if ("add".equals(action)) {
            addItemToCart(request, cart);
        } else if (action.equals("update")) {
        	updateCartItem(request, cart);
        } else if (action.equals("remove")) {
        	removeItemFromCart(request, cart);
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }
    
    
 // Inside the addItemToCart method
    private void addItemToCart(HttpServletRequest request, Cart cart) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Assuming you have a MenuDAO and CartItem class
        MenuDAO menuDAO = new MenuDAOImpl();
        Menu menuItem = menuDAO.getMenu(itemId);
        System.out.println(menuItem.getRestaurantId());
        if (menuItem != null) {
            HttpSession session = request.getSession();
            session.setAttribute("restaurantId", menuItem.getRestaurantId());
            
            CartItem cartItem = new CartItem(
                    menuItem.getMenuId(),
                    menuItem.getRestaurantId(),
                    menuItem.getItemName(),
                    quantity,  // Quantity is an int
                    menuItem.getPrice()
            );
            cart.addItems(cartItem);
        }
    }


    private void updateCartItem(HttpServletRequest request, Cart cart) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Assuming you have a method in Cart to update item quantity
        cart.updateItems(itemId, quantity);
    }

    private void removeItemFromCart(HttpServletRequest request, Cart cart) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));

        // Assuming you have a method in Cart to remove an item by itemId
        cart.removeItem(itemId);
    }

}
