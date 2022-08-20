<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_sch_img_insert_style.css'/>">
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
                <h1>관광지 사진 등록</h1>
                <div class="prd_sch_img_input_form">
                    <form action="<c:url value='/product/schedule/image/insert'/>" method="post" enctype="multipart/form-data" id="image_upload">
                        <input type="hidden" name="frs_rgs_no" value="${sessionScope.usr_id}"/>
                        일정 번호 입력&nbsp;<br><input type="text" name="sch_no" placeholder="일정 번호 입력" class="input_prd"><br>
                        상품 번호 입력&nbsp;<br><input type="text" name="prd_cd" placeholder="상품 번호 입력" class="input_prd"><br>
                        <input type="file" name="prd_img" id="prd_img1" class="input_prd"><br>
                        <div class="preview-img">
                            <img src="" id="product_img1" width="500px" height="300px">
                        </div>
                        <input type="file" name="prd_img" id="prd_img2" class="input_prd"><br>
                        <div class="preview-img">
                            <img src="" id="product_img2" width="500px" height="300px">
                        </div>
                        <input type="file" name="prd_img" id="prd_img3" class="input_prd"><br>
                        <div class="preview-img">
                            <img src="" id="product_img3" width="500px" height="300px">
                        </div>
                    </form>
                    <button id="submit_btn">등록</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
    function setImageFromFile(input, expression) {
        if (input.files && input.files[0]) {
            const reader = new FileReader();
            reader.onload = function (e) {
                $(expression).attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $(document).ready(function (){

        $('#prd_img1').change(function(){
            setImageFromFile(this, '#product_img1');
        });

        $('#prd_img2').change(function(){
            setImageFromFile(this, '#product_img2');
        });

        $('#prd_img3').change(function(){
            setImageFromFile(this, '#product_img3');
        });

        $('#submit_btn').on("click",function (){
            const formData = new FormData($("#image_upload")[0]);
            formData.append("prd_img",$("#prd_img1").val());
            formData.append("prd_img",$("#prd_img2").val());
            formData.append("prd_img",$("#prd_img3").val());
            $.ajax({
                type: "POST",
                url : "<c:url value='/product/schedule/image/insert'/>",
                data : formData,
                contentType: false,
                processData: false,
                cache : false,
                success: function(data){
                    if(data=="success"){
                        alert("이미지가 등록되었습니다.");
                        window.location.reload();
                    }else{
                        alert("이미지 등록을 실패했습니다.");
                        window.location.reload();
                    }
                }
            });

        });
    });
</script>
</body>
</html>
