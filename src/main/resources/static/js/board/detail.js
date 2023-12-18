$(document).ready(function (){

    $(".uiChilds").click(function (){

        $(this).closest(".comment-item").find(".child").toggle();
        $(this).closest(".comment-item").find(".child-hr").toggle();
        $('.hideChilds').toggle();
        $('.showChilds').toggle();
    })
})








