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
                    <div class="card custom-card" data-bs-toggle="modal" data-bs-target="#myModal${air.flightid}">
                        <div class="card-body custom-card-body">
                            <h5 class="card-title custom-card-title">${air.airline}</h5>
                            <!-- 다른 필요한 내용 추가 -->
                        </div>
                    </div>
                    <!-- 모달 내용 추가 -->
                    <div class="modal fade" id="myModal${air.flightid}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                      <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">항공일정 상세정보</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                        <div class="modal-body">
                        <!-- 모달 내용 추가 -->
                                <div><strong>국가 및 공항</strong><p>${air.airport}</p></div><hr>
                                <div><strong>공항코드</strong><p>${air.airportcode}</p></div><hr>
                                <div><strong>항공사</strong><p>${air.airline}</p></div><hr>
                                <div><strong>항공기</strong><p>${air.flightid}</p></div><hr>
                                <div><strong>운항일정</strong><p>${air.firstdate} ~ ${air.lastdate}</p></div><hr>
                                <div><strong>출발시간</strong><p>${air.st}</p></div><hr>
                                <div><strong>요일별 출국일정</strong></div>
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
                            </div>
                           </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                            </div>
                        </div>
                    </div>
                </div>
                        `;
                });
                // 예시: 해당 테이블의 tbody에 업데이트된 HTML 삽입
                $('#resultBody2').html(tableHtml);
            }
        },
        error: function (xhr, status, error) {
            console.error('Error:', error);
        }
    });
}

