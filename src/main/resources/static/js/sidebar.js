const dropdownMember = $(".dropdownMember");
const dropdownLogout = $(".dropdownLogout");
const navbarPrimary = $('.navbar-primary');

$('.btn-expand-collapse').click(function (e) {
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