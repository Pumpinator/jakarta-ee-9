<%@page contentType="text/html; ISO-8859-1" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Form</title>
    <link rel="stylesheet" href="webjars/bootstrap/5.3.2/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid">
    <h3 class="text-center border-bottom border-dark">Formulario</h3>
    <div>
        <%
            Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
            if (errores != null && errores.size() > 0) {
        %>
        <ul class="alert alert-danger p-5">
            <%
                for (String error : errores.values()) {
            %>
            <li>
                <%=error%>
            </li>
            <%
                    }
                }
            %>
        </ul>
    </div>
    <form class="p-3" action="/webapp-form/form" method="post">
        <div class="container-fluid">
            <div>
                <input type="hidden" name="id" value="100">
            </div>
            <div class="row mb-3">
                <label class="form-label">
                    Username
                    <div>
                        <input class="col form-control" type="text" name="username" value="${param.username}">
                    </div>
                    <%=(errores != null && errores.containsKey("username")) ? "<small style='color: red'>" + errores.get("username") + "</small>" : ""%>
                </label>

            </div>
            <div class="row mb-3">
                <label class="form-label">
                    Password
                    <div>
                        <input class="col form-control" type="password" name="password">
                    </div>
                    <%=(errores != null && errores.containsKey("password")) ? "<small style='color: red'>" + errores.get("password") + "</small>" : ""%>
                </label>
            </div>
            <div class="row mb-3">
                <label class="form-label">
                    Email
                    <div>
                        <input class="col form-control" type="email" name="email" value="${param.email}">
                    </div>
                    <%=(errores != null && errores.containsKey("email")) ? "<small style='color: red'>" + errores.get("email") + "</small>" : ""%>
                </label>
            </div>
            <div class="row mb-3">
                <label class="form-label">
                    País
                    <div>
                        <select class="col form-select" name="pais">
                            <option value="" selected>Seleccionar</option>
                            <option value="MX" ${param.pais.equals("MX") ? "selected" : ""}>México</option>
                            <option value="US" ${param.pais.equals("US") ? "selected" : ""}>Estados Unidos</option>
                            <option value="CA" ${param.pais.equals("CA") ? "selected" : ""}>Canadá</option>
                        </select>
                    </div>
                    <%=(errores != null && errores.containsKey("pais")) ? "<small style='color: red'>" + errores.get("pais") + "</small>" : ""%>
                </label>
            </div>
            <div class="mb-3">
                <label class="form-label">
                    Lenguajes de programación
                </label>
                <div>
                    <select class="form-select" name="lenguajes" multiple>
                        <option value="java" ${paramValues.lenguajes.stream().anyMatch(value -> value.equals("java")).get() ? "selected" : ""}>Java SE</option>
                        <option value="jakartaee" ${paramValues.lenguajes.stream().anyMatch(value -> value.equals("jakartaee")).get() ? "selected" : ""}>Jakarta EE 9</option>
                        <option value="spring" ${paramValues.lenguajes.stream().anyMatch(value -> value.equals("spring")).get() ? "selected" : ""}>Spring</option>
                        <option value="javascript" ${paramValues.lenguajes.stream().anyMatch(value -> value.equals("javascript")).get() ? "selected" : ""}>JS</option>
                        <option value="angular" ${paramValues.lenguajes.stream().anyMatch(value -> value.equals("angular")).get() ? "selected" : ""}>Angular</option>
                        <option value="react" ${paramValues.lenguajes.stream().anyMatch(value -> value.equals("react")).get() ? "selected" : ""}>React</option>
                    </select>
                </div>
                <%=(errores != null && errores.containsKey("lenguajes")) ? "<small style='color: red'>" + errores.get("lenguajes") + "</small>" : ""%>
            </div>
            <div class="row mb-3">
                <label class="col-auto form-label">Roles</label>
                <div class="col-auto">
                    <label class="form-check-label">
                        Admin
                        <input class="form-check" type="checkbox" name="roles" value="ROLE_ADMIN" ${paramValues.roles.stream().anyMatch(value -> value.equals("ROLE_ADMIN")).get() ? "checked" : ""}>
                    </label>
                    <label class="form-check-label">
                        Usuario
                        <input class="form-check" type="checkbox" name="roles" value="ROLE_USER" ${paramValues.roles.stream().anyMatch(value -> value.equals("ROLE_USER")).get() ? "checked" : ""}>
                    </label>
                    <label class="form-check-label">
                        Moderador
                        <input class="form-check" type="checkbox" name="roles" value="ROLE_MODERATOR" ${paramValues.roles.stream().anyMatch(value -> value.equals("ROLE_MODERATOR")).get() ? "checked" : ""}>
                    </label>
                </div>
                <%=(errores != null && errores.containsKey("roles")) ? "<small style='color: red'>" + errores.get("roles") + "</small>" : ""%>
            </div>
            <div class="row mb-3">
                <label class="col-auto form-label">Idiomas</label>
                <div class="col-auto">
                    <label class="form-check-label">
                        Español
                        <input class="form-check" type="radio" name="idioma" value="es" ${param.idioma.equals("es") ? "checked" : ""}>
                    </label>
                    <label class="form-check-label">
                        Inglés
                        <input class="form-check" type="radio" name="idioma" value="en" ${param.idioma.equals("en") ? "checked" : ""}>
                    </label>
                    <label class="form-check-label">
                        Francés
                        <input class="form-check" type="radio" name="idioma" value="fr" ${param.idioma.equals("fr") ? "checked" : ""}>
                    </label>
                </div>
                <%=(errores != null && errores.containsKey("idioma")) ? "<small style='color: red'>" + errores.get("idioma") + "</small>" : ""%>
            </div>
            <div class="mb-3">
                <label class="form-check-label">
                    Habilitar
                    <input class="form-check" type="checkbox" name="habilitar" checked>
                </label>
            </div>
            <div class="text-center">
                <button class="btn btn-primary" type="submit">Enviar</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>