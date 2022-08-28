<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_attractive_style.css'/>"/>
</head>
<body>

<div class="wrap">
    <div class="content">
        <div class="tlt">
            <h1>나의 관심 상품</h1>
        </div>
        <h1>관심 상품은 최대 12개 까지 등록할 수 있으며, 30일 까지 보관됩니다.</h1>
        <button id="delete_all">전체 삭제</button><br>
        <h2>총 ${cnt}건</h2>
        <c:forEach items="${list}" var="list">
            <div class="product__list">
                <a><i class="fa-solid fa-x" name="product__attr--cancel" prd_cd="${list.prd_cd}"></i></a>
                <img src="${list.img_pth}" width="350px" height="250px">
                <div class="text">
                    <p>상품코드 ${list.prd_cd}</p>
                    <p>${list.prd_nm}</p>
                    <strong><fmt:formatNumber value="${list.prd_str_prc}" pattern="#,##0"/></strong><em> 원~</em>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script>
    $(document).ready(function (){

        let usr_id = "${sessionScope.userDto.usr_id}";

        if(usr_id == null || usr_id == ""){
            location.href = "<c:url value='/'/>";
        }

        $('#delete_all').on("click",function (){

            const result = confirm("관심 상품을 전부 삭제하시겠습니까?");

            if(!result){
                return
            }else{
                $.ajax({
                    url : "<c:url value='/product/attractive/deleteAll'/>",
                    type : "POST",
                    data : {"usr_id":"${sessionScope.userDto.usr_id}"},
                    success : function(data){
                        alert("관심 상품 전체 삭제가 성공하였습니다.");
                        window.location.reload();
                    },
                    error : function (){
                        alert("관심 상품 전체 삭제가 실패하였습니다.");
                    }
                });
            }


        });

        $('i[name=product__attr--cancel]').on("click",function (){
            let prd_cd = $(this).attr('prd_cd');

            let result = confirm('관심상품을 삭제하시겠습니까?');
            if(!result){
                return;
            }else{
                $.ajax({
                   url : "<c:url value='/product/attractive/delete'/>",
                   type: "POST",
                   data : {"usr_id":"${sessionScope.userDto.usr_id}","prd_cd":prd_cd},
                    success : function (data){
                       alert("관심 상품 삭제가 성공하였습니다.")
                        window.location.reload();
                    },
                    error : function (){
                       alert("관심 상품 삭제가 실패하였습니다.");
                    }
                });
            }
        });

    });
</script>
</body>
</html>
