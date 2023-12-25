$(document).ready(function() {
  const content = $("#content");
  const prompt = $("#prompt");

  content.scrollTop(content[0].scrollHeight);
  prompt.on("keydown", function(event) {
    if (event.keyCode == 13 ) {

      userBoxRender(prompt);
      infoRequest(prompt);
    }
  });


  // 채팅
  function chatRequest(prompt) {
    $.ajax({
      url: `/openai/api/chat?prompt=${prompt.val()}`,
      method: 'GET',
      success: function(data) {
        console.log(data)
        catBoxRender(data);
        prompt.val("");
      },
      error: function(jqXHR, textStatus, errorThrown) {
        console.error('Error:', errorThrown);
      }
    });
  }


  // 정보
  function infoRequest(prompt) {
    console.log(prompt);
    loadingRender();
    $.ajax({
      url: `/openai/api/recommend?prompt=${prompt.val()}`,
      method: 'GET',
      success: function(data) {
        console.log(data)
        catBoxRender(data);
        nearInfo(data);
        $(".loading").remove();
        prompt.val("");
      },
      error: function(jqXHR, textStatus, errorThrown) {
        console.error('Error:', errorThrown);
        $(".loading").remove();
      }
    });
  }

  function loadingRender(){
    let html = `
      <img src="../../img/assets/loading.gif" alt="loading">  
    `;

    const newChatBox = $("<div class='loading'></div>").addClass("chat-box cat-chat");
    newChatBox.append(html);
    content.append(newChatBox);
    content.scrollTop(content[0].scrollHeight);
  }




  function catBoxRender(data){
    let rec = data.recommendation;

    let html = `
      <h3>${rec.place}</h3>
      <p>${rec.national}</p>
      <p>${rec.city}</p>
      <p>${rec.address}</p>
      <p>${rec.detail}</p>
    `;
    html += `<p>사용언어</p>`
    for (var e of rec.languages) {
      html += (`<p>${e.language}</p>`);
    }


    const newChatBox = $("<div></div>").addClass("chat-box cat-chat");
    newChatBox.append(html);
    content.append(newChatBox);
    content.scrollTop(content[0].scrollHeight);
  }

  function userBoxRender(keyword){
    const newChatBox = $("<div></div>").addClass("chat-box user-chat");
    newChatBox.append(`<span>${keyword.val()}</span>`);
    content.append(newChatBox);
    content.scrollTop(content[0].scrollHeight);
  }


  //###################################### 이미지 렌더

  const url = 'https://places.googleapis.com/v1/places:searchNearby';
  const apiKey = 'AIzaSyAqcohoRqpDKerLz1FpanyQihPZsy5p7f0';



  function nearInfo(data){

    const requestData = {
      includedTypes: ['historical_landmark'],
      maxResultCount: 1,
      locationRestriction: {
        circle: {
          center: {
            latitude: data.recommendation.latitude,
            longitude: data.recommendation.longitude
          },
          radius: 300.0
        }
      }
    };

    const headers = {
      'Content-Type': 'application/json',
      'X-Goog-Api-Key': apiKey,
      'X-Goog-FieldMask': 'places.photos'
    };

    let photos = [];
    $.ajax({
      url: url,
      type: 'POST',
      data: JSON.stringify(requestData),
      headers: headers,
      contentType: 'application/json',
      success: function (response) {
        console.log(response);
        photos = response.places[0].photos;
        imgRender(photos);

      },
      error: function (error) {
        console.error(error);
      }
    });

  }

  function imgRender(photos){
    let imgUrl;
    console.log(photos)

    for (let i = 0; i < photos.length; i++) {
      imgUrl = `https://places.googleapis.com/v1/${photos[i].name}/media?key=${apiKey}&maxHeightPx=1920&maxWidthPx=1080`;
      $(".cat-chat:last").prepend(`<img src="${imgUrl}">`)
    }

  }

});