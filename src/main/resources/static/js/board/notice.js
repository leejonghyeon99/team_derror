$(function (){
    $("[name='pageRows']").change(function () {
        let frm = $("[name = 'frmPageRows']");
        frm.attr("method", "POST");
        frm.attr("action", "pageRows")
        frm.submit();
    });

    $("[name='sort']").change(function (){

        let val = $(this).val();
        console.log(val);
        let frm = $("[name = 'frmPageRows']");

        if(val === 'id' || val == null){
            frm.attr("method", "GET");
            frm.attr("action", "http://localhost:8080/board/notice");
            frm.submit();
        }

        if(val === 'viewCnt'){
            frm.attr("method", "GET");
            frm.attr("action", "http://localhost:8080/board/notice/desc");
            frm.submit();
        }
    })

    $('#searchForm').submit(function(event) {
        event.preventDefault(); // 폼 제출 기본 동작 방지
        const keyword = $('#searchInput').val(); // 검색어 가져오기

        // 검색어를 서버로 전송
        $.ajax({
            url: '/board/search', // 검색을 처리할 서버 엔드포인트 설정
            method: 'GET',
            data: { keyword: keyword }, // 검색어를 서버로 전송
            success: function(response) {
                // 검색 결과를 처리하여 화면에 표시
                displaySearchResults(response); // 검색 결과를 표시하는 함수 호출
            },
            error: function(error) {
                // 검색 요청 실패 시 처리
                console.error('검색 요청 실패:', error);
            }
        });
    });

// 검색 결과를 처리하여 화면에 표시하는 함수
    function displaySearchResults(results) {
        // 결과를 처리하여 화면에 표시하는 코드 작성
        // 예: 결과를 반복하여 각각의 결과를 테이블에 추가하는 방식
        let tableBody = $('#tableBody'); // 결과를 넣을 테이블 바디 엘리먼트 선택

        // 기존 테이블 내용을 지우고, 검색 결과를 테이블에 추가
        tableBody.empty(); // 테이블 바디 초기화

        // 각 결과를 테이블에 추가
        results.forEach(function(result) {
            // 각 결과를 테이블에 추가하는 코드 작성
            let newRow = '<tr>' +
                '<td>' + result.id + '</td>' +
                '<td>' + result.title + '</td>' +
                // 나머지 필드들도 추가
                '</tr>';

            tableBody.append(newRow); // 테이블에 새로운 행 추가
        });
    }


}); // end script
