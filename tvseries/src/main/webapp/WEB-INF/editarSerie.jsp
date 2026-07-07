<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Serie</title>
</head>
<body style="font-family: Arial, sans-serif; margin: 40px;">
    <h2>Editar Serie: <c:out value="${serie.titulo}"/></h2>
    <a href="/dashboard">Cancelar y Volver</a>
    <hr>

    <c:if test="${errorSerie != null}">
        <p style="color: red; font-weight: bold;"><c:out value="${errorSerie}"/></p>
    </c:if>

    <form action="/series/${serie.id}/actualizar" method="POST" style="max-width: 400px; display: inline-block;">
        <p>
            <label>Título:</label><br>
            <input type="text" name="titulo" value="${serie.titulo}" style="width: 100%;" required>
        </p>
        <p>
            <label>Año:</label><br>
            <input type="number" name="anio" value="${serie.anio}" style="width: 100%;" required>
        </p>
        <p>
            <label>Descripción:</label><br>
            <textarea name="descripcion" rows="4" style="width: 100%;" required><c:out value="${serie.description}"/></textarea>
        </p>
        <button type="submit" style="background-color: orange; color: white; padding: 10px;">Actualizar Serie</button>
    </form>

    <br><br>
    <form action="/series/${serie.id}/eliminar" method="POST" onsubmit="return confirm('¿Seguro que deseas eliminar esta serie?');">
        <button type="submit" style="background-color: red; color: white; padding: 10px; border: none; cursor: pointer;">🗑️ Eliminar Serie</button>
    </form>
</body>
</html>