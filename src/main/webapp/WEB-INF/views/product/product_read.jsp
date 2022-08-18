<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_read_style.css'/>">
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
                    <li class="menu_list">상품 일정 사진 관리</li>
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
                    <h1>상품 조회</h1>
                        <div class="prd_input_form">
                            상품코드 &nbsp;<br><input type="text"  class="input_prd" name="prd_cd" placeholder="상품코드" value="${trvPrdDto.prd_cd}" readonly="readonly"><br>
                            여행지코드 &nbsp;<br><input type="text" class="input_prd" name="dstn_cd" placeholder="여행지 코드" value="${trvPrdDto.dstn_cd}" readonly="readonly"><br>
                            테마상태 &nbsp;<br><input type="text" class="input_prd" name="cmn_cd_thm" placeholder="공통코드_테마상태" value="${trvPrdDto.cmn_cd_thm}" readonly="readonly"><br>
                            상품명 &nbsp;<br><input type="text" class="input_prd" name="prd_nm" placeholder="상품명" value="${trvPrdDto.prd_nm}" readonly="readonly"><br>
                            상품 상세 설명&nbsp;<br><textarea cols="24" rows="10" name="prd_dtl_desc" placeholder="상품 상세 설명" id="desc_area" readonly="readonly">${trvPrdDto.prd_dtl_desc}</textarea><br>
                            여행기간 &nbsp;<br><input type="text" class="input_prd" name="trv_per" placeholder="여행기간" value="${trvPrdDto.trv_per}" readonly="readonly"><br>
                            상품시작가격 &nbsp;<br><input type="text" class="input_prd" name="prd_str_prc" placeholder="상품시작가격" value="${trvPrdDto.prd_str_prc}" readonly="readonly"><br>
                            출발 시작일 &nbsp;<br><input type="text" class="input_prd" name="dpr_str_date" placeholder="출발 시작일" value="${trvPrdDto.dpr_str_date}" readonly="readonly"><br>
                            출발 마감일 &nbsp;<br><input type="text" class="input_prd" name="dpr_fin_date" placeholder="출발 마감" value="${trvPrdDto.dpr_fin_date}" readonly="readonly"><br>
                            <button class="btn" id="modify_btn">수정</button><br>
                            <button class="btn" id="delete_btn">삭제</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
    $(document).ready(function (){
        $('#modify_btn').on("click",function (){
            window.location.href = "<c:url value='/product/modify?prd_cd=${trvPrdDto.prd_cd}'/>";
        });

        $('#delete_btn').on("click",function (){
            var result = confirm("정말로 삭제 하시겠습니까?");
            if(result){
                $.ajax({
                    type: "POST",
                    url : "<c:url value='/product/delete'/>",
                    data: {prd_cd:"${trvPrdDto.prd_cd}"},
                    success : function (data){
                        if(data=="success"){
                            alert('삭제가 성공했습니다.');
                            window.location.href = "<c:url value='/product/management'/>"
                        }else{
                            alert('삭제가 실패했습니다.');
                            window.location.href = "<c:url value="/product/read?prd_cd=${trvPrdDto.prd_cd}"/>"
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
