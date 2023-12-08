const box = document.getElementById("keyword");
const leftUp = document.getElementById("left-up");
const leftDown = document.getElementById("left-down");
const rightUp = document.getElementById("right-up");
const rightDown = document.getElementById("right-down");

leftUp.style.display = 'none';
leftDown.style.display = 'none';
rightUp.style.display = 'none';
rightDown.style.display = 'none';

const leftKeys = ['KeyQ', 'KeyW', 'KeyE', 'KeyR', 'KeyT', 'KeyY', 'KeyA', 'KeyS', 'KeyD', 'KeyF', 'KeyG', 'KeyH', 'KeyZ', 'KeyX', 'KeyC', 'KeyV', 'KeyB', 'Space'];
const rightKeys = ['KeyU', 'KeyJ', 'KeyN', 'KeyI', 'KeyK', 'KeyM', 'KeyO', 'KeyL', 'KeyP', 'BracketLeft', 'BracketRight', 'Semicolon', 'Quote', 'Comma', 'Period', 'Slash', 'Enter'];


box.addEventListener("keydown", function(event){
    if (leftKeys.includes(event.code)) {
        leftUp.style.display = 'block';
        leftDown.style.display = 'none';
    }else if(rightKeys.includes(event.code)){
        rightUp.style.display = 'block';
        rightDown.style.display = 'none';
    }
})

box.addEventListener("keyup", function(event){
    if (leftKeys.includes(event.code)) {
        leftUp.style.display = 'none';
        leftDown.style.display = 'block';
    }else if(rightKeys.includes(event.code)){
        rightUp.style.display = 'none';
        rightDown.style.display = 'block';
    }
})