$(document).ready(function (){

    $(".uiChilds").click(function (){

        $(this).closest(".comment-item").find(".child").toggle();
        $(this).closest(".comment-item").find(".child-hr").toggle();
        $(this).closest(".comment-item").find(".hideChilds").toggle();
        $(this).closest(".comment-item").find(".showChilds").toggle();
    })


    let parentId;
    let item;
    $(".comment-reply").click(function (){
        item = $(this).closest(".comment-item");
        item.find(".hideReply").toggle();
        item.find(".showReply").toggle();


        parentId = $(this).closest(".comment-item").data('parent-id');
        let replyBody = $(this).closest(".comment-item").find(".reply-body");
        if (!replyBody.html()) {
            replyBody.html(`
            <form class="reply-form" action="/comment/child/write" method="post">
                <input type="hidden" name="commentId" value="${parentId}">                               
                <input type="hidden" name="memberId" value="${logged_id}">
                <input type="hidden" name="postId" value="${post_id}">
                <input type="text" name="content" required>
                <button class="child-reply-btn" type="button">작성</button>
            </form>
            `);
        }
        item.find(".reply-form").toggle();
        childReplyAddEvents(replyBody);
    })

    function childReplyAddEvents( element ){
        $('.child-reply-btn').click(function (){
            subForm(element);
        })
    }

    function subForm(element){
        let input = element.find('input');

        let formsInput =[];
        for (const e of input) {
            formsInput.push(e.value);
        }

        console.log(formsInput)
        var data = {
            commentId: formsInput[0],
            memberId: formsInput[1],
            postId : formsInput[2],
            content : formsInput[3].trim()
        }
        console.log(data);
        $.ajax({
            url: '/comment/child/write',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (data, status) {
                console.log(data)
                appentChildComment(data);

            },
            error: function (err) {
                console.log(err);
            }
        });
    }

    function appentChildComment(data){
        let date;
        let datetime;
        let reply;
        let replyHtml =[];
        for (const e of data.list) {
            date = new Date(e.createdDate);
            datetime = `${date.getFullYear()}-${date.getMonth()}-${date.getDay()} ${date.getHours()}-${date.getMinutes()}`
            reply= `
        <hr>
        <div class="child">
            <div class="comment-item">
                <div class="comment-logo">
                <img class="user-logo" src="">          
                </div>
            <div class="comment-body">
                <span class="comment-item-user">${e.member.name}</span>
                <span class="comment-item-content">${e.content}</span>
                <div>
                    <span class="comment-item-created">${datetime}</span>
                </div>
            </div>
            </div>
        </div>
        `
            replyHtml.push(reply);
        }
        item.find('.child-list').html(replyHtml);
        item.find('.child').show();


    }
})




