<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_detail_read_style.css'/>">
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
                <h1>상품 상세 관리</h1>
                <div class="prd_detail_input_form">
                        상품상세코드&nbsp;<br><input type="text" name="prd_dtl_cd" placeholder="상품상세코드" value="${prd_dtl.prd_cd}" class="input_prd" readonly="readonly"/><br>
                        상품코드&nbsp;<br><input type="text" name="prd_cd" value="${prd_dtl.prd_cd}" placeholder="상품코드" class="input_prd" readonly="readonly"/><br>
                        상품이름&nbsp;<br><input type="text" name="prd_nm" placeholder="상품이름" class="input_prd" value="${prd_dtl.prd_nm}" readonly="readonly"/><br>
                        상품시작가격&nbsp;<br><input type="text" name="prd_str_prc"  placeholder="상품시작가격" class="input_prd" value="${prd_dtl.prd_str_prc}" readonly="readonly"/><br>
                        항공사명&nbsp;<br><input type="text" name="arl_nm" placeholder="항공사명" class="input_prd" value="${prd_dtl.arl_nm}" readonly="readonly"/><br>
                        최소출발인원&nbsp;<br><input type="text" name="min_stt_cnt" placeholder="최소출발인원" class="input_prd" value="${prd_dtl.min_stt_cnt}" readonly="readonly"/><br>
                        최대출발인원&nbsp;<br><input type="text" name="max_stt_cnt" placeholder="최대출발인원" class="input_prd" value="${prd_dtl.max_stt_cnt}" readonly="readonly"/><br>
                        <fmt:parseDate var="dpr_date" value="${prd_dtl.dpr_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        <fmt:parseDate var="fin_date" value="${prd_dtl.fin_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        출발일&nbsp;<br><input type="datetime-local" name="dpr_date" placeholder="출발일" class="input_prd" value="<fmt:formatDate value="${dpr_date}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly"/><br>
                        도착일&nbsp;<br><input type="datetime-local" name="fin_date" placeholder="도착일" class="input_prd" value="<fmt:formatDate value="${fin_date}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="readonly"/><br>
                                <button type="button" id="modify_btn" class="btn">수정</button><br>
                                <button type="button" id="delete_btn" class="btn">삭제</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
    $(document).ready(function (){

        $('#modify_btn').on("click",function (){
            window.location.href = "<c:url value='/product/detail/modify?prd_dtl_cd=${prd_dtl.prd_dtl_cd}'/>";
        });

        $('#delete_btn').on("click",function (){
            var result = confirm("정말로 삭제 하시겠습니까?");
            if(result){
                $.ajax({
                    type: "POST",
                    url : "<c:url value='/product/detail/delete'/>",
                    data: {prd_dtl_cd:"${prd_dtl.prd_dtl_cd}"},
                    success : function (data){
                        if(data=="success"){
                            alert('삭제가 성공했습니다.');
                            window.location.href = "<c:url value='/product/management/detail'/>"
                        }else{
                            alert('삭제가 실패했습니다.');
                            window.location.href = "<c:url value="/product/detail/read?prd_dtl_cd=${prd_dtl.prd_dtl_cd}"/>"
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
