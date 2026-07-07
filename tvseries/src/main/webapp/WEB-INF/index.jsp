<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TV Series - Bienvenido</title>
</head>
<body style="font-family: Arial, sans-serif; margin: 40px; background-color: #f8f9fa;">

    <h1>TV Series</h1>
    <hr>

    <div style="display: flex; gap: 50px;">
        <div style="flex: 1; border: 1px solid #ccc; padding: 20px; background: white; border-radius: 5px;">
			<h2>Registro</h2>
			            
			            <c:if test="${errorRegistro != null}">
			                <p style="color: red; font-weight: bold;"><c:out value="${errorRegistro}"/></p>
			            </c:if>

			            <form action="/registro" method="POST">
                <p>
                    <label>Nombre:</label><br>
                    <input type="text" name="nombre" required>
                </p>
                <p>
                    <label>Apellido:</label><br>
                    <input type="text" name="apellido" required>
                </p>
                <p>
                    <label>Correo Electrónico:</label><br>
                    <input type="email" name="correo" required>
                </p>
                <p>
                    <label>Contraseña:</label><br>
                    <input type="password" name="password" required>
                </p>
                <p>
                    <label>Confirmar Contraseña:</label><br>
                    <input type="password" name="confirmacion" required>
                </p>
                <button type="submit" style="background-color: blue; color: white; padding: 10px; border: none; border-radius: 3px; cursor: pointer;">Registrarse</button>
            </form>
        </div>

        <div style="flex: 1; border: 1px solid #ccc; padding: 20px; background: white; border-radius: 5px;">
            <h2>Login</h2>
            
            <c:if test="${errorLogin != null}">
                <p style="color: red; font-weight: bold;"><c:out value="${errorLogin}"/></p>
            </c:if>

            <form action="/login" method="POST">
                <p>
                    <label>Correo:</label><br>
                    <input type="email" name="correo" required>
                </p>
                <p>
                    <label>Contraseña:</label><br>
                    <input type="password" name="password" required>
                </p>
                <button type="submit" style="background-color: blue; color: white; padding: 10px; border: none; border-radius: 3px; cursor: pointer;">Login</button>
            </form>
        </div>
    </div>

</body>
</html>