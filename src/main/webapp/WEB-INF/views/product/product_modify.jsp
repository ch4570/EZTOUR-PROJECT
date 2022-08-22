<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_modify_style.css'/>">
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
                    <h1>상품 수정</h1>
                        <div class="prd_input_form">
                            <form:form action="/product/modify" method="post" modelAttribute="trvPrdWriteDto">
                                <input type="hidden" name="prd_cd_mod" value="${trvPrdDto.prd_cd}"/>

                                <form:label path="prd_cd">상품코드</form:label><br>
                                <form:input path="prd_cd" placeholder="상품코드" value="${trvPrdDto.prd_cd}" cssClass="input_prd"/><br>
                                <form:errors path="prd_cd" cssClass="error_msg"/><br><br>

                                <form:label path="cntn_cd">대륙코드</form:label><br>
                                <select name="cntn_cd" class="input_prd" id="cntn_select">
                                    <option value="" selected>대륙코드</option>
                                    <option value="A">A</option>
                                    <option value="B">B</option>
                                    <option value="C">C</option>
                                    <option value="D">D</option>
                                    <option value="E">E</option>
                                </select><br>
                                <form:errors path="cntn_cd" cssClass="error_msg"/><br><br>

                                <form:label path="nt_cd">국가코드</form:label><br>
                                <select name="nt_cd" class="input_prd" id="nt_cd_select">

                                </select><br>

                                <form:label path="dstn_cd">여행지코드</form:label><br>
                                <form:input path="dstn_cd" placeholder="여행지코드" value="${trvPrdDto.dstn_cd}" cssClass="input_prd"/><br>
                                <form:errors path="dstn_cd" cssClass="error_msg"/><br><br>

                                <form:label path="cmn_cd_thm">테마코드</form:label><br>
                                <form:input path="cmn_cd_thm" placeholder="테마코드" value="${trvPrdDto.cmn_cd_thm}" cssClass="input_prd"/><br>
                                <form:errors path="cmn_cd_thm" cssClass="error_msg"/><br><br>

                                <form:label path="prd_nm">상품명</form:label><br>
                                <form:input path="prd_nm" placeholder="상품명" value="${trvPrdDto.prd_nm}" cssClass="input_prd"/><br>
                                <form:errors path="prd_nm" cssClass="error_msg"/><br><br>

                                <form:label path="prd_dtl_desc">상품 상세 설명</form:label><br>
                                <textarea name="prd_dtl_desc" placeholder="상품 상세 설명" id="desc_area">${trvPrdDto.prd_dtl_desc}</textarea><br>
                                <form:errors path="prd_dtl_desc" cssClass="error_msg"/><br><br>


                                <form:label path="trv_per">여행기간</form:label><br>
                                <form:input path="trv_per" placeholder="여행기간" value="${trvPrdDto.trv_per}" cssClass="input_prd"/><br>
                                <form:errors path="trv_per" cssClass="error_msg"/><br><br>

                                <form:label path="prd_str_prc">상품시작가격</form:label><br>
                                <form:input path="prd_str_prc"  placeholder="상품시작가격" value="${trvPrdDto.prd_str_prc}" cssClass="input_prd"/><br>
                                <form:errors path="prd_str_prc" cssClass="error_msg"/><br><br>

                                <fmt:parseDate value="${trvPrdDto.dpr_str_date}" var="dpr_str_date" pattern="yyyy-MM-dd"/>
                                <fmt:parseDate value="${trvPrdDto.dpr_fin_date}" var="dpr_fin_date" pattern="yyyy-MM-dd"/>

                                <form:label path="dpr_str_date">출발 시작일</form:label><br>
                                <input type="date" class="input_prd" name="dpr_str_date" placeholder="출발 시작일" value="<fmt:formatDate value='${dpr_str_date}' pattern='yyyy-MM-dd'/>"><br>
                                <form:errors path="dpr_str_date" cssClass="error_msg"/><br><br>

                                <form:label path="dpr_fin_date">출발 마감일</form:label><br>
                                <input type="date" class="input_prd" name="dpr_fin_date" placeholder="출발 마감" value="<fmt:formatDate value='${dpr_fin_date}' pattern='yyyy-MM-dd'/>"><br>
                                <form:errors path="dpr_fin_date" cssClass="error_msg"/><br><br>
                               <input type="submit" value="상품 수정" id="submit_btn">
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
<script>
    $(document).ready(function (){
        $('#cntn_select').change(function (){
            let value = $(this).val();
            $.ajax({
                type: "POST",
                url : "<c:url value='/product/option'/>",
                data : {option:value},
                success : function (data){
                    $('#nt_cd_select').empty();
                    $(data).each(function (){
                        $('#nt_cd_select').append("<option value="+this.nt_cd+">"+this.nt_cd_nm+"</option>");
                    });
                },
                error : function (){
                    alert("국가코드를 선택해주세요");
                }

            });
        });
    });
</script>
    </body>
</html>
