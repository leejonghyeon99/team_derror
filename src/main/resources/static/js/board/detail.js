$(document).ready(function (){

    // 글 [삭제] 버튼
    $("#btnDel").click(function(){
        let answer = confirm("삭제하시겠습니까?");
        if(answer){
            $("form[name='frmDelete']").submit();
        }
    });

    const commentBody = $('.comment-body');
    const replyComment = $("#replyComment");
    let childList;

    $(".reply-comment-btn").click(function() {
        // 폼 데이터 수집
        var formData = new FormData(replyComment[0]);

        // Ajax 요청 보내기
        $.ajax({
            url: '/comment/parent/write',
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function(data, status) {
                console.log(status);
                console.log(data);
                updateParent(data);
                replyComment.find('[name="content"]').val('');
            },
            error: function(error) {
                console.error(error);
            }
        });
    });


    function updateParent(data){
        console.log(data)
        let date = new Date(data.comment.createdDate);
        let dateToString = `${date.getFullYear()}-${date.getMonth()}-${date.getDay()} ${date.getHours()}:${date.getMinutes()}`;
        let html = `<div class="parent" data-commentid="${data.comment.id}">
                           <div class="display-flex">
                               <img class="user-logo" src="/upload/thumbnail/${data.comment.member.thumbnail_img}">
                               <div class="post-info">
                                   <span class="info-member">${data.comment.member.name}</span>
                                   <span> ${dateToString} </span>
                                   <div class="toggleChild">
                                       <a><span class="reply">답글</span></a>
                                       <a>
                                       <span class="comment-icon">댓글 <strong class="child-cnt">${data.comment.childCnt}</strong>개
                                        <i class="bi bi-caret-down up"></i>
                                        <i class="bi bi-caret-up down"></i>
                                       </span>
                                        </a>
                                   </div>                                 
                               </div>
                               <span class="comment-content">${data.comment.content}</span>
                           </div> 
                           <div class="parent-form"></div>
                           <div class="child-list"></div>
                        </div>
                        <br>
                        `
        commentBody.prepend(html);
    }


    $(document).on('click','.reply', function (){
        childList = $(this).closest('.parent').find('.child-list');
        let commentId = $(this).closest('.parent').data('commentid');
        let form = $(this).closest('.parent').find('.parent-form');
        let data = `
                <form>
                    <input type="hidden" name="memberId" value="${logged_id}">
                    <input type="hidden" name="postId" value="${post_id}">
                    <input type="hidden" name="commentId" value="${commentId}">                         
                    <input type="text" name="content" required>
                    <button class="child-reply-btn" type="button">작성</button>
                </form>`

        if(!(form.children().length > 0)){
            form.html(data);
            childReply(form);
        }else{
            form.html('');
        }

    })





    let childCnt;
    function childReply(parentForm){
        childCnt = parentForm.closest('.parent').find('.child-cnt')[0];
        let cnt = parseInt(childCnt.innerText,10);
        parentForm.find('form').find('.child-reply-btn').click(function (){
            var formData = new FormData(parentForm.find('form')[0]);


            $.ajax({
                url: '/comment/child/write',
                type: 'POST',
                data: formData,
                contentType: false,
                processData: false,
                success: function(data, status) {
                    updateChild(data);
                    cnt++;
                    childCnt.innerHTML = cnt;
                    console.log(childCnt,cnt)
                    parentForm.find('input').val('');
                    parentForm.html('');
                },
                error: function(error) {
                    console.error(error);
                }
            });
        });

    }

    function updateChild(data){
        console.log(data)
        let date = new Date(data.comment.createdDate);
        let dateToString = `${date.getFullYear()}-${date.getMonth()}-${date.getDay()} ${date.getHours()}:${date.getMinutes()}`;
        let html = `<div class="child">
                           <div class="display-flex">
                               <img class="user-logo" src="/upload/thumbnail/${data.comment.member.thumbnail_img}">
                               <div class="post-info">
                                   <span class="info-member">${data.comment.member.name}</span>
                                   <span> ${dateToString} </span>
                               </div>
                               <span class="comment-content">${data.comment.content}</span>
                           </div> 
                        </div>
                        <br>
                        `
        childList.prepend(html);
    }



    $(document).on('click','.comment-icon', function (){
        $(this).find('i').toggle();
        $(this).closest('.parent').find('.child-list').toggle();
    })
})


