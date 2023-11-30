$.ajax({
    type: "GET",
    url: "https://app.ticketmaster.com/discovery/v2/venues.json?keyword=UCV&apikey=JVSEuY5G6jkq6i2eYx4EX53D0z5tZz64",
    async: true,
    dataType: "json",
    success: function (json) {
        console.log(json);
    },
});