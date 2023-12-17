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



}); // end script
