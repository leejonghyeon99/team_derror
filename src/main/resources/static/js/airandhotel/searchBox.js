// searchBox.js

// 서치박스 초기화
function initializeSearchBox(map) {
    const input = document.getElementById("searchInput");
    const searchBox = new google.maps.places.SearchBox(input);

    searchBox.addListener("places_changed", () => {
        const places = searchBox.getPlaces();
        if (places.length === 0) {
            return;
        }

        const place = places[0];
        input.value = place.name;

        if (!place.geometry || !place.geometry.location) {
            console.log("Returned place contains no geometry");
            return;
        }

        if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
        } else {
            map.setCenter(place.geometry.location);
            map.setZoom(14);
        }

        // 검색된 장소로 호텔 검색 실행
        searchHotels(place.name);
    });
}

// 자동완성 결과 업데이트 함수
function updateAutocompleteResults() {
    // 자동완성 결과 업데이트에 필요한 코드를 추가하세요.
    // 예를 들어, 자동완성 결과를 가져오고 화면에 표시하는 등의 동작을 수행합니다.
    // 필요한 경우 이 부분에 코드를 추가하세요.
}

// 이벤트 리스너 추가
document.getElementById("searchInput").addEventListener("input", updateAutocompleteResults);
