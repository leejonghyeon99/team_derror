//------------------------- map ------------------
function myMap() {
    let latitudeElement = document.getElementById('latitude')
    let longitudeElement = document.getElementById('longitude')

    let lat = parseFloat(latitudeElement.textContent);
    let lng = parseFloat(longitudeElement.textContent);

    const mapProp = {
        center: new google.maps.LatLng(lat, lng),
        zoom: 17,
    };
    const map = new google.maps.Map(document.getElementById('googleMap'), mapProp);

    const myPos1 = {lat: lat, lng: lng};
    let marker1 = new google.maps.Marker({position: myPos1});
    marker1.setMap(map);
};
