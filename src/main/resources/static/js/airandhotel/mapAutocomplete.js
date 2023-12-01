// mapAutocomplete.js

// Autocomplete를 초기화할 입력 필드 ID
const inputFieldId = "searchInput";

// Autocomplete 서비스 생성
const autocompleteService = new google.maps.places.AutocompleteService();

// Autocomplete 결과를 보여줄 목록을 초기화할 함수
function clearAutocompleteResults() {
    const resultsContainer = document.getElementById("searchInputResults");
    resultsContainer.innerHTML = "";
}

// Autocomplete 결과를 보여주는 함수
function displayAutocompleteResults(predictions) {
    const resultsContainer = document.getElementById("searchInputResults");

    // 결과 목록을 초기화
    resultsContainer.innerHTML = "";

    // 각 예측 결과를 목록에 추가
    predictions.forEach((prediction) => {
        const resultItem = document.createElement("div");
        resultItem.textContent = prediction.description;
        resultItem.addEventListener("click", () => {
            // 클릭 시 해당 위치로 이동
            moveMapToPlace(prediction.place_id);
            // Autocomplete 결과를 숨김
            clearAutocompleteResults();
        });
        resultsContainer.appendChild(resultItem);
    });
}

// 입력 필드에 따라 Autocomplete 결과 업데이트
function updateAutocompleteResults() {
    const inputField = document.getElementById(inputFieldId);
    const inputValue = inputField.value;

    // 입력 값이 비어있으면 결과를 숨김
    if (!inputValue.trim()) {
        clearAutocompleteResults();
        return;
    }

    // Autocomplete 서비스를 사용하여 예측 결과 가져오기
    autocompleteService.getPlacePredictions(
        {
            input: inputValue,
            types: ["geocode"], // 주소 및 장소에 대한 예측만 가져오도록 설정
        },
        (predictions, status) => {
            if (status === google.maps.places.PlacesServiceStatus.OK) {
                // 예측 결과를 보여줌
                displayAutocompleteResults(predictions);
            } else {
                console.error("Autocomplete request failed with status:", status);
            }
        }
    );
}

// 입력 필드에 이벤트 리스너 추가하여 Autocomplete 결과 업데이트
document.getElementById(inputFieldId).addEventListener("input", updateAutocompleteResults);
