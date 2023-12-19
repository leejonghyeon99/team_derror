$(function (){
    // [추가] 버튼 누르면 될 첨부파일 추가 가능
    var i = 0;
    $("#btnAdd").click(function(){
        $("#files").append(`
           <div class="input-group mb-2">
               <input class="form-control col-xs-3" type="file" name="upfile${i}"/>
               <button type="button" class="btn btn-outline-danger" onclick="$(this).parent().remove()">삭제</button>
           </div>`);
        i++;
    });

    // [삭제] 버튼 누르면 삭제될 첨부파일 id 담기
    $("[data-fileid-del]").click(function (){
        let fileId = $(this).attr('data-fileid-del');
        deleteFiles(fileId);
        $(this).parent().remove();
    });

    // Summernote 추가
    $("#content").summernote({
        height: 300,
        placeholder: 'Hello stand alone ui',
        tabsize: 2,
        toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'underline', 'clear']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['table', ['table']],
            ['view', ['fullscreen', 'codeview', 'help']]
        ]
    });
});

function deleteFiles(fileId){
    $("#delFiles").append(`<input type='hidden' name='delfile' value='${fileId}'>`);
}