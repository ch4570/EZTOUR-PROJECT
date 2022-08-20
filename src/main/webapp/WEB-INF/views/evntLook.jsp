<%--
  Created by IntelliJ IDEA.
  User: fhohf
  Date: 2022-08-17(017)
  Time: 오전 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <title>이벤트 상품 페이지</title>
    <style>
        .date{
            text-align: right;
            padding: 10px;
        }
        .h1{

            margin: 30px 30px 30px 30px;
        }
        .btn{

            width: 250px;
            height: 50px;
            font-size: 20px;
            font-weight: 400px;
            margin: 0 auto;
            padding: 10px;
        }
        .img-fluid{
            margin-left: auto;
            margin-right: auto;
            display: block;
            width: 40%;
        }
    </style>
</head>
<body>
<h1>이벤트 제목</h1>
<div class="date">
    <span class="label">이벤트 기간</span>
    "0000.00.00 부터 0000.00.00 까지"
    <hr>
</div>
<img src="교원 이벤트 이미지.png" class="img-fluid" alt="Responsive image">
<br>
<button type="button" id="listBtn"class="btn btn-secondary btn-lg btn-block">목록</button>
</body>
<script>
    $("#listBtn").on("click", function(){
        location.href="/event/eventList";
    });
</script>
</html>