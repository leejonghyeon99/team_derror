$(document).ready(function (){

    $(".uiChilds").click(function (){
        $(".child").toggle();

        let ui = $('.child').css('display');
        if (ui === 'block'){
            $('.hideChilds').show();
            $('.showChilds').hide();
        }else{
            $('.hideChilds').hide();
            $('.showChilds').show();
        }
    })
})








