const menu = document.querySelector('.menu-item');
const signBox = document.querySelector('.sign');


menu.addEventListener('mouseenter', function(){
    signBox.style.display = 'block';
})

menu.addEventListener('mouseleave', function(){
    signBox.style.display = 'none';
})

const userToggle = document.querySelector('.user-toggle');
const userlist = document.querySelector('.user-list');

    userToggle.addEventListener('mouseenter', function(){
        userlist.style.display = 'block';
    })

    userToggle.addEventListener('mouseleave', function(){
        userlist.style.display = 'none';
    })