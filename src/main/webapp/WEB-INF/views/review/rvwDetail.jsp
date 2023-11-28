<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="<c:url value='/css/rvw/rvwDetail.css?after'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>

    <style>
        .tit{
            width: 200px;
            height: 50px;
            margin: 0 auto;
            text-align: center;
            padding-top: 20px;
            font-size: 45px;
            font-weight: bold;
            color: #1c1c1c;
        }
    </style>
</head>
<body>
<div class="wrap">
    <div class="content" style="padding-top: 80px; width: 900px;">
        <div class="tit">
            <h1>Review</h1>
            <p style="font-size: 13px; padding-top: 20px; color: #666666">이지투어 고객님들의 소중한 후기</p>
        </div>
        <div class="boarder-wrap" style="margin-top: 120px">
            <div style="display: flex; align-items: center">
                <p class="ttl" name="rvw_ttl" style="font-size: 30px; font-weight: bold;"><c:out value="${rvwDto.rvw_ttl}"/></p>
                <span class="reviewLikeViewCnt-lk_cnt-rvw_cont" name="lk_cnt" style="margin-left: 20px"><i class="fa fa-eye" aria-hidden="true"></i> ${rvwDto.rvw_vcnt}</span>
                <span class="reviewLikeViewCnt-lk_cnt-rvw_cont" name="lk_cnt" style="margin-left: 20px"><i class="fas fa-heart" aria-hidden="true"></i> ${rvwDto.lk_cnt}</span>
            </div>
            <div style="margin-top: 20px; display: flex; justify-content: end; font-weight: bold; color: #666666 " >
                <p class="right">
                    <span class="writer" name="wrt_nm">작성자 | ${rvwDto.wrt_nm}</span>
                    <span class="date" name="rvw_reg_date">등록일 | <fmt:formatDate value="${rvwDto.rvw_reg_date}" pattern="yyyy-MM-dd"/></span>
                </p>
            </div>
        </div>
        <hr style="margin-top: 0px;">

            <!-- 관련 상품-->
        <div class="prdInfo" style="display: flex; justify-content: space-between;">
            <div class="image-box" style="background-image: url(../..${rvwDto.img_pth})">
            </div>
            <div class="prdInfo-content" style="margin-right: 20px">
                <a class="prdInfo-content-title" href="/product/detail?prd_dtl_cd=${rvwDto.prd_dtl_cd}">${rvwDto.prd_nm}</a>
                <a class="btn sz-l st-blue view" href="/product/detail?prd_dtl_cd=${rvwDto.prd_dtl_cd}">상품보기</a>
            </div>
        </div>

        <!-- 글 내용-->
        <div class="detailInfo">
            <div class="detailInfo-content" name="rvw_cont">
                <div class="detailInfo-content-child" style="font-size: 18px;"><c:out value="${rvwDto.rvw_cont}" escapeXml="false"/></div>
            </div>
        </div>
        <hr style="margin-top: 0px;">
        <!-- 삭제, 수정, 목록 버튼-->
        <form action="" id="form" method="post">
            <input class="value-move" type="hidden" name="rvw_no" value="${rvwDto.rvw_no}" readonly="readonly">
            <input class="value-move" type="hidden" name="rvw_ttl" value="${rvwDto.rvw_ttl}" readonly="readonly">
            <input class="value-move" type="hidden" name="lk_cnt" value="${rvwDto.lk_cnt}" readonly="readonly">
            <input class="value-move" type="hidden" name="wrt_nm" value="${rvwDto.wrt_nm}" readonly="readonly">
            <input class="value-move" type="hidden" name="rvw_reg_date" value="${rvwDto.rvw_reg_date}" readonly="readonly">
            <input class="value-move" type="hidden" name="rvw_vcnt" value="${rvwDto.rvw_vcnt}" readonly="readonly">
            <input class="value-move" type="hidden" name="rvw_cont" value="${rvwDto.rvw_cont}" readonly="readonly">
            <input class="value-move" type="hidden" name="img_pth" value="${rvwDto.img_pth}" readonly="readonly">
            <input class="value-move" type="hidden" name="prd_nm" value="${rvwDto.prd_nm}" readonly="readonly">
            <input class="value-move" type="hidden" name="rvw_lk_yn" value="${rvwLkAdmDto.rvw_lk_yn}" readonly="readonly">
            <div class="boradBtns">
                <!--좋아요 버튼-->
                <c:choose>
                    <c:when test="${rvwLkAdmDto.rvw_lk_yn == 1}">
                        <!-- full heart-->
                        <div class="view_btn_set">
                            <i class="fas fa-heart" name="fill-heart" id="heart-fill"></i>
                            <a>좋아요</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <!-- non full heart-->
                        <div class="view_btn_set">
                            <i class="far fa-heart" name="non-fill-heart" id="heart-empty"></i>
                            <a>좋아요</a>
                        </div>
                    </c:otherwise>
                </c:choose>
                <button type="button" class="btn sz-inp st-lblue btn_summit" id="listBtn">목록</button>
                <c:if test="${check.equals('me')}">
                    <button type="button" class="btn sz-inp st-lblue btn_summit" id="modifyBtn">수정</button>
                    <button type="button" class="btn sz-inp st-lblue btn_summit" id="removeBtn">삭제</button>
                </c:if>
            </div>
        </form>
    </div>
</div>
<script>
    $(document).ready(function () {

        $('#listBtn').on("click", function(){
            location.href = "<c:url value='/review/list${searchCondition.queryString}'/>";
        });

        $('#removeBtn').on("click", function(){
            if(!confirm("정말로 삭제하시겠습니까?")) return;
            let form = $('#form');
            form.attr("action", "<c:url value='/review/remove${searchCondition.queryString}'/>");
            form.attr("method", "post");
            form.submit();
        });
        $('#modifyBtn').on("click", function(){
            location.href = "<c:url value='/review/modify${searchCondition.queryString}&rvw_no=${rvwDto.rvw_no}'/>";
        });

        // 좋아요 버튼을 클릭 시 실행되는 코드
        var heartval = "${rvwLkAdmDto.rvw_lk_yn}"

        $('i[name=fill-heart]').on("click",function (){
            $.ajax({
                url : '/heart',
                type : 'POST',
                data : {'rvw_no': "${rvwDto.rvw_no}"},
                success : function (data){
                    if(data=="HeartDown") {
                        $(".fas fa-heart").attr("class", "far fa-heart");
                        location.reload()
                    }
                    else {
                        return;
                    }
                }

            });
        });
        $('i[name=non-fill-heart]').on("click",function (){
            if(heartval == ""){
                alert("로그인을 해야 후기에 좋아요 버튼를 누를 수 있습니다.");
                var confirmUser = confirm("로그인 하시겠습니까?");
                if(confirmUser){
                    location.href = "<c:url value='/user/login'/>"
                }else{
                    return;
                }
            }
            $.ajax({
                url : '/heart',
                type : 'POST',
                data : {'rvw_no': "${rvwDto.rvw_no}"},
                success : function (data){
                    if(data=="HeartUp") {
                        $(".far fa-heart").attr("class", "fas fa-heart");
                        location.reload()
                    }
                    else {
                        return;
                    }
                }
            });
        });
    });
</script>
</body>
</html>