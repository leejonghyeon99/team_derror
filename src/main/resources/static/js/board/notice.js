$(function (){
    $("[name='pageRows']").change(function (){
        let frm = $("[name = 'frmPageRows']");
        frm.attr("method", "POST");
        frm.attr("action", "pageRows")
        frm.submit();
    });

    $("[name='sort']").change(function (){
        let frm = $("[name = 'frmPageRows']");
        frm.attr("method", "POST");
        frm.attr("action", "sort")
        frm.submit();
    });

});