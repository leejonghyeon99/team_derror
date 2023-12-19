$(document).ready(function (){
    const dropdownMember = $(".dropdownMember");
    const dropdownLogout = $(".dropdownLogout");
    const boardToggle = document.querySelector('#board-toggle');
    const boardList = document.querySelector('.board-list');

    $('.btn-expand-collapse').click(function (e) {
        $('.navbar-primary').toggleClass('collapsed');
        dropdownMember.hide();
        dropdownLogout.hide();
    });

    document.addEventListener("DOMContentLoaded", function () {
        var navbar = document.querySelector(".navbar-primary-menu");
        var navi = document.querySelector(".navi");


        navi.addEventListener("click", function () {
            $('.navbar-primary').toggleClass('collapsed');
            dropdownMember.toggle();
            dropdownLogout.toggle();
            boardToggle.toggle();
            boardList.toggle();
        });

        navbar.addEventListener("mouseleave", function () {
            dropdownMember.style.display = "none";
            dropdownLogout.style.display = "none";
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
