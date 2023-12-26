$(document).ready(function () {
    startSlideshow();
});

function startSlideshow() {
    // 이미지가 로딩될 때마다 이벤트 핸들러 등록
    $('#backgroundSlideshow img').on('load', function () {
        // 첫 번째 이미지가 로딩되면 로딩 페이지 숨김
        if ($(this).is(':first-child')) {
        }
    });

    // 슬라이드쇼 시작 코드는 그대로 유지
    var displayTime = 1500; // 2초
    var transitionTime = 1000; // 1초

    setInterval(function () {
        nextSlide();
    }, displayTime + transitionTime);
}

function nextSlide() {
    var currentSlide = $('#backgroundSlideshow img.active');
    var nextSlide = currentSlide.next();

    if (nextSlide.length === 0) {
        nextSlide = $('#backgroundSlideshow img:first');
    }

    currentSlide.removeClass('active');
    nextSlide.addClass('active');
}