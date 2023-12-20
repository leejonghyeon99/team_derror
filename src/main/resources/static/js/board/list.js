$(function () {

  const  frmPageRows = $("[name='frmPageRows']");
  const  select = $(".form-select");

    select.change(function (){
      frmPageRows.submit();
  });


    //*************************************************************************************

    // 검색창 검색 결과가 없을때
    $(function () {
        var noSearchResults = $('#noSearchResults').data('no-search-results');
        if (noSearchResults === true) {
            alert('검색 결과가 없습니다.');
            history.back();
        }
    });

    // 엔터로 검색 가능
    $('#serchButton').submit(function(event) {
        event.preventDefault(); // 기본 동작(페이지 리로드) 방지
        const keyword = $('#keyword').val().trim();
        if (keyword !== '') {
            // 검색 동작: window.location.href = '/search?keyword=' + keyword;
            // 혹은 AJAX를 사용하여 검색 요청을 서버에 보낼 수 있습니다.
            console.log('검색어:', keyword);
        }
    });




}); // end script
