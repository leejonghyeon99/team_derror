$(function(){
    // [추가] 버튼 누르면 첨부파일 추가
    var i = 0;
    $("#btnAdd").click(function(){
        $("#files").append(`
            <div class="input-group mb-2">
               <input class="form-control col-xs-3" type="file" name="upfile${i}"/>
               <button type="button" class="btn btn-outline-danger" onclick="$(this).parent().remove()">삭제</button>
            </div>
        `);
        i++;
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

    var i = 0;
    var maxSize = 1073741824; // 허용된 최대 파일 총 크기 (10GB)

    $("#btnAdd").click(function() {
        var fileInput = $('<input>', {
            class: 'form-control col-xs-3',
            type: 'file',
            name: 'upfile' + i,
            multiple: true
        }).on('change', function () {
            var totalSize = 0;
            for (var j = 0; j < this.files.length; j++) {
                totalSize += this.files[j].size; // 파일 크기를 더합니다.
            }
            if (totalSize > maxSize) {
                alert('첨부 파일의 총 크기가 허용된 크기를 초과합니다.');
                $(this).val(''); // 파일 입력 초기화
            }
        });
    });
});