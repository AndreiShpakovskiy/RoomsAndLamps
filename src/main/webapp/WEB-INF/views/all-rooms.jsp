<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/res/css/all-rooms.css"/>" rel="stylesheet" type="text/css"/>
    <title>All Rooms</title>
</head>

<body>
<div class="header">
    <h1>ROOMS AND LAMPS</h1>
    <article>
        <form action="/new">
            <button type="submit" class="addbutton">CREATE NEW ROOM</button>
        </form>
    </article>
</div>

<article>
    <table>
        <tr>
            <th>Name</th>
            <th>Country</th>
            <th>Action</th>
        </tr>

        <c:forEach var="room" items="${allRooms}">
            <tr>
                <td>${room.name}</td>
                <td>${countryService.getCountryById(room.countryId).name}</td>
                <td>
                    <form action="/enter/${room.id}">
                        <input type="submit" value="ENTER" />
                    </form>
<%--                    <a href="/enter/${room.countryId}">Enter</a>--%>
                </td>
            </tr>
        </c:forEach>

    </table>
</article>

<div class="footer">
    <h3>Copyright © Andrei Shpakovskiy, 2020</h3>
</div>
</body>
</html>
