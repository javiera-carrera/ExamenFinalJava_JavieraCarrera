<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Nueva Serie</title>
</head>
<body style="font-family: Arial, sans-serif; margin: 40px;">
    <h2>Agregar una Nueva Serie</h2>
    <a href="/dashboard">Volver al Dashboard</a>
    <hr>

    <c:if test="${errorSerie != null}">
        <p style="color: red; font-weight: bold;"><c:out value="${errorSerie}"/></p>
    </c:if>

    <form action="/series/crear" method="POST" style="max-width: 400px;">
        <p>
            <label>Título de la Serie:</label><br>
            <input type="text" name="titulo" style="width: 100%;" required>
        </p>
        <p>
            <label>Año de Lanzamiento:</label><br>
            <input type="number" name="anio" style="width: 100%;" required>
        </p>
        <p>
            <label>Descripción:</label><br>
            <textarea name="descripcion" rows="4" style="width: 100%;" required></textarea>
        </p>
        <button type="submit" style="background-color: green; color: white; padding: 10px;">Crear Serie</button>
    </form>
</body>
</html>