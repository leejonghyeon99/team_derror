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

}); // end script
