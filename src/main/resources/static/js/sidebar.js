const dropdownMember = $(".dropdownMember");
const dropdownLogout = $(".dropdownLogout");

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
    });

    navbar.addEventListener("mouseleave", function () {
            dropdownMember.hide();
            dropdownLogout.hide();
    });
});
