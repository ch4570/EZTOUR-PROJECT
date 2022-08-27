<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
</head>
    <body>

        <div class="aside">
            <button class="aside__btn" name="asideBtn">
                <em><i class="fas fa-ellipsis-v"></i></em>
            </button>
            <div class="aside__menu">
                <ul class="aside__menu--list">
                    <li>
                        <a class="aside__link">
                            <span><i class="fas fa-history" name="prd_history"></i></span>
                            <span>최근 본 상품</span>
                        </a>
                    </li>
                    <li>
                        <a class="aside__link" href="#">
                            <span><i class="far fa-clone"></i></span>
                            <span>상품 비교함</span>
                        </a>
                    </li>
                    <li>
                        <a class="aside__link" href="#">
                            <a href="<c:url value='/product/attractive'/>"><span><i class="far fa-heart"></i></span></a>
                            <span>관심상품</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="foot">
            <div class="foot-menu">
                <a class="foot-menu-dtl" href="">여행정보</a>
                <a class="foot-menu-dtl" href="">홍보센터</a>
                <a class="foot-menu-dtl" href="">여행약관</a>
                <a class="foot-menu-dtl" href="">개인정보처리방침</a>
                <a class="foot-menu-dtl" href="">기업/단체문의</a>
                <a class="foot-menu-dtl" href="">대리점안내</a>
                <a class="foot-menu-dtl" href="">신용카드 무이자 할부안내</a>
                <a class="foot-menu-dtl" href="">EZ Tour 전화번호안내</a>
            </div>
            <div class="foot-Address">
                <dl>
                    <dt class="foot-title">Address & Contact</dt>
                    <dd class="foot-desc">
                        "대표번호 : 5555-0000"
                        <br>
                        서울특별시 종로구 청계천로 51-1, 교원투어 빌딩
                    </dd>
                </dl>
            </div>
            <div class="foot-Information">
                <dl>
                    <dt class="foot-title">EzTour Information</dt>
                    <dd class="foot-desc">
                        (주)EzTour 대표 : xxx
                        <br>
                        서울특별시 종로구 청계천로 51-1, 교원투어 빌딩
                        <br>
                        본사 사업자등록번호 : 000-0000-0000
                    </dd>
                </dl>
            </div>
            <div class="foot-FollowUs">
                <dl>
                    <dt class="foot-title">Follow Us</dt>
                    <dd class="foot-desc">
                        여행자 배상책임보험 20억원 가입
                        <br>
                        일반여행업 보증금외 15억원 가입
                        <br>
                        xxxx@eztour.co.kr
                    </dd>
                </dl>
            </div>
        </div>

        <div class="modal_main hidden">
            <div class="modal_wrap">
                <div class="modal_recently">
                    <i class="fa-solid fa-x" name="modal_close_btn"></i>
                    <div class="modal_tlt">
                        <strong>최근 본 상품 (${sessionScope.trvList == null ? 0 : sessionScope.trvList.size()})</strong>
                        <hr>
                    </div>
                    <div class="modal_recently_content">
                        <div class="product--list__modal">
                            <c:forEach items="${sessionScope.trvList}" var="trvList">
                                <div class="product__recent--list">
                                    <a><i class="fa-solid fa-x" name="product__recent--cancel" prd_cd="${trvList.prd_cd}"></i></a>
                                    <img src="<c:url value='${trvList.img_pth}'/>" width="300px" height="400px">
                                    <a href="/product/recent/list?prd_cd=${trvList.prd_cd}"><p>${trvList.prd_nm}</p></a>
                                    <div class="product__cost--list"><strong>${trvList.prd_str_prc}</strong><h6>원</h6></div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <script>

        const modal_main = document.querySelector(".modal_main");

        $(document).ready(function () {



            // 자세히보기 버튼 클릭 시 클래스 'active' 추가
            $('button[name="asideBtn"]').on('click', function () {
                $(".aside").toggleClass('open');
                $(".aside__btn").toggleClass('open');
            });

            $('i[name=modal_close_btn]').click(function (){
                modal_main.classList.add("hidden");
            });

            $('i[name=prd_history]').click(function (){
                modal_main.classList.remove("hidden");
            });

            $(document).on("click","i[name=product__recent--cancel]",function (){
                let prd_cd = $(this).attr('prd_cd');

                $.ajax({
                    type : "GET",
                    url : "<c:url value='/product/show/delete'/>",
                    data : {'prd_cd':prd_cd},
                    success : function (data){
                        $('.product__recent--list').remove();
                        $('.modal_tlt > strong').remove();

                        if(data.length == "0"){
                            $('.modal_tlt').append('<strong>최근 본 상품 (0)</strong>');
                        }else{
                            $('.modal_tlt').append('<strong>최근 본 상품 ('+data.length+')</strong>');
                        }

                        $(data).each(function (){
                            $('.product--list__modal').append('<div class="product__recent--list">'+
                                '<a><i class="fa-solid fa-x" name="product__recent--cancel" prd_cd='+this.prd_cd+'></i></a>'
                                + '<img src='+ this.img_pth +' width="300px" height="400px">' +
                                '<a href="/product/recent/list?prd_nm="'+this.prd_nm+'><p>'+ this.prd_nm +'</p></a>'
                                + '<div class="product__cost--list"><strong>' + this.prd_str_prc + '</strong><h6>원</h6></div>'
                                + '</div>'
                            );

                        });
                    },
                    error : function (data){

                    }
                });
            });

            $('i[name=product__recent--cancel]').on("click",function (){
                let prd_cd = $(this).attr('prd_cd');

                $.ajax({
                    type : "GET",
                    url : "<c:url value='/product/show/delete'/>",
                    data : {'prd_cd':prd_cd},
                    success : function (data){
                        $('.product__recent--list').remove();
                        $('.modal_tlt > strong').remove();

                        if(data.length == "0"){
                            $('.modal_tlt').append('<strong>최근 본 상품 (0)</strong>');
                        }else{
                            $('.modal_tlt').append('<strong>최근 본 상품 ('+data.length+')</strong>');
                        }



                        $(data).each(function (){
                            $('.product--list__modal').append('<div class="product__recent--list">'+
                                '<a><i class="fa-solid fa-x" name="product__recent--cancel" prd_cd='+this.prd_cd+'></i></a>'
                                + '<img src='+ this.img_pth +' width="300px" height="400px">' +
                                '<a href="/product/recent/list?prd_nm="'+this.prd_nm+'><p>'+ this.prd_nm +'</p></a>'
                                + '<div class="product__cost--list"><strong>' + this.prd_str_prc + '</strong><h6>원</h6></div>'
                                + '</div>'
                            );


                        });
                    },
                    error : function (data){

                    }
                });

            });

        });
    </script>
    </body>
</html>