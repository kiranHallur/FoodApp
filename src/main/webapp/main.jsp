<!-- navbar.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
    .bg-sky-blue {
        background-color: #87CEEB; /* Sky blue color */
    }
</style>

<!-- Navigation Bar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-sky-blue">
    <a class="navbar-brand" href="#"><i class="fa-solid fa-bowl-rice"></i></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="navbar-nav ml-auto" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">About</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="menu.jsp">Services</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Contact</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="adminLogin.jsp">Admin</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="cart.jsp"><i class="fa-solid fa-cart-shopping"></i></a>
            </li>
        </ul>

        <!-- Content and Login/Logout Buttons -->
        <div class="navbar-nav ml-auto">
            <!-- Check if the user is logged in -->
            <% if (session.getAttribute("username") != null) { %>
                <!-- Display greeting and logout button if logged in -->
                <div class="nav-item">
                    <p class="nav-link">Welcome, <%= session.getAttribute("username") %>!</p>
                </div>
                <div class="nav-item">
                    <form action="UserServlet" method="get">
                        <button type="submit" name="action" value="logout" >Logout</button>
                    </form>
                </div>
            <% } else { %>
                <!-- Display login and register links if not logged in -->
                <div class="nav-item">
                    <a class="nav-link" href="login.jsp">Login</a>
                </div>
                <div class="nav-item">
                    <a class="nav-link" href="register.jsp">Register</a>
                </div>
            <% } %>
        </div>
    </div>
</nav>
