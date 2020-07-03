<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <title>New Room</title>
    <link href="<c:url value="/res/css/new-room.css"/>" rel="stylesheet" type="text/css"/>
</head>

<body>

<div class="outer">
    <div class="middle">
        <div class="inner">
            <form:form method="POST" modelAttribute="room" action="create-room">
                <h2>CREATE ROOM</h2>
                <hr>

                <br>
                <input id="name" name="name" placeholder="Enter Name" required type="name">

                <div class="select-style">
                    <form:select path="countryId" items="${allRooms}"/>
                </div>

                <button type="submit" class="registerbtn">CREATE</button>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>