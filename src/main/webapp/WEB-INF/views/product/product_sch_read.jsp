<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_sch_read_style.css'/>">
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
                </ul>
                <ul>
                    <li class="prd_tlt">고객 관리</li>
                    <li class="menu_list">고객 서비스 제안 관리</li>
                    <li class="menu_list">1:1 문의 관리</li>
                </ul>
            </div>
            <div class="board">
                <h1>상품 일정 등록</h1>
                <div class="prd_sch_input_form">
                        상품코드&nbsp;<br><input type="text" value="${list.prd_cd}" class="input_prd" readonly="readonly"/><br>
                        여행일차&nbsp;<br><input type="text"  value="${list.trv_date}"  class="input_prd"readonly="readonly"/><br>
                        일정순번&nbsp;<br><input type="text"  value="${list.sch_ord}" class="input_prd" readonly="readonly"><br>
                        관광지이름&nbsp;<br><input type="text" value="${empty list.st_nm ? '정보 없음' : list.st_nm}" class="input_prd" readonly="readonly"/><br>
                        관광지 간략설명&nbsp;<br><textarea id="sit_sh_desc" cols="22" rows="5" readonly="readonly">${empty list.sit_sh_desc ? '정보 없음' : list.sit_sh_desc}</textarea><br>
                        관광지 상세설명&nbsp;<br><textarea id="sit_lo_desc" cols="22" rows="5"readonly="readonly">${empty list.sit_lo_desc ? '정보 없음' : list.sit_lo_desc}</textarea><br>
                        호텔정보&nbsp;<br><input type="text" value="${empty list.ht_inf ? '정보 없음' : list.ht_inf}" class="input_prd" readonly="readonly"/><br>
                        아침&nbsp;<br><input type="text" value="${empty list.brk ? '정보 없음' : list.brk}" class="input_prd" readonly="readonly"/><br>
                        점심&nbsp;<br><input type="text" value="${empty list.luh ? '정보 없음' : list.luh}" class="input_prd" readonly="readonly"/><br>
                        저녁&nbsp;<br><input type="text" value="${empty list.din ? '정보 없음' : list.din}" class="input_prd" readonly="readonly"/><br>
                        이동소요기간&nbsp;<br><input type="text" value="${empty list.dstnc_tm ? '정보 없음' : list.dstnc_tm}" class="input_prd" readonly="readonly"/><br>
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
            window.location.href = "<c:url value='/product/schedule/modify?sch_no=${list.sch_no}'/>";
        });

        $('#delete_btn').on("click",function (){
            var result = confirm("정말로 삭제 하시겠습니까?");
            if(result){
                $.ajax({
                    type: "POST",
                    url : "<c:url value='/product/schedule/delete'/>",
                    data: {sch_no:"${list.sch_no}"},
                    success : function (data){
                        if(data=="success"){
                            alert('삭제가 성공했습니다.');
                            window.location.href = "<c:url value='/product/management/schedule'/>"
                        }else{
                            alert('삭제가 실패했습니다.');
                            window.location.href = "<c:url value="/product/schedule/read?sch_no=${list.sch_no}"/>"
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
