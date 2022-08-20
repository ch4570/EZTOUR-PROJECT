<%--
  Created by IntelliJ IDEA.
  User: fhohf
  Date: 2022-08-17(017)
  Time: 오전 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" rel="stylesheet">
    <title>이벤트 목록</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        .gallery-list{
            display: flex;
            background-position: center;
            vertical-align: middle;

        }
        .tit{
            text-align: center;
        }
        .cont-inner{
            text-align: center;
            width: 1200px;
            margin: 0px auto;
        }

    </style>
</head>

<body>
<div class="cont-body etc full"  >
    <div class="cont-inner">
        <div class="cont-tab-wrap">
            <nav class="tab-default"style="font-size: 30px;">
                <a href="/etc/event/eventList" class="selected">진행중인 이벤트</a>
            </nav>
        </div>
    </div>
</div>
        <br>
<table border="1">

<c:forEach items="${list}" var="list">
    <div class="list-item" name="eventItem">
        <a>
            <div class="item-img" ><img src="/resources/images/1.PNG" class="rounded mx-auto d-block" style="width: 400px; padding:15px 15px 15px 15px;"></div>
            <div class="item-info">
                <a href="<c:url value='/event/eventList/eventListLook?evnt_No=${event.evnt_no}'/>">${list.evnt_ttl}</a>
            </div>

        </a>
    </div>

   </c:forEach>
</table>
<br>
    <div>
        <c:if test="${ph.eventShowPrev}">
            <a href="<c:url value='/event/eventList?page=${ph.eventBeginPage-1}&eventPageSize=${ph.eventPageSize}'/>">&lt;</a>
        </c:if>
        <c:forEach var="i" begin="${ph.eventBeginPage}" end="${ph.eventEndPage}">
            <a href="<c:url value='/event/eventList?eventPage=${i}&eventPageSize=${ph.eventPageSize}'/>">${i}</a>
        </c:forEach>
        <c:if test="${ph.eventShowNext}">
            <a href="<c:url value='/event/eventList?page=${ph.eventEndPage+1}&eventPageSize=${ph.eventPageSize}'/>">&gt;</a>
        </c:if>
</div>
</body>
</html>
