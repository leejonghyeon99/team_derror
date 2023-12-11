function searchCountry() {
    var countryName = document.getElementById("countryName").value;

    // API 호출
    fetch(`/api/air/code`)
        .then(response => response.json())
        .then(data => {
            var countryList = data.response.body.items || [];
            var filteredData = countryList.filter(country => country.countryName.includes(countryName));

            // 결과를 표에 표시
            displayResults(filteredData);
            $('.aircode').on('click', function () {
                const code = $(this).data('airport-code');
                send(code);
            });
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById("resultBody").innerHTML = '<tr><td colspan="3">Error occurred while fetching data.</td></tr>';
        });
}


function displayResults(data) {
    var resultHtml = '';
    data.forEach(country => {
        var airportCode = country.airportCode;
        resultHtml += `<tr>
          <td>${country.countryName}</td>
          <td>${country.airportName}</td>
          <td>${airportCode}</td>
          <td>
              <button type="button" class="btn btn-info text-white aircode" data-airport-code="${airportCode}">선택</button>
          </td>
        </tr>`;
    });

    document.getElementById("resultBody").innerHTML = resultHtml;

}

function send(code) {
    $.ajax({
        url: `/api/air/airport`,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({code: code}), // 코드를 JSON 형식으로 전송
        success: function (data, status) {
            if (status === "success") {
                var tableHtml = '';
                data.forEach(function (air) {
                    tableHtml += `
                      <div class="table-container">
                      <table class="table table-bordered text-center uptable">
                         <tr>
                            <th>항공기</th>
                            <th>항공사</th>
                            <th>출발시간</th>
                            <th>시작일정</th>
                            <th>종료일정</th>
                            <th>항공코드</th>
                            <th>공항</th>
                         </tr>
                         <tr>
                            <td>${air.flightid}</td>
                            <td>${air.airline}</td>
                            <td>${air.st}</td>
                            <td>${air.firstdate}</td>
                            <td>${air.lastdate}</td>
                            <td>${air.airportcode}</td>
                            <td>${air.airport}</td>
                        </tr>
                      </table>
                      <table class="table table-bordered text-center downtable">
                       <tr>
                            <th>월요일</th>
                            <th>화요일</th>
                            <th>수요일</th>
                            <th>목요일</th>
                            <th>금요일</th>
                            <th>토요일</th>
                            <th>일요일</th>
                        </tr>
                        <tr>
                            <td style="font-size: 22px;">${(air.monday === "Y") ? "🛫" : "⛔"}</td>
                            <td style="font-size: 22px;">${(air.tuesday === "Y") ? "🛫" : "⛔"}</td>
                            <td style="font-size: 22px;">${(air.wednesday === "Y") ? "🛫" : "⛔"}</td>
                            <td style="font-size: 22px;">${(air.thursday === "Y") ? "🛫" : "⛔"}</td>
                            <td style="font-size: 22px;">${(air.friday === "Y") ? "🛫" : "⛔"}</td>
                            <td style="font-size: 22px;">${(air.saturday === "Y") ? "🛫" : "⛔"}</td>
                            <td style="font-size: 22px;">${(air.sunday === "Y") ? "🛫" : "⛔"}</td>
                        </tr>
                        </table>
                        </div>`;
                });
                // 예시: 해당 테이블의 tbody에 업데이트된 HTML 삽입
                $('#resultBody2').html(tableHtml);
                handleResize();
            }
        },
        error: function (xhr, status, error) {
            console.error('Error:', error);
        }
    });
}

