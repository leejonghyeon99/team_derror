$("#imageCarousel").hide();
$("#place").hide();




// 초기화 함수
$("#keyword").on("keyup", function(event) {
    if (event.key === "Enter") {
        requestApi();
    }
});


function requestApi() {
    let $keyword = $("#keyword").val();
    $.ajax({
        url: "http://localhost:8080/openai/api/recommendation?keyword=" + $keyword,
        method: "GET",
        dataType: "json",
        success: function (data) {
            imagesFunc(data);


            $("#imageCarousel").show();
            $("#place").show();
        },
        error: function (error) {
            // 실패 시 실행되는 콜백 함수
            console.error("에러 발생: ", error);
        }
    });
}



let placeService;
// 초기화 함수
async function init() {
    const { PlacesService } = await google.maps.importLibrary("places");
    placeService = new PlacesService(document.getElementById("place"));
}
let place = $("#place");
async function imagesFunc(data) {
    await init(); // 이미 초기화되었는지 확인 후 초기화
    let rec = data.recommendation;
    let str = `${rec.national} ${rec.city} ${rec.place}`;
    console.log(str);

    let images = [];

    // 각 키워드에 대해 요청 수행
    let request = {
        query: str,
        fields: ['name', 'photos'], // 반환할 필드 지정
    };

    // placeService를 사용할 수 있음
    if (placeService) {
        await new Promise(resolve => {
            placeService.findPlaceFromQuery(request, function(results, status) {
                if (status === "OK") {
                    console.log(results)

                    var info = results[0];
                    for (var i = 0; i < info.photos.length; i++) {
                        if (info.photos && info.photos.length > 0) {
                            images.push(info.photos[i].getUrl({maxWidth: 500, maxHeight: 600}));
                        }
                    }
                } else {
                    console.error('Error:', status);
                }
                resolve(); // Promise를 해결하여 다음 반복으로 진행
            });
        });

        // 이미지 정보를 담는 div에 정보 추가
        let createInfo = `
                <div class="image-info">
                    <h5>국가명 : ${rec.national}</h5>
                    <h5>도시 : ${rec.city}</h5>
                    <h5>장소 : ${rec.place}</h5>                   
                    <p>${rec.detail}</p>
                </div>
            `;

        place.html(createInfo);

    } else {
        console.error('placeService is not initialized.');
    }

    // 모든 이미지 배열 출력
    console.log(images);
    displayImages(images);
}

function displayImages(images) {
    let carouselInner = $(".carousel-inner");

    // Bootstrap Carousel에 이미지 추가
    carouselInner.empty(); // 이미지 슬라이드 초기화
    for (let i = 0; i < images.length; i++) {
        let activeClass = i === 0 ? "active" : "";
        carouselInner.append(`
            <div class="carousel-item ${activeClass}">
                <img src="${images[i]}" class="d-block w-100" alt="Image ${i + 1}">
            </div>
        `);
    }

    // Bootstrap Carousel 초기화
    $("#imageCarousel").carousel();
}
