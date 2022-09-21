<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="/css/home/search.css">
    <title>Title</title>
</head>
<body>

<div class="search-result">

    <div class="result-tit">
        <span>'${keyword}' 검색결과 (${resultCnt}건)</span>
    </div>

    <div class="result-item">
        <c:forEach var="Trv_prd_dto" items="${list}">
            <div class="result-item-link">
                <a href="/product/recent/list?prd_cd=${Trv_prd_dto.prd_cd}">
                    <div class="item-thumb">
                        <img src="${Trv_prd_dto.img_pth}" alt="상품 이미지">
                    </div>
                    <div class="result-item__info">
                        <span>${Trv_prd_dto.prd_nm}</span>
                        <span><fmt:formatNumber value='${Trv_prd_dto.prd_str_prc}' pattern="#,##0"/></span>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
    <div class="more-btn">
        <button class="more-btn__btn">더보기</button>
    </div>
</div>

<script>

    $(function() {
        $(".result-item-link").slice(0, 8).show();
        if($(".result-item-link:hidden").length == 0){
            $(".more-btn__btn").addClass('none-active');
        }
        $(".more-btn__btn").click(function(e) {
            e.preventDefault();
            $(".result-item-link:hidden").slice(0, 8).show();
            if($(".result-item-link:hidden").length == 0){
                $(".more-btn__btn").addClass('none-active');
            }
        });
    });
</script>

</body>
</html>
