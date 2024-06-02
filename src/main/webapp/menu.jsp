<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, food.moduels.Menu"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Page</title>

    <!-- Bootstrap CSS (assuming you have Bootstrap included) -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="css/menu.css">
    <!-- Your custom CSS styles can be added here -->
    <style>
        
    </style>
</head>
<body>
    <%@ include file="main.jsp" %>
    <!-- Content Section -->
    <div class="container mt-3">
        <h1 class="text-center mb-4">Menu</h1>
        <div class="row">
            <% 
            List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
            if (menuList != null) {
                for (Menu menu : menuList) {
            %>
            <!-- Menu Item -->
            <div class="col-md-3 mb-4">
                <div class="card">
                    <img src="https://source.unsplash.com/300x200/?food" class="card-img-top" alt="Menu Item">
                    <div class="card-body">
                        <h5 class="card-title"><%= menu.getItemName() %></h5>
                        <p class="card-text"><%= menu.getDescription() %></p>
                        <div class="d-flex align-items-center">
                            <p class="mb-0">Price: <%= menu.getPrice() %></p>
                            <p class="mb-0">Availability: <%= menu.getisAvailable() %></p>
                        </div> 
                        
                        <form action="CartServlet" method="get"><!-- Add action attribute -->
						    Qts: <input type="number" name="quantity" value="1" min="1" class="quantity-input">
						    <button class="btn btn-add-to-cart">Add to Cart</button>
						    <input type="hidden" name="itemId" value="<%= menu.getMenuId() %>">
						    <input type="hidden" name="action" value="add">
						</form>

                        
                    </div>
                </div>
            </div>
            <%
                }
            } else {
                System.out.print("null");
            }
            %>
        </div>
    </div>

    <!-- Bootstrap JS (including jQuery) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- Font Awesome JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/js/all.min.js" integrity="sha512-GWzVrcGlo0TxTRvz9ttioyYJ+Wwk9Ck0G81D+eO63BaqHaJ3YZX9wuqjwgfcV/MrB2PhaVX9DkYVhbFpStnqpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>
