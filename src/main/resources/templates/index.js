$(document).ready(function() {
    $.ajax({
        url: 'https://localhost:8080/videos'
    }).then(function(data, status, jqxhr){
        $('.greeting-id').append(data.id);
        $('')
    })
})