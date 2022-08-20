<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_price_read_style.css'/>">
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
                    <li class="menu_list">상품 승인</li>
                    <a href="<c:url value='/reserv/admin'/>"><li class="menu_list">예약 승인</li></a>
                </ul>
                <ul>
                    <li class="prd_tlt">고객 관리</li>
                    <li class="menu_list">고객 서비스 제안 관리</li>
                    <li class="menu_list">1:1 문의 관리</li>
                </ul>
            </div>
            <div class="board">
                <h1>상품 가격 조회</h1>
                <div class="prd_price_input_form">
                        상품상세코드&nbsp;<br><input type="text" value="${list.prd_dtl_cd}" name="prd_dtl_cd" placeholder="상품상세코드" readonly="readonly" class="input_prd"/><br>
                        상품코드&nbsp;<br><input type="text" value="${list.prd_cd}" name="prd_cd" placeholder="상품코드" readonly="readonly" class="input_prd"/><br>
                        성인요금&nbsp;<br><input type="text" value="${list.adt_prc}" name="adt_prc" placeholder="성인요금" class="input_prd"/><br>
                        아동요금&nbsp;<br><input type="text" value="${list.chd_prc}" name="chd_prc" placeholder="아동요금" class="input_prd"/><br>
                        유아요금&nbsp;<br><input type="text" value="${list.bb_prc}" name="bb_prc" placeholder="유아요금" class="input_prd"/><br>
                        <button id="modify_btn" class="button_click">수정</button><br>
                        <button id="delete_btn" class="button_click">삭제</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
    $(document).ready(function (){

        $('#modify_btn').on("click",function (){
            window.location.href = "<c:url value='/product/price/modify?prd_prc_no=${list.prd_prc_no}'/>";
        });

        $('#delete_btn').on("click",function (){
            var result = confirm("정말로 삭제 하시겠습니까?");
            if(result){
                $.ajax({
                    type: "POST",
                    url : "<c:url value='/product/price/delete'/>",
                    data: {prd_prc_no:"${list.prd_prc_no}"},
                    success : function (data){
                        if(data=="success"){
                            alert('삭제가 성공했습니다.');
                            window.location.href = "<c:url value='/product/management/price'/>"
                        }else{
                            alert('삭제가 실패했습니다.');
                            window.location.href = "<c:url value="/product/price/read?prd_prc_no=${list.prd_prc_no}"/>"
                        }
                    }
                });
            }else{
                return;
            }
        });

    });
</script>
</body>
</html>
