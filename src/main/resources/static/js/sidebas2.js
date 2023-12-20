const dropdownMember = $(".dropdownMember");
const dropdownLogout = $(".dropdownLogout");
const navbarPrimary = $('.navbar-primary');
const boardToggle = document.querySelector('#board-toggle');
const boardList = document.querySelector('.board-list');

$('.btn-expand-collapse').click(function () {
    navbarPrimary.toggleClass('collapsed');
    hideDropdowns();
});

document.addEventListener("DOMContentLoaded", function () {
    const navbar = document.querySelector(".navbar-primary-menu");
    const navi = document.querySelector(".navi");

    navi.addEventListener("click", function () {
        if (!navbarPrimary.hasClass('collapsed')) {
            updateDropdownVisibility();
        }
    });

    navbar.addEventListener("mouseleave", function () {
        hideDropdowns();
    });

    navi.addEventListener("click", function () {
        $('.navbar-primary').toggleClass('collapsed');
        boardToggle.toggle();
        boardList.toggle();
    });

});

function updateDropdownVisibility() {
    if (!navbarPrimary.hasClass('collapsed')) {
        dropdownMember.show();
        dropdownLogout.show();
    } else {

        hideDropdowns();
    }
}

function hideDropdowns() {
    dropdownMember.hide();
    dropdownLogout.hide();
}

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