$(function () {

    const  frmPageRows = $("[name='frmPageRows']");
    const  searchKeyword = $("#searchKeyword");
    const  select = $(".form-select");
    const keyword = $("[name='keyword']").val();


    select.change(function (){
        if(keyword.length){
            let selectedPageRows = $("select[name='pageRows']").val();
            let selectedPage = $("select[name='sort']").val();
            let selectedSort = $("select[name='sort']").val();
            searchKeyword.append(`<input type="text" name="pageRows" value=${selectedPageRows}>`);
            searchKeyword.append(`<input type="text" name="sort" value=${selectedSort}>`);
            searchKeyword.submit();
            return
        }else{
            frmPageRows.submit();
        }

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
    $('#searchButton').submit(function(event) {

        const keyword = $('#keyword').val().trim();
        if (keyword !== '') {
            // 검색 동작: window.location.href = '/search?keyword=' + keyword;
            // 혹은 AJAX를 사용하여 검색 요청을 서버에 보낼 수 있습니다.
            console.log('검색어:', keyword);
        }
    });





}); // end script
