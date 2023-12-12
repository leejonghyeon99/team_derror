$(function () {
    $("[name='pageRows']").change(function () {
        let frm = $("[name = 'frmPageRows']");
        frm.attr("method", "POST");
        frm.attr("action", "pageRows")
        frm.submit();
    });


    // 최신순 조회순 전환
    $("[name='sort']").change(function () {

        let val = $(this).val();
        console.log(val);
        let frm = $("[name = 'frmPageRows']");

        if (val === 'id' || val == null) {
            frm.attr("method", "GET");
            frm.attr("action", "http://localhost:8080/board/notice");
            frm.submit();
        }

        if (val === 'viewCnt') {
            frm.attr("method", "GET");
            frm.attr("action", "http://localhost:8080/board/notice/desc");
            frm.submit();
        }
    })


    // 검색창 검색 결과가 없을때
    $(document).ready(function() {
        var noSearchResults = $('#noSearchResults').data('no-search-results');
        if (noSearchResults === true) {
            alert('검색 결과가 없습니다.');
            history.back();
        }
    });




}); // end script
