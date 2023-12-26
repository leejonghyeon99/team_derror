$(document).ready(function() {
  const content = $("#content");
  const prompt = $("#prompt");
  const textBox = $(".cat-text");
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

  function startCatText(){
    textBox.fadeToggle();
  }

  // 정보
  function infoRequest(prompt) {
    console.log(prompt);
    let interval = setInterval(startCatText,1000);
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
        clearInterval(interval);
        textBox.hide();
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
      <p>사용언어</p>
      <p>${rec.language}</p>
    `;



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
        imgRender(photos,data.recommendation.id);

      },
      error: function (error) {
        console.error(error);
      }
    });

  }

  function imgRender(photos, id){
    let imgUrl;
    console.log(photos)
    let images = [];
    images.push(id);
    for (let i = 0; i < photos.length; i++) {
      imgUrl = `https://places.googleapis.com/v1/${photos[i].name}/media?key=${apiKey}&maxHeightPx=1920&maxWidthPx=1080`;
      console.log(imgUrl)
      images.push(imgUrl);
      $(".cat-chat:last").prepend(`<img src="${imgUrl}">`)
    }
    saveImage(images);

  }


  function saveImage(imgUrl){

    $.ajax({
      url : "/openai/api/saveImages",
      method: "POST",
      headers: {
        'Content-Type': 'application/json',
      },
      data: JSON.stringify(imgUrl),
      success: function (response) {
        console.log(response);
      },
      error: function (error) {
        console.error(error);
      }
    })
  }
});