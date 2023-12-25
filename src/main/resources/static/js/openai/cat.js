$(document).ready(function (){
    const box = document.getElementById("prompt");
    const leftDown = document.getElementsByClassName("left-down");
    const leftUp = document.getElementsByClassName("left-up");
    const rightDown = document.getElementsByClassName("right-down");
    const rightUp = document.getElementsByClassName("right-up");
    disNone();
    function disNone(){
        leftUp[0].style.display ='none';
        leftUp[0].style.display = 'none';
        leftDown[0].style.display = 'none';
        leftDown[1].style.display = 'none';

        rightUp[0].style.display = 'none';
        rightDown[0].style.display = 'none';
        rightDown[1].style.display = 'none';
    }

    const leftKeys = ['KeyQ', 'KeyW', 'KeyE', 'KeyR', 'KeyT', 'KeyY', 'KeyA', 'KeyS', 'KeyD', 'KeyF', 'KeyG', 'KeyH', 'KeyZ', 'KeyX', 'KeyC', 'KeyV', 'KeyB', 'Space'];
    const rightKeys = ['KeyU', 'KeyJ', 'KeyN', 'KeyI', 'KeyK', 'KeyM', 'KeyO', 'KeyL', 'KeyP', 'BracketLeft', 'BracketRight', 'Semicolon', 'Quote', 'Comma', 'Period', 'Slash', 'Enter'];

    function isInput(){
        let bool = false;
        if(box.value.trim() === ""){
            bool = false;
        }else{
            bool = true;
        }
        return bool;
    }
    function setRnd(){
        let rnd = Math.round(Math.random());
        return rnd;
    }
    let rnd;
    box.addEventListener("keydown", function(event){
        rnd = setRnd();
        if(isInput()){
            disNone();
        }
        if (leftKeys.includes(event.code)) {
            leftUp[0].style.display = 'none';
            leftDown[rnd].style.display = 'block';
        }else if(rightKeys.includes(event.code)){
            rightUp[0].style.display = 'none';
            rightDown[rnd].style.display = 'block';
        }
    })

    box.addEventListener("keyup", function(event){
        rnd = setRnd();
        if(isInput()){
            disNone();
        }
        if (leftKeys.includes(event.code)) {
            leftUp[0].style.display = 'block';
            leftDown[rnd].style.display = 'none';
        }else if(rightKeys.includes(event.code)){
            rightUp[0].style.display = 'block';
            rightDown[rnd].style.display = 'none';
        }
    })
})