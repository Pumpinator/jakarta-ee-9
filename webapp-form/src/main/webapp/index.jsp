<%@page contentType="text/html; ISO-8859-1" pageEncoding="UTF-8" %>
<%@page import="java.util.Stack" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Form</title>
</head>
<body>
<h3>Formulario</h3>
<%
    Stack<String> errores = (Stack<String>) request.getAttribute("errores");
    if (errores != null && errores.size() > 0) {
%>
<ul>
    <%
        for (String error : errores) {
    %>
    <li>
        <%=error%>
    </li>
    <%
            }
        }
    %>
</ul>
<form action="/webapp-form/form" method="post">
    <div>
        <div>
            <input type="hidden" name="id" value="100">
        </div>
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
            <label>
                Email
                <input type="email" name="email">
            </label>
        </div>
        <div>
            <label>
                País
                <select type="email" name="pais">
                    <option value="" selected>Seleccionar</option>
                    <option value="MX">México</option>
                    <option value="US">Estados Unidos</option>
                    <option value="CA">Canadá</option>
                </select>
            </label>
        </div>
        <div>
            <label>
                Lenguajes de programación
            </label>
            <div>
                <select name="lenguajes" multiple>
                    <option value="java">Java SE</option>
                    <option value="jakartaee">Jakarta EE 9</option>
                    <option value="spring">Spring</option>
                    <option value="javascript">JS</option>
                    <option value="angular">Angular</option>
                    <option value="react">React</option>
                </select>
            </div>
        </div>
        <div>
            <label>Roles</label>
            <div>
                <label>
                    Admin
                    <input type="checkbox" name="roles" value="ROLE_ADMIN">
                </label>
                <label>
                    Usuario
                    <input type="checkbox" name="roles" value="ROLE_USER">
                </label>
                <label>
                    Moderador
                    <input type="checkbox" name="roles" value="ROLE_MODERATOR">
                </label>
            </div>
        </div>
        <div>
            <label>Idiomas</label>
            <div>
                <label>
                    Español
                    <input type="radio" name="idioma" value="es">
                </label>
                <label>
                    Inglés
                    <input type="radio" name="idioma" value="en">
                </label>
                <label>
                    Francés
                    <input type="radio" name="idioma" value="fr">
                </label>
            </div>
        </div>
        <div>
            <label>
                Habilitar
                <input type="checkbox" name="habilitar" checked>
            </label>
        </div>
        <div>
            <button type="submit">Enviar</button>
        </div>
    </div>
</form>
</body>
</html>