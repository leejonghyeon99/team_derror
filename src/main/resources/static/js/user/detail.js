//----------------버튼-------------------------
document.addEventListener("DOMContentLoaded", function () {

    var buttons = document.querySelectorAll('input[type="button"]');


    buttons.forEach(function (button) {
        button.addEventListener('click', function () {
            // Remove 'active' class from all buttons
            buttons.forEach(function (btn) {
                btn.classList.remove('active');
            });

            this.classList.add('active');
        });
    });
});

//----------------------모달창------------------

function openModal(modalId) {
    var modal = document.getElementById(modalId);


    var currentOpenModal = document.querySelector('.modal.show');
    if (currentOpenModal) {
        closeModal(currentOpenModal.id);
    }

    modal.style.display = "block";
    modal.classList.add('show');
}

function closeModal(modalId) {
    var modal = document.getElementById(modalId);
    modal.style.display = "none";
    modal.classList.remove('show');
}


document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("modalOpen1").addEventListener("click", function () {
        openModal("modal1");
    });

    document.getElementById("modalOpen2").addEventListener("click", function () {
        openModal("modal2")
    });

    document.getElementById("modalOpen3").addEventListener("click", function () {
        openModal("modal3");
    });

    document.getElementById("modalOpen4").addEventListener("click", function () {
        openModal("modal4");
    });

    var closeBtn = document.getElementsByClassName("close");
    for(var i = 0; i<closeBtn.length; i++){
        closeBtn[i].addEventListener("click",function (){
            var modalId = this.parentElement.parentElement.id;
            closeModal(modalId);
        })
    }
});
