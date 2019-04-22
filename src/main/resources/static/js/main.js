$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/destinations"
    }).then(function(data) {
       $('.dest-name').append(data.name);
       $('.dest-address').append(data.address);
    });
});