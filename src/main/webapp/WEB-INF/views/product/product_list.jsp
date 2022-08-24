<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="/css/product/product_list.css">
</head>

<body>

<div class="product-list__header">
  <h1></h1>
</div>

<div class="product-list__content">
  <div class="content--list__wrap">
    <div class="content--filter">
      <a id="vcnt_top" href="<c:url value='/product/list?keyword=vcnt&standard=DESC&usr_id=${sessionScope.userDto.usr_id}&cntn_cd=${param.cntn_cd}&nt_cd=${param.nt_cd}&nt_cd_nm=${param.nt_cd_nm}'/>">조회많은순</a>
      <a id="low_price" href="<c:url value='/product/list?keyword=prd_str_prc&standard=ASC&usr_id=${sessionScope.userDto.usr_id}&cntn_cd=${param.cntn_cd}&nt_cd=${param.nt_cd}&nt_cd_nm=${param.nt_cd_nm}'/>">낮은가격순</a>
      <a id="high_price" href="<c:url value='/product/list?keyword=prd_str_prc&standard=DESC&usr_id=${sessionScope.userDto.usr_id}&cntn_cd=${param.cntn_cd}&nt_cd=${param.nt_cd}&nt_cd_nm=${param.nt_cd_nm}'/>">높은가격순</a>
    </div>
    <c:forEach var="item" items="${list}" varStatus="status">

      <section class="content--wrap">
        <div class="content--list">
          <div class="content--list_img">
            <img src="${item.img_pth}">
            <c:choose>
              <c:when test="${item.usr_id == sessionScope.userDto.usr_id && not empty sessionScope.userDto.usr_id}">
                <div><i class="fas fa-heart" name="fill-heart" cnt="${status.count}" prd_cd="${item.prd_cd}"></i></div>
              </c:when>

              <c:otherwise>
                <div><i class="far fa-heart" name="non-fill-heart" cnt="${status.count}" prd_cd="${item.prd_cd}"
                        prd_nm="${item.prd_nm}" prd_str_prc="${item.prd_str_prc}"></i></div>
              </c:otherwise>
            </c:choose>


          </div>
          <div class="content--list_info">
            <div class="info-tit">
              <span class="item-tit">${item.prd_nm}</span>
              <span class="item-desc">${item.prd_dtl_desc}</span>
              <div class="item-period">
                <i class="far fa-calendar"></i>
                <span>여행기간</span>
                <span>${item.trv_per}</span>
              </div>
              <div class="item-strdate">
                <i class="far fa-calendar"></i>
                <span>출발기간</span>
                <span><fmt:formatDate value="${item.dpr_str_date}" pattern="yyyy-MM-dd"/>  ~  <fmt:formatDate value="${item.dpr_fin_date}" pattern="yyyy-MM-dd" /> </span>
              </div>
              <span class="item-arl">${item.arl_nm}</span>
            </div>
            <div class="info-price">
              <span class="item-dstn_cd">${item.prd_cd}</span>
              <div class="item-prd_str_prc">
                <span><fmt:formatNumber value="${item.prd_str_prc}" pattern="#,##0"/></span>
                <span>원~</span>
              </div>
              <div class="item-detailBtn__wrap">
                <button class="item-detailBtn" id="detailBtn" name="btnDetail" prd_cd="${item.prd_cd}" cnt="${status.count}" able="false">
                  <em>자세히보기<i class="fas fa-chevron-down"></i></em>
                  <em>닫기<i class="fas fa-chevron-up"></i></em>
                </button>
              </div>
            </div>
          </div>
        </div>

        <div class="content--detail-items" prd_cd="${item.prd_cd}">
          <input type="hidden"/>
          <div class="content--detail__item">
            <ul class="detail__item--list${status.count}"></ul>
          </div>
        </div>

      </section>
    </c:forEach>
  </div>
</div>

<script>


  // 상품 리스트 보기
  $(document).ready(function () {


    // 글자색 변경을 위해 Controller 에게 옵션을 받음
    let option = "${option}";

    if(option==1){
      $('#vcnt_top').css({'font-weight':'bold','color':'black'});
      $('#high_price').css({'font-weight':'bold','color':'rgba(0, 0, 0, .5)'});
      $('#low_price').css({'font-weight':'bold','color':'rgba(0, 0, 0, .5)'});
    }else if(option==2){
      $('#low_price').css({'font-weight':'bold','color':'black'});
      $('#high_price').css({'font-weight':'bold','color':'rgba(0, 0, 0, .5)'});
      $('#vcnt_top').css({'font-weight':'bold','color':'rgba(0, 0, 0, .5)'});
    }else if(option==3){
      $('#high_price').css({'font-weight':'bold','color':'black'});
      $('#vcnt_top').css({'font-weight':'bold','color':'rgba(0, 0, 0, .5)'});
      $('#low_price').css({'font-weight':'bold','color':'rgba(0, 0, 0, .5)'});
    }else{
      $('#low_price').css({'font-weight':'bold','color':'rgba(0, 0, 0, .5)'});
      $('#high_price').css({'font-weight':'bold','color':'rgba(0, 0, 0, .5)'});
      $('#vcnt_top').css({'font-weight':'bold','color':'rgba(0, 0, 0, .5)'});
    }




    // 자세히보기 버튼 클릭 시 클래스 'active' 추가
    $('button[name="btnDetail"]').on('click', function () {
      $(this).toggleClass('active');
      $(this).closest('.content--list').next().toggleClass('active');

      // 변수 설정
      const prd_cd = $(this).attr('prd_cd');
      const count = $(this).attr('cnt');
      let able = $(this).attr('able');

      if(able=='false'){
        $(this).attr('able','true');
        // 리스트 출력
        if ($(this).hasClass('active')) {
          $.ajax({
            type: 'GET',
            url: "<c:url value='/product/detailList'/>",
            data: {prd_cd:prd_cd},
            success: function (result) {

              $(result).each(function() {
                $('.detail__item--list'+count).
                append('<li class="detail-item__list">'+
                        '<div class="detail-item__list--info">'+
                        '<span class="list__prd_nm">'+ this.prd_nm +'</span>'+
                        '<div><i class="far fa-calendar"></i><span class="list__dpr_date">'+ this.dom_dpr_date +'</span>'+
                        '~<span class="list__fin_date">'+ this.dom_fin_date +'</span></div>'+
                        '<span>'+ this.arl_nm +'</span></div>'+
                        '<div class="detail-item__list--price"><span>'+ this.prd_str_prc +'<span>원</span></span>'+
                        '<button class="detail-item__btn--btn" prd_dtl_cd="' + this.prd_dtl_cd + '" name="detailItemBtn">'+
                        '자세히보기</button></div></li>');
              });
            },
            error: function () {
              alert("에러 발생");
            }
          });
        }
      } else {
        return;
      }
    });

    var usr_id = "${sessionScope.userDto.usr_id}";

    $('i[name=fill-heart]').on("click",function (){
      let count = $(this).attr('cnt');
      let prd_cd = $(this).attr('prd_cd');
      if(usr_id == null || usr_id === ""){
        alert("로그인을 해야 관심 상품 기능을 이용할 수 있습니다.");
        var confirmUser = confirm("로그인 하시겠습니까?");
        if(confirmUser){
          location.href = "<c:url value='/user/login'/>"
        }else{
          return;
        }
      }

        $.ajax({
          type : "POST",
          url  : "<c:url value='/product/like/delete'/>",
          data : {"prd_cd":prd_cd,"usr_id":usr_id},
          success : function (){
              window.location.reload();
          },
          error : function (){
            alert("오류가 발생했습니다.");
          }
        });
    });

    $('i[name=non-fill-heart]').on("click",function (){
      let count = $(this).attr('cnt');
      let prd_cd = $(this).attr('prd_cd');
      let prd_nm = $(this).attr('prd_nm');
      let prd_str_prc = $(this).attr('prd_str_prc');

      if(usr_id == null || usr_id === ""){
        alert("로그인을 해야 관심 상품 기능을 이용할 수 있습니다.");
        var confirmUser = confirm("로그인 하시겠습니까?");
        if(confirmUser){
          location.href = "<c:url value='/user/login'/>"
        }else{
            return;
        }
      }

      $.ajax({
        type : "POST",
        url  : "<c:url value='/product/like/insert'/>",
        data : {"prd_cd":prd_cd,"usr_id":usr_id,"prd_nm":prd_nm,"prd_str_prc":prd_str_prc},
        success : function (){
          window.location.reload();
        },
        error : function (){
          alert("오류가 발생했습니다.");
        }
      });
    });

    $(document).on("click",".detail-item__btn--btn",function () {
      const prd_dtl_cd = $(this).attr('prd_dtl_cd');

      $.ajax({
        type : "POST",
        url : "<c:url value='/product/show'/>",
        data : {"prd_dtl_cd":prd_dtl_cd},
        success : function (data){
          console.log("성공");
          location.href = "/product/detail?prd_dtl_cd="+prd_dtl_cd;
        },
        error : function (){
          console.log("실패");
          location.href = "/product/detail?prd_dtl_cd="+prd_dtl_cd;
        }
      });
    });

  });

</script>
</body>
</html>