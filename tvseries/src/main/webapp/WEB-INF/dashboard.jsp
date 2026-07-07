<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TV Series - Dashboard</title>
</head>
<body style="font-family: Arial, sans-serif; margin: 40px;">
    <header style="display: flex; justify-content: space-between; align-items: center; border-bottom: 2px solid #ccc; padding-bottom: 10px;">
        <h2>Bienvenido, <c:out value="${usuarioLogueado.nombre} ${usuarioLogueado.apellido}"/></h2>
        <nav>
            <a href="/dashboard">Dashboard</a> | 
            <a href="/logout" style="color: red;">Cerrar Sesión</a>
        </nav>
    </header>

    <h3>Lista de Series de TV</h3>
    <p><a href="/series/nuevo">➕ Agregar una nueva serie</a></p>

    <table border="1" style="width: 100%; border-collapse: collapse; text-align: left;">
        <thead style="background-color: #f2f2f2;">
            <tr>
                <th style="padding: 10px;">Título</th>
                <th style="padding: 10px;">Año de Lanzamiento</th>
                <th style="padding: 10px;">Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty series}">
                    <tr>
                        <td colspan="3" style="padding: 10px; text-align: center; color: gray;">No hay series registradas aún.</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="serie" items="${series}">
                        <tr>
                            <td style="padding: 10px;"><c:out value="${serie.titulo}"/></td>
                            <td style="padding: 10px;"><c:out value="${serie.anio}"/></td>
							<td style="padding: 10px;">
							    <a href="/series/${serie.id}">👁️ Ver Detalles</a> | 
							    <a href="/series/${serie.id}/editar">✏️ Editar</a>
							</td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</body>
</html>