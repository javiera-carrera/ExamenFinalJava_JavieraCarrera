<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detalles de la Serie</title>
</head>
<body style="font-family: Arial, sans-serif; margin: 40px;">
    <h2><c:out value="${serie.titulo}"/></h2>
    <a href="/dashboard">Volver al Dashboard</a> | <a href="/series/${serie.id}/editar">Editar</a>
    <hr>

    <p><strong>Año de Lanzamiento:</strong> <c:out value="${serie.anio}"/></p>
    <p><strong>Descripción:</strong></p>
    <p style="background: #f4f4f4; padding: 15px; border-radius: 5px;"><c:out value="${serie.description}"/></p>
</body>
</html>
