<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/customer/customer_main.css">
</head>
<body>

    <div class="customer-title">
        <h2>고객센터</h2>
    </div>
    <form class="customer-search" action="/customer/faqSearch" >
        <span class="search__title">무엇을 도와드릴까요?</span>
        <div class="search__form">
            <input type="search" name="keyword" placeholder="검색어를 입력하세요">
            <button><i class="fa-solid fa-magnifying-glass"></i></button>
        </div>
    </form>

    <div class="customer-menu">
        <a href="#" class="menu__link">
            <span><i class="fa-solid fa-comments"></i></span>
<%--            1:1 문의로 넘어가는 주소 입력해줘야함.--%>
            <span>1:1문의</span>
        </a>
        <a href="#" class="menu__link">
            <span><i class="fa-solid fa-envelope-open-text"></i></span>
            <span>고객 서비스 개선 제안</span>
        </a>
        <a href="#" class="menu__link">
            <span><i class="fa-solid fa-file-circle-check"></i></span>
            <span>고객만족도 조사</span>
        </a>
        <a href="#" class="menu__link">
            <span><i class="fa-solid fa-credit-card"></i></span>
            <span>결제방법 안내</span>
        </a>
        <a href="#" class="menu__link">
            <span><i class="fa-solid fa-coins"></i></span>
            <span>포인트 안내</span>
        </a>
    </div>

    <div class="customer-faq">
        <div class="faq__title">
            <span class="faq__title--title">자주하는 질문</span>
            <a class="faq__more" href="/customer/faqList">
                <span>더보기</span>
                <i class="fa-solid fa-arrow-right"></i>
            </a>
        </div>
        <div class="faq__list">
        <c:forEach begin="1" end="5" var="faqDto" items="${list}">
            <div class="faq__q-area">
                <div class="faq__q-area--tit">${faqDto.qna_cd}</div>
                <div class="faq__q-area--txt">${faqDto.faq_qna_cont}</div>
                <span><i class="fa-solid fa-angle-down"></i></span>
            </div>
            <div class="faq__a-area">
                <div class="faq__a-area--txt">
                    <p>${faqDto.faq_ans_cont}</p>
                </div>
            </div>
        </c:forEach>
        </div>
    </div>
    <a class="reg-faq" href="/"></a>

</body>
</html>
