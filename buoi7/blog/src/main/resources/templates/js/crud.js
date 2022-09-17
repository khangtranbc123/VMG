$(document).ready(function() {

    // $("#phantrang1").click(function (){
    //     $(".noidung-pt").load();
    // });
    $.getJSON('http://localhost:8080/blog', function (json) {
        $("#chuyen").click(function (){

        })
        var tr = [];
        for(var i = 0;i < json.content.length;i++){
            console.log(json.content[i].title);
            tr.push('<tr>');
            tr.push('<td>' + json.content[i].title + '</td>');
            tr.push('<td>' + json.content[i].content + '</td>');
            tr.push('<td>' + json.content[i].categorys.name + '</td>');
            tr.push('<td>' + json.content[i].authors.authorName + '</td>');
            tr.push('<td>' + json.content[i].covers + '</td>');
            tr.push('<td><button class=\'edit\'>Edit</button>&nbsp;&nbsp;<button class=\'delete\' id=' + json.content[i].id + '>Delete</button></td>');
            tr.push('</tr>');
        }
        $('table').append($(tr.join('')));
    });

    $.getJSON('http://localhost:8080/category', function(json) {
        var tr=[];
        for (var i = 0; i < json.length; i++) {
            tr.push('<option value="${json[i].id}" id="categorys">' + json[i].name + '</option>');
        }
        $("#op1").append($(tr.join('')));
    });

    $.getJSON('http://localhost:8080/author', function(json) {
        var tr=[];
        for (var i = 0; i < json.length; i++) {
            tr.push('<option value="${json[i].id}" id="authors">' + json[i].authorName + '</option>');
        }
        $("#op2").append($(tr.join('')));
    });

    $(document).delegate('.delete', 'click', function (){
        if (confirm("Bạn muốn xóa à")){
            var id = $(this).attr('id');
            var parent = $(this).parent().parent();
            $.ajax({
                type: "DELETE",
                url: "http://localhost:8080/blog/delete/" + id,
                cache: false,
                success: function (){
                    parent.fadeOut('slow', function (){
                        $(this).remove();
                    });
                    location.reload(true);
                },
                error: function () {
                    $('#err').html('<samp style=\'coler: red;\'>Erorr deleting record').fadeIn().fadeOut(4000, function (){
                        $(this).remove();
                    });
                }
            });
        }
    });
    $(document).delegate('#addNew', 'click', function (event){
        // var title = $('#title').val();
        // var content = $('#content').val();
        // var categorys = $('#categorys').val();
        // var authors = $('#authors').val();
        let form = $('#add')[0];
        let blogFrom = new FormData(form);
        console.log(blogFrom)
        $.ajax({
            type: "POST",
            contentType: false,
            enctype: 'multipart/form-data',
            url: "http://localhost:8080/blog/create",
            processData: false,
            data: blogFrom,
            // data: JSON.stringify({'title': title}),
            // data: JSON.stringify({'content': content}),
            // data: JSON.stringify({'categorys': categorys}),
            // data: JSON.stringify({'authors': authors}),
            cache: false,
            success: function (result){
                $("msg").html("<span style='color: green'>Company added successfully</span>");
                window.setTimeout(function (){location.reload()}, 1000)
            },
            error: function (err){
                $("#msg").html("<span style='color: red'>Name is required</span>");
            }

        });
        event.preventDefault();
    });


});