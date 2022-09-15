$(document).ready(function() {

    // $("#phantrang1").click(function (){
    //     $(".noidung-pt").load();
    // });

    $.getJSON('http://localhost:8080/blog', function (json) {
        var tr = [];
        for (var i = 0; i < 5; i++) {
            tr.push('<tr>');
            tr.push('<td>' + json[i].title + '</td>');
            tr.push('<td>' + json[i].content + '</td>');
            tr.push('<td>' + json[i].categorys.name + '</td>');
            tr.push('<td>' + json[i].authors.authorName + '</td>');
            tr.push('<td>' + json[i].covers + '</td>');
            tr.push('<td><button class=\'edit\'>Edit</button>&nbsp;&nbsp;<button class=\'delete\' id=' + json[i].id + '>Delete</button></td>');
            tr.push('</tr>');
        }
        $('table').append($(tr.join('')));
    });

});