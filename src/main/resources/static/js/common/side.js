$(document).ready(function (){
    const postlink = $(".post-link");
    const post = $(".post");
    const mainMenu = $(".main-menu");
    const mainContent = $(".main-content");
    const logout = $("#logout");
    const logoutFrm = $("#logout-frm");

// // mainMenu에 대한 이벤트 핸들러 추가
//     mainMenu.on("mouseover", function() {
//         mainContent.css("padding-left", "200px");
//     });
//
//     mainMenu.on("mouseout", function() {
//         mainContent.css("padding-left", "60px");
//     });

// postlink에 대한 이벤트 핸들러 추가
    postlink.on("mouseover", function() {
        post.css("display", "block");
    });

    postlink.on("mouseout", function() {
        post.css("display", "none");
    });

    logout.click(function (){
        logoutFrm.submit();
    })
})