$(function () {


    const postList = $('.post-body');


    //최초 로딩
    getList();

    $(".form-select").change(function (){
        getList();
    })

    function getList(){
        let  category = $('[name="category"]').val();
        let sort = $('[name="sort"]').val();

        $.ajax({
            url: `api/list/${category}?sort=${sort}`,
            method : "GET",
            success : function (data,status){

                if (status === "success"){
                    listRendering(data)
                }
            }

        })
    }
    // // 최신순 조회순/ 몇개 뽑을건지
    // $("[name='pageRows']").change(function () {
    //     let $category = $("#category").val();
    //     let frm = $("[name = 'frmPageRows']");
    //     frm.attr("method", "POST");
    //
    //     let sort = $("[name='sortForm']").val();
    //     console.log("목록에"+sort) // 목록에 id
    //     if (sort === 'id') {
    //         frm.attr("action", `${$category}/pageRows`)
    //         frm.submit();
    //     }
    //     if (sort === 'view') {
    //         frm.attr("action", `${$category}/pageRows`)
    //         frm.submit();
    //     }
    // });
    //
    //
    // // 최신순, 조회순 정렬
    // $("[name='sortForm']").change(function () {
    //     let $category = $("#category").val();
    //     let sort = $(this).val();
    //     console.log("정렬에"+sort) // 정렬에 view
    //     // 여기서 새로고침 왜????
    //     let frm = $("[name = 'frmPageRows']");
    //     frm.attr("method", "GET");
    //
    //     if (sort === 'id') {
    //         frm.attr("action", `/board/${$category}`);
    //         frm.submit();
    //     }
    //
    //     if (sort === 'view') {
    //         frm.attr("action", `/board/${$category}`);
    //         frm.submit();
    //     }
    // });



    //*************************************************************************************


    // 검색창 검색 결과가 없을때
    $(function () {
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
            $('#shareCards').hide(); // 공유게시판 보이기
        }
        if (path === '/board/free') {
            $("#free").show(); // 자유게시판을 보여줌
            $('#shareCards').hide(); // 공유게시판 보이기
        }
        if (path === '/board/share') {
            $('#listTable').hide(); // 테이블 숨기기
            $('#shareCards').show(); // 공유게시판 보이기
            $("#share").show();
        }
    }

    // 페이지 로드 시 URL에 따라 제목 표시
    $(function () {
        displayTitleBasedOnURL(); // 페이지 로드 시 호출하여 URL에 따른 제목 표시

        // 뒤로 가기, 앞으로 가기 시 URL 변경에 따른 제목 변경
        window.onpopstate = function (event) {
            displayTitleBasedOnURL(); // URL에 따른 제목 표시
        };
    });


}); // end script
