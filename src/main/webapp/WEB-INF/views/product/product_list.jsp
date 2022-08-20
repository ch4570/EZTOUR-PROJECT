<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/product/product_list.css">
</head>

<body>

    <div class="product-list__header">
        <h1>A</h1>
    </div>

    <div class="product-list__content">
        <div class="content--list__wrap">
        <div class="content--filter">
            <a href="#">추천상품순</a>
            <a href="#">낮은가격순</a>
            <a href="#">높은가격순</a>
        </div>
            <c:forEach var="item" items="${list}">
                <section class="content--wrap">
                    <div class="content--list">
                        <div class="content--list_img">
                            <img src="${item.img_pth}">
                            <div><i class="far fa-heart"></i></div>
                            <div><i class="fas fa-heart"></i></div>
                        </div>
                        <div class="content--list_info">
                            <div class="info-tit">
                                <span class="item-tit">${item.prd_nm}</span>
                                <span class="item-desc">${item.prd_dtl_desc}</span>
                                <div class="item-period">
                                    <i class="far fa-calendar"></i>
                                    <span>여행기간</span>
                                    <span>${item.trv_per}</span>
                                </div>
                                <div class="item-strdate">
                                    <i class="far fa-calendar"></i>
                                    <span>출발기간</span>
                                    <span><fmt:formatDate value="${item.dpr_str_date}" pattern="yyyy-MM-dd"/>  ~  <fmt:formatDate value="${item.dpr_fin_date}" pattern="yyyy-MM-dd" /> </span>
                                </div>
                                <span class="item-arl">${item.arl_nm}</span>
                            </div>
                            <div class="info-price">
                                <span class="item-dstn_cd">${item.dstn_cd}</span>
                                <div class="item-prd_str_prc">
                                    <span>${item.prd_str_prc}</span>
                                    <span>원~</span>
                                </div>
                                <div class="item-detailBtn__wrap">
                                    <button class="item-detailBtn" id="detailBtn" name="btnDetail" prd_cd="${item.prd_cd}">
                                        <em>자세히보기<i class="fas fa-chevron-down"></i></em>
                                        <em>닫기<i class="fas fa-chevron-up"></i></em>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="content--detail-items" prd_cd="${item.prd_cd}">
                        <div class="content--detail__item">
                            <ul class="detail__item--list"></ul>
                        </div>
                    </div>

                </section>
            </c:forEach>
        </div>
    </div>

<script>

    // 상품 리스트 보기
    $(document).ready(function () {
        // 자세히보기 버튼 클릭 시 클래스 'active' 추가
        $('button[name="btnDetail"]').on('click', function () {
            $(this).toggleClass('active');
            $(this).closest('.content--list').next().toggleClass('active');

            // 변수 설정
            const prd_cd = $(this).attr('prd_cd');

            // 리스트 출력
            if ($(this).hasClass('active')) {
                $.ajax({
                    type: 'GET',
                    url: "<c:url value='/product/detailList'/>",
                    data: {prd_cd:prd_cd},
                    headers: {"content-type": "application/json"},
                    success: function (result) {

                        let ul = document.querySelector(".detail__item--list");
                        let li = document.createElement("li");
                        let div = document.createElement("div");
                        let span = document.createElement("span");

                        result.forEach(function(product) {
                            span.innerText = product.prd_cd
                            li.appendChild(span);
                            ul.appendChild(li);
                        });
                    },
                    error: function () {
                        alert("에러 발생");
                    }
                });
            }
        })

        //상품 상세 보기
        $('button[name="detailItemBtn"]').on('click', function () {
            const prd_cd = $(this).attr('prd_cd');

        })


    });

</script>
</body>
</html>