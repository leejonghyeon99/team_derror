$(function () {


    $("[name='pageRows']").change(function () {
        let $category = $("#category").val();
        let frm = $("[name = 'frmPageRows']");
        frm.attr("method", "POST");
        frm.attr("action", `${$category}/pageRows`)
        frm.submit();
    });


    // 최신순 조회순 전환
    $("[name='sort']").change(function () {
        let $category = $("#category").val();
        let val = $(this).val();
        console.log(val);
        let frm = $("[name = 'frmPageRows']");

        if (val === 'id' || val == null) {
            frm.attr("method", "GET");
            frm.attr("action", `/board/${$category}`);
            frm.submit();
        }

        if (val === 'viewCnt') {
            frm.attr("method", "GET");
            frm.attr("action", `${$category}/desc`);
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

    // URL에서 경로를 확인하여 해당하는 제목을 보여주는 함수
    function displayTitleBasedOnURL() {
        var path = window.location.pathname; // 현재 URL 경로 가져오기

        if (path === '/board/notice') {
            $("#notice").show(); // 공지사항을 보여줌
        } else if (path === '/board/free') {
            $("#free").show(); // 자유게시판을 보여줌
        }
    }

    // 페이지 로드 시 URL에 따라 제목 표시
    $(function() {
        displayTitleBasedOnURL(); // 페이지 로드 시 호출하여 URL에 따른 제목 표시

        // 뒤로 가기, 앞으로 가기 시 URL 변경에 따른 제목 변경
        window.onpopstate = function(event) {
            displayTitleBasedOnURL(); // URL에 따른 제목 표시
        };
    });


}); // end script
