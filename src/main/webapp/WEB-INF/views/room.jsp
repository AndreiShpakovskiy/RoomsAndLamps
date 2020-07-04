<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/res/css/room.css"/>" rel="stylesheet" type="text/css"/>
    <title>${room.name}</title>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.0.3/sockjs.js"></script>--%>
    <script src="../../res/js/sockjs-0.3.4.js"></script>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>--%>
    <script src="../../res/js/stomp.js"></script>
    <script type="text/javascript">
        let stompClient = null;

        function setConnected(connected) {
            document.getElementById('lamp').style.visibility = connected ? 'visible' : 'hidden';
            alert(${room.lampOn});
            setLampState(${room.lampOn} ? 'true' : 'false');
            console.log("Connected");
        }

        function connect() {
            //alert("CONNECT");
            let socket = new SockJS('/switch');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                setConnected(true);
                stompClient.subscribe('/room/switch-state/${room.id}', function(greeting) {
                    alert(greeting.body);
                    setLampState(String(JSON.parse(greeting.body).lampOn));
                });
            });
        }

        function disconnect() {
            //alert("DISCONNECT");
            // if (stompClient != null) {
            //     stompClient.disconnect();
            // }
            // setConnected(false);
            // console.log("Disconnected");
        }

        function switchLampState() {
            //alert("SWITCH STATE");
            stompClient.send("/lamp/switch", {}, JSON.stringify({ "id": ${room.id} }));
        }

        document.addEventListener('DOMContentLoaded', function() {
            connect();
        }, false);
        
        function setLampState(lampState) {
            if (lampState === "true") {
                document.getElementById("lamp").setAttribute("src", "${pageContext.request.contextPath}/res/pictures/lamp-on.png")
            }
            else {
                document.getElementById("lamp").setAttribute("src", "${pageContext.request.contextPath}/res/pictures/lamp-off.png")
            }
        }

    </script>
</head>

<body onload="disconnect()">
<noscript>
    <h2 style="color: #ff0000">Turn on JavaScript in your browser, please!</h2>
</noscript>

<div>
    <div class="outer">
        <div class="middle">
            <div class="inner">
                <h2>${room.name}</h2>
                <hr>

                <input style="width: 50%; outline: none" id="lamp" onclick="switchLampState()" type="image" src="${pageContext.request.contextPath}/res/pictures/lamp-off.png" alt="Lamp"/>
                <p>Click to switch state ☝🏻</p>

            </div>
        </div>
    </div>
</div>
</body>
</html>