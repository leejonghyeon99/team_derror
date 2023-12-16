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
        showEventDetails();
    });
    function showEventDetails() {
        // 이벤트 데이터를 가져오거나 미리 정의
        var eventData = {
            name: "이벤트 이름",
            time: "2023-12-31 18:30:00",
            imageUrl: "https://example.com/image.jpg"
        };
        // 모달 내용 업데이트
        document.getElementById('eventName').textContent = eventData.name;
        document.getElementById('eventTime').textContent = eventData.time;
        // 이미지 업데이트
        var imgElement = document.getElementById('eventImage');
        imgElement.src = eventData.imageUrl;
        imgElement.alt = "이벤트 이미지";
        // 모달 열기
        openModal('modal2');
    }

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
