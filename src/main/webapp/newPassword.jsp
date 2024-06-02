<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Your existing head section remains unchanged -->
</head>
<body oncontextmenu='return false' class='snippet-body'>
    <!-- Existing content remains unchanged -->
    <form class="card mt-4" action="FpNewPassword" method="POST" >
        <div class="card-body">
            <div class="form-group">
                <label for="password">New Password</label>
                <input class="form-control" type="password" name="password" id="password" required="">
            </div>
            <div class="form-group">
                <label for="confPassword">Confirm New Password</label>
                <input class="form-control" type="password" name="confPassword" id="confPassword" required="">
            </div>
        </div>
        <div class="card-footer">
            <button class="btn btn-success" type="submit">Reset Password</button>
            <a class="btn btn-danger" href="login.jsp">Back to Login</a>
        </div>
    </form>
    <!-- Your existing script tags remain unchanged -->
   
	
    
</body>
</html>
