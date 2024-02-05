<%@page contentType="text/html;" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login | Request Headers</title>
</head>
<body>
<div>
    <h1>Login</h1>
    <form action="login" method="POST">
        <div>
            <label>
                Username
                <input type="text" name="username">
            </label>
        </div>
        <div>
            <label>
                Password
                <input type="password" name="password">
            </label>
        </div>
        <div>
            <button type="submit">Login</button>
        </div>
    </form>
</div>
</body>
</html>