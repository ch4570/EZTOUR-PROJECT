<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_attractive_style.css'/>"/>
</head>
<body>

<div class="wrap">
    <class="content">

        <div class="tlt">
            <h1>나의 관심 상품</h1>
            <p>관심 상품은 최대 12개 까지 등록할 수 있으며, 30일 까지 보관됩니다.</p>
        </div>


        <!-- 관심상품 없을 시 -->
        <c:if test="${cnt==0}">
        <div class="no-list">
            <div class="image-section">
                <img src="../../image/product/no-product-list.PNG" width="250px" height="200px">
            </div>
            <div class="no-list-info">나의 관심 상품에 담긴 상품이 없습니다.</div>
        </div>
         </c:if>

        <!-- 관심상품 info -->
        <div style="display: flex; justify-content: center; margin-top: 20px">
            <div style="display: flex; justify-content: space-between; align-items: center; text-align: center; width: 1100px;">
                <c:if test="${cnt!=0}">
                    <h2>총 ${cnt}건</h2>
                    <button id="delete_all"><i class="fa fa-trash-o" aria-hidden="true"></i> 전체 삭제</button>
                </c:if>
            </div>
        </div>

        <!-- 관심상품 리스트 -->
        <div style="display: flex; width: 1550px; justify-content: center">
            <div style="display: flex; width: 1080px; justify-content: flex-start; flex-wrap: wrap;">
                <c:forEach items="${list}" var="list">
                        <div class="item-attr">
                            <div class="product__list">
                                <div class="image-box" style="background-image: url(../..${list.img_pth})">
                                    <a style="position: absolute; top:10px; left:290px; z-index: 1000; color: #000; opacity:0.7;"><i class="fa fa-times-circle fa-2x" aria-hidden="true" name="product__attr--cancel" prd_cd="${list.prd_cd}"></i></a>
                                </div>
                                <a href="<c:url value='/product/recent/list?prd_cd=${list.prd_cd}'/>">
                                    <div class="text">
                                        <p style="font-size: 13px; font-weight: bold; color: #999999; padding-top: 10px">상품코드 ${list.prd_cd}</p>
                                        <p style="font-size: 16px; font-weight: bold; ">
                                            <c:choose>
                                                <c:when test="${fn:length(list.prd_nm) > 45}">
                                                    <c:out value="${fn:substring(list.prd_nm,0,44)}"/>....
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value="${list.prd_nm}"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                        <strong><fmt:formatNumber value="${list.prd_str_prc}" pattern="#,##0"/></strong><em> 원~</em>
                                    </div>
                                </a>
                            </div>
                        </div>
                </c:forEach>
            </div>
        </div>

    </div>
</div>
<script>
    $(document).ready(function (){

        let usr_id = "${sessionScope.userDto.usr_id}";

        if(usr_id == null || usr_id == ""){
            location.href = "<c:url value='/'/>";
        }

        $('#delete_all').on("click",function (){

            const result = confirm("관심 상품을 전부 삭제하시겠습니까?");

            if(!result){
                return
            }else{
                $.ajax({
                    url : "<c:url value='/product/attractive/deleteAll'/>",
                    type : "POST",
                    data : {"usr_id":"${sessionScope.userDto.usr_id}"},
                    success : function(data){
                        alert("관심 상품 전체 삭제가 성공하였습니다.");
                        window.location.reload();
                    },
                    error : function (){
                        alert("관심 상품 전체 삭제가 실패하였습니다.");
                    }
                });
            }


        });

        $('i[name=product__attr--cancel]').on("click",function (){
            let prd_cd = $(this).attr('prd_cd');

            let result = confirm('관심상품을 삭제하시겠습니까?');
            if(!result){
                return;
            }else{
                $.ajax({
                   url : "<c:url value='/product/attractive/delete'/>",
                   type: "POST",
                   data : {"usr_id":"${sessionScope.userDto.usr_id}","prd_cd":prd_cd},
                    success : function (data){
                       alert("관심 상품 삭제가 성공하였습니다.")
                        window.location.reload();
                    },
                    error : function (){
                       alert("관심 상품 삭제가 실패하였습니다.");
                    }
                });
            }
        });

    });
</script>
</body>
</html>
