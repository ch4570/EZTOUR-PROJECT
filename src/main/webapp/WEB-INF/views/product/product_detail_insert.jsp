<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_detail_insert_style.css'/>">
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
                <h1>상품 상세 등록</h1>
                <div class="prd_detail_input_form">
                    <form:form action="/product/detail/insert" method="post" modelAttribute="trvPrdDtlWriteDto">
                        <input type="hidden" name="frs_rgs_no" value="${sessionScope.usr_id}"/>
                        <form:label path="prd_dtl_cd">상품상세코드</form:label><br>
                        <form:input path="prd_dtl_cd" placeholder="상품상세코드" value="${param.prd_cd}" cssClass="input_prd"/><br>
                        <form:errors path="prd_dtl_cd" cssClass="error_msg"/><br><br>

                        <form:label path="prd_cd">상품코드</form:label><br>
                        <form:input path="prd_cd" placeholder="상품코드" value="${param.prd_cd}" cssClass="input_prd"/><br>
                        <form:errors path="prd_cd" cssClass="error_msg"/><br><br>

                        <form:label path="prd_nm">상품이름</form:label><br>
                        <form:input path="prd_nm" placeholder="상품이름" cssClass="input_prd"/><br>
                        <form:errors path="prd_nm" cssClass="error_msg"/><br><br>

                        <form:label path="prd_str_prc">상품시작가격</form:label><br>
                        <form:input path="prd_str_prc" placeholder="상품시작가격" cssClass="input_prd"/><br>
                        <form:errors path="prd_str_prc" cssClass="error_msg"/><br><br>

                        <form:label path="arl_nm">항공사명</form:label><br>
                        <form:input path="arl_nm" placeholder="항공사명" cssClass="input_prd"/><br>
                        <form:errors path="arl_nm" cssClass="error_msg"/><br><br>

                        <form:label path="min_stt_cnt">최소출발인원</form:label><br>
                        <form:input path="min_stt_cnt" placeholder="최소출발인원" cssClass="input_prd"/><br>
                        <form:errors path="min_stt_cnt" cssClass="error_msg"/><br><br>

                        <form:label path="max_stt_cnt">최대출발인원</form:label><br>
                        <form:input path="max_stt_cnt" placeholder="최대출발인원" class="input_prd"/><br>
                        <form:errors path="max_stt_cnt" cssClass="error_msg"/><br><br>

                        <form:label path="dom_dpr_date">국내 출발일</form:label><br>
                        <input type="datetime-local" name="dom_dpr_date" placeholder="국내 출발일" class="input_prd"/><br>
                        <form:errors path="dom_dpr_date" cssClass="error_msg"/><br><br>

                        <form:label path="loc_fin_date">현지 도착일</form:label><br>
                        <input type="datetime-local" name="loc_fin_date" placeholder="현지 도착일" class="input_prd"/><br>
                        <form:errors path="loc_fin_date" cssClass="error_msg"/><br><br>

                        <form:label path="loc_dpr_date">현지 출발일</form:label><br>
                        <input type="datetime-local" name="loc_dpr_date" placeholder="현지 출발일" class="input_prd"/><br>
                        <form:errors path="loc_dpr_date" cssClass="error_msg"/><br><br>

                        <form:label path="dom_fin_date">국내 도착일</form:label><br>
                        <input type="datetime-local" name="dom_fin_date" placeholder="국내 도착일" class="input_prd"/><br>
                        <form:errors path="dom_fin_date" cssClass="error_msg"/><br><br>

                    </form:form>
                    <button id="submit_btn" class="input_prd">상품 상세 등록</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function checkForm(){
        let prd_dtl_cd = $('input[name=prd_dtl_cd]').val();
        let prd_cd = $('input[name=prd_cd]').val();
        let prd_nm = $('input[name=prd_nm]').val();
        let arl_nm = $('input[name=arl_nm]').val();
        let min_stt_cnt = $('input[name=min_stt_cnt]').val();
        let max_stt_cnt = $('input[name=max_stt_cnt]').val();
        let dom_dpr_date = $('input[name=dom_dpr_date]').val();
        let dom_fin_date = $('input[name=dom_fin_date]').val();
        let loc_fin_date = $('input[name=loc_fin_date]').val();
        let loc_dpr_date = $('input[name=loc_dpr_date]').val();

        if(prd_cd == null || prd_cd == ""){
            alert('상품코드는 필수 입력 항목입니다.');
            return;
        }

        if(prd_nm == null || prd_nm == ""){
            alert('상품 이름은 필수 입력 항목입니다.');
            return;
        }

        if(prd_dtl_cd == null || prd_dtl_cd == ""){
            alert('상품 상세 코드는 필수 입력 항목입니다.');
            return;
        }

        if(arl_nm == null || arl_nm == ""){
            alert('항공사정보는 필수 입력 항목입니다.');
            return;
        }

        if(min_stt_cnt == null || min_stt_cnt == ""){
            alert('최소 출발인원은 필수 입력 항목입니다.');
            return;
        }

        if(max_stt_cnt == null || max_stt_cnt == ""){
            alert('최대 출발인원은 필수 입력 항목입니다.');
            return;
        }

        if(dom_dpr_date == null || dom_dpr_date == ""){
            alert('국내 출발일자는 필수 입력 항목입니다.');
            return;
        }

        if(dom_fin_date == null || dom_fin_date == ""){
            alert('국내 도착일자는 필수 입력 항목입니다.');
            return;
        }

        if(loc_dpr_date == null || loc_dpr_date == ""){
            alert('현지 출발일자는 필수 입력 항목입니다.');
            return;
        }

        if(loc_fin_date == null || loc_fin_date == ""){
            alert('현지 도착일자는 필수 입력 항목입니다.');
            return;
        }


        $('form').submit();

    }

    $(document).ready(function (){
       $('#submit_btn').on("click",function (){
          checkForm();
       });
    });
</script>
</body>
</html>
