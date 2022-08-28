<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/rvw/rvwDetail.css?after'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<div class="wrap">
    <div class="content">
        <div class="review-wrap">
            <div class="review-title">여행후기</div>
            <div class="review-title-desc">
                여행을 다녀온 고객분들의
                <br>
                솔직한 여행 이야기
            </div>
        </div>
        <div class="boarder-wrap">
            <h2>
                <p class="tit" name="rvw_ttl"><c:out value="${rvwDto.rvw_ttl}"/></p>
                <span class="reviewLikeViewCnt-lk_cnt-rvw_cont" name="lk_cnt">좋아요 ${rvwDto.lk_cnt} | 조회수 ${rvwDto.rvw_vcnt}</span>
                <p class="right">
                    <span class="date" name="rvw_reg_date">등록일 | <fmt:formatDate value="${rvwDto.rvw_reg_date}" pattern="yyyy-MM-dd hh:mm:ss"/></span>
                    <span class="writer" name="wrt_nm">작성자 | ${rvwDto.wrt_nm}</span>
                </p>
            </h2>
            <!-- 관련 상품-->
            <div class="prdInfo">
                <a class="prdImg" href="#">
                    <img src="/image/review/IMG_0966.JPG"  width="300" height="180">
                    <!--img src="${rvwDto.img_pth}" alt="상품사진"-->
                </a>
                <div class="prdInfo-content">
                    <a class="prdInfo-content-title" href="#">${rvwDto.prd_nm}</a>
                    <a class="btn sz-l st-blue view" href="#">상품보기</a>
                </div>
            </div>
            <!-- 글 내용-->
            <div class="detailInfo">
                <div class="detailInfo-content" name="rvw_cont">
                    <div class="detailInfo-content-child"><c:out value="${rvwDto.rvw_cont}"/></div>
                </div>
            </div>
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
                    <button type="button" class="btn sz-inp st-lblue btn_summit" id="listBtn">목록</button>
                    <c:if test="${check.equals('me')}">
                        <button type="button" class="btn sz-inp st-lblue btn_summit" id="modifyBtn">수정</button>
                        <button type="button" class="btn sz-inp st-lblue btn_summit" id="removeBtn">삭제</button>
                    </c:if>
                </div>
            </form>
            <!--좋아요 버튼-->
            <div>
                <a class="text-dark heart" style="text-decoration-line: none;">
                    <img id="heart" src="/resources/icon/empty_heart_icon.svg">
                    좋아요
                </a>
            </div>
            <c:choose>
                <c:when test="${rvwLkAdmDto.rvw_lk_yn == 1}">
                    <div class="view_btn_set">
                        <i class="fas fa-heart" name="fill-heart" id="heart-fill"></i>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="view_btn_set">
                        <i class="far fa-heart" name="non-fill-heart" id="heart-empty"></i>
                    </div-->
                </c:otherwise>
            </c:choose>
            <!-- 댓글 -->
            <!--div class="CommentBox" id="rvwCmtList">
                <div class="Comment_option">
                    <h3 class="Comment_title"> 댓글 </h3>
                </div>
                <ul class="Comment_list">
                    <li class="CommentItem">
                        <div class="comment_area">
                        <div class="comment_box">
                        <div class="comment_nick_box">
                        <div class="comment_nick_info">
                        <span class="usr_nm">comment.usr_nm</span>
                        </div>
                        </div>
                        <div class="comment_text_box">
                        <p class="comment_text_view">
                        <span class="text_comment">comment.cmt_cont</span>
                        </p>
                        </div>
                        <div class="comment_info_box">
                        <span class="comment_info_date">comment.mdf_date</span>
                        <button class="replyBtn">답글</button>
                        <button class="delBtn">삭제</button>
                        <button class="modBtn">수정</button>
                        </div>
                        </div>
                        </div>
                    </li>
                    <li class="CommentItem CommentItem--reply">
                    </li>
                </ul>
            </div-->
        </div>
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

        // $('.fas fa-heart').on("click", function () {
        $(document).on("click", ".fas fa-heart", function () {
            alert("클릭");
            $.ajax({
                url : '/heart',
                type : 'POST',
                data : {'rvw_no': "${rvwDto.rvw_no}"},
                success : function (data){
                    if(data=="HeartDown") {
                        $(".fas fa-heart").attr("class", "far fa-heart");
                        location.reload();
                    }
                    else {
                        return;
                    }
                }

            });
        });
        // $(".far fa-heart").on("click", function () {
            $(document).on("click", ".far fa-heart", function () {
            alert("클릭");
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
                        location.reload();
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