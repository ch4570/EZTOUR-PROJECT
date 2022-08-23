<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_sch_img_read_style.css'/>">
</head>
<body>
<div class="wrap">
    <div class="content">
        <h1>관리자 페이지</h1>
        <div class="nav">
            <div class="menu">
                <ul>
                    <li class="prd_tlt">상품 등록 관리</li>
                    <a href="<c:url value='/product/insert'/>"><li class="menu_list">상품 등록</li></a>
                    <a href="<c:url value='/product/detail/insert'/>"><li class="menu_list">상품 상세 등록</li></a>
                    <a href="<c:url value='/product/insert/image'/>"><li class="menu_list">상품 이미지 등록</li></a>
                    <a href="<c:url value='/product/insert/schedule'/>"><li class="menu_list">상품 일정 등록</li></a>
                    <a href="<c:url value='/product/insert/price'/>"><li class="menu_list">상품 가격 등록</li></a>
                    <a href="<c:url value='/product/schedule/image/insert'/>"><li class="menu_list">상품 일정 사진 등록</li></a>
                </ul>
                <ul>
                    <li class="prd_tlt">상품 등록 현황 관리</li>
                    <a href="<c:url value='/product/management'/>"><li class="menu_list">상품 관리</li></a>
                    <a href="<c:url value='/product/management/detail'/>"><li class="menu_list">상품 상세 관리</li></a>
                    <a href="<c:url value='/product/management/image'/>"><li class="menu_list">상품 이미지 관리</li></a>
                    <a href="<c:url value='/product/management/schedule'/>"><li class="menu_list">상품 일정 관리</li></a>
                    <a href="<c:url value='/product/management/price'/>"><li class="menu_list">상품 가격 관리</li></a>
                    <a href="<c:url value='/product/management/schedule/image'/>"><li class="menu_list">상품 일정 사진 관리</li></a>
                </ul>
                <ul>
                    <li class="prd_tlt">상품 승인 관리</li>
                    <a href="<c:url value='/product/recognize'/>"><li class="menu_list">상품 승인</li></a>
                    <a href="<c:url value='/reserv/admin'/>"><li class="menu_list">예약 승인</li></a>
                </ul>
                <ul>
                    <li class="prd_tlt">고객 관리</li>
                    <li class="menu_list">고객 서비스 제안 관리</li>
                    <li class="menu_list">1:1 문의 관리</li>
                </ul>
            </div>
            <div class="board">
                <h1>관광지 사진 조회</h1>
                <div class="prd_sch_img_input_form">
                        <input type="hidden" name="frs_rgs_no" value="${sessionScope.usr_id}"/>
                    상품 번호&nbsp;<br><input type="text" value="${list[0].prd_cd}" readonly="readonly" class="input_prd"/><br>
                        <c:forEach var="list_img" items="${list}" varStatus="i">
                            <div class="preview_img">
                                <input type="hidden" name="sch_img_no" value="${list_img.sch_img_no}"/>
                                일정 번호&nbsp;<br><input type="text" value="${list_img.sch_no}" readonly="readonly" class="input_prd"/><br>
                                <img src="<c:url value='${list_img.prf_img_pth}'/>" id="product_img${i.count}" width="500px" height="300px"/><br>
                            </div>
                        </c:forEach>
                    </form>
                    <button id="delete_btn" class="button_click">일괄삭제</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
    const img_pth_arr = [];
    <c:forEach var="list_img" items="${list}" varStatus="i">
        img_pth_arr.push("${list_img.prf_img_pth}");
    </c:forEach>

    $(document).ready(function (){



        $('#delete_btn').on("click",function (){
            $.ajax({
                url : "<c:url value='/product/schedule/image/delete'/>",
                type : "POST",
                traditional: true,
                data : {img_pth: img_pth_arr, prd_cd:"${list[0].prd_cd}"},
                success : function(data) {
                    if(data=="success"){
                        alert("삭제가 성공했습니다.");
                        window.location.href = "<c:url value='/product/management/schedule/image'/>"
                    }else{
                        alert("삭제가 실패했습니다.");
                        window.location.href = "<c:url value="/product/schedule/image/read?prd_cd=${list[0].prd_cd}"/>"
                    }
                }
            });
        });
    });



</script>
</body>
</html>
