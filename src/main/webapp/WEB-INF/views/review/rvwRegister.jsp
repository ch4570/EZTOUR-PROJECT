<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/rvw/rvwRegister.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<div class="wrap">
    <div class="content">
        <div class="review-wrap">
            <div class="review-title">여행후기 ${mode=="new" ? "작성" : "수정"}</div>
            <div class="review-title-desc">
                여행을 다녀온 고객분들의
                <br>
                솔직한 여행 이야기
            </div>
        </div>
        <div class="boarder-wrap">
            <form action="" id="form-reviewRegister" method="post">
                <h2>
                    제목  <input class="tit" tpye="text" name="rvw_ttl" value="${rvwDto.rvw_ttl}" placeholder="제목을 입력해주세요">
                    <input class="value-move" tpye="hidden" name="usr_id" value="${rvwDto.usr_id}">
                    <input class="value-move" tpye="hidden" name="rvw_no" value="${rvwDto.rvw_no}">
                    <input class="value-move" tpye="hidden" name="wrt_nm" value="${rvwDto.wrt_nm}">
                    <input class="value-move" tpye="hidden" name="wrt_email" value="${rvwDto.wrt_email}">
                    <input class="value-move" tpye="hidden" name="rvw_cont" value="${rvwDto.rvw_cont}">
                    <p class="left">
                        <span class="writer" name="wrt_nm" value="${rvwDto.wrt_nm}">작성자 | ${rvwDto.wrt_nm}</span>
                        <span class="email" name="wrt_email" value="${rvwDto.wrt_email}">이메일 | ${rvwDto.wrt_email}</span>
                    </p>
                </h2>
                <!-- 여행지역 select-->
                <div class="prdInfo">
                    <h2>
                        여행지역
                        <select class="prd_select "name="prd_dtl_cd">
                            <option value="">선택해주세요</option>
                            <c:forEach var="rvwDto" items="${list}">
                                <option value="${rvwDto.prd_dtl_cd}">${rvwDto.prd_nm}</option>
                            </c:forEach>
                        </select>
                    </h2>
                    <div class="content-desc">
                    </div>
                </div>
                <!-- 글 내용-->
                <textarea class="detailInfo-content" name="rvw_cont" placeholder="내용을 입력해주세요"><c:out value="${rvwDto.rvw_cont}"/></textarea>
                <div class="boradBtns">
                    <c:if test="${mode ne 'new'}">
                        <button class="btn sz-inp st-lblue btn_summit" type="button" id="modifyBtn">확인</button>
                    </c:if>
                    <c:if test="${mode eq 'new'}">
                        <button class="btn sz-inp st-lblue btn_summit" type="button" id="newRegisterBtn">확인</button>
                    </c:if>
                    <button class="btn sz-inp st-lblue btn_summit" type="button" id="cancelBtn">취소</button>
                    <button class="btn sz-inp st-lblue btn_summit" type="button" id="listBtn">목록</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    let msg="${msg}";
    if(msg=="RVW_REGISTER_ERR") alert("여행 지역, 제목, 내용 하나라도 비어 있으면 등록할 수 없어요. 확인해주세요.");
    if(msg=="WRT_ERR") alert("게시물 등록에 실패했습니다. 다시 시도해주세요.");

    $(document).ready(function (){
        // 신규 등록
        $('#newRegisterBtn').on("click", function(){
            let form = $('#form-reviewRegister');
            form.attr("action", "<c:url value='/review/write'/>");
            form.attr("method", "post");
            form.submit();
        });
        // 수정 등록
        $('#modifyBtn').on("click", function(){
            let form = $('#form-reviewRegister');
            form.attr("action", "<c:url value='/review/modify'/>");
            form.attr("method", "post");
            form.submit();
        });
        // 취소
        $('#cancelBtn').on("click", function(){
            if(!confirm("정말로 취소하시겠습니까?")) return;
            location.href = "<c:url value='/review/list${searchCondition.queryString}'/>";
        });

        // 목록
        $('#listBtn').on("click", function(){
            location.href = "<c:url value='/review/list${searchCondition.queryString}'/>";
        });
    });
</script>
</body>
</html>