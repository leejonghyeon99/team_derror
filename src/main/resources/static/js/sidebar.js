$(document).ready(function (){

    const dropdownMember = $(".dropdownMember");
    const dropdownLogout = $(".dropdownLogout");
    const userToggle = document.querySelector('.user-toggle');
    const userlist = document.querySelector('.user-list');
    const boardToggle = document.querySelector('.board-toggle');
    const boardList = document.querySelector('.board-list');

    const navbar = document.querySelector(".navbar-primary");
    const navi = document.querySelector(".navi");

    userToggle.addEventListener('mouseenter', function(){
        userlist.style.display = 'block';
    })

    userToggle.addEventListener('mouseleave', function(){
        userlist.style.display = 'none';
    })

    // 사이드바 하단 화살표 버튼
    $('.btn-expand-collapse').click(function (e) {
        $('.navbar-primary').toggleClass('collapsed');
    });


    document.addEventListener("DOMContentLoaded", function () {

        // navi.addEventListener("click", function () {
        //     $('.navbar-primary').toggleClass('collapsed');
        //     dropdownMember.toggle();
        //     dropdownLogout.toggle();
        //     boardToggle.toggle();
        //     boardList.toggle();
        // });

        navi.addEventListener("click", function () {
            const navbarPrimary = $('.navbar-primary');
            if (!navbarPrimary.hasClass('collapsed')) {
                dropdownMember.toggle();
                dropdownLogout.toggle();
                boardToggle.toggle();
                boardList.toggle();
            }
            navbarPrimary.toggleClass('collapsed');
        });

    });
        boardToggle.addEventListener('click', function (event) {
            event.preventDefault();
            boardList.style.display = boardList.style.display === 'none' ? 'block' : 'none';
        });


    const boardLinks = document.querySelectorAll('.board-list a');
    boardLinks.forEach(function (link) {
        link.addEventListener('click', function (event) {
            // 기본 동작인 링크로 이동하는 것을 막습니다.
            event.preventDefault();
            const destination = this.getAttribute('href');
            // 페이지 이동
            window.location.href = destination;
        });
    });


})