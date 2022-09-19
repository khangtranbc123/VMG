$(document).ready(function() {

    // $("#phantrang1").click(function (){
    //     $(".noidung-pt").load();
    // });
    // $("#chuyen").click(function (){
    //
    // })
    $.getJSON('http://localhost:8080/blog', function (json) {
        var tr2 = [];

        // console.log(json.totalPages)
        // if (json.number = 0){
            // tr2.push('<li>');
            tr2.push('<a> Previous  </a>')
            // tr2.push('</li>');
        // }
        // if (json.number < json.totalPages-1){
            // tr2.push('<li>');
            tr2.push('<a>  next  </a>')
            // tr2.push('</li>');
        // }
        $('#number').append($(tr2.join('')));

    });

    $.getJSON('http://localhost:8080/blog', function (json) {
        var tr = [];
        let image = '';

        for(var i = 0;i < json.content.length;i++){
            console.log(json.content[i]);
            tr.push('<tr>');
            tr.push('<td>' + json.content[i].title + '</td>');
            tr.push('<td>' + json.content[i].content + '</td>');
            tr.push('<td>' + json.content[i].categorys.name + '</td>');
            tr.push('<td>' + json.content[i].authors.authorName + '</td>');
           //tr.push('<td>' + json.content[i].covers.name + '</td>');
           //  json.content[i].covers.forEach(c =>{
           //      console.log(c)
           //      image += `<img src="${'http://localhost:8080/image/' + c.name}" width="50px">`
           //  });
            for(var j =0; j< json.content[i].covers.length;j++){
                tr.push(`<td><img src="${'http://localhost:8080/image/' + json.content[i].covers[j].name}" width="100px"></td>`)
            }

           // tr.push('<td id="showImg"></td>');
            tr.push('<td><button class=\'edit\'>Edit</button>&nbsp;&nbsp;<button class=\'delete\' id=' + json.content[i].id + '>Delete</button></td>');
            tr.push('</tr>');
        }
        $('table').append($(tr.join('')));
    });

    $.getJSON('http://localhost:8080/category', function(json) {
        var tr=[];
        for (var i = 0; i < json.length; i++) {
            tr.push('<option value="'+json[i].id+'" id="categorys">' + json[i].name + '</option>');
        }
        $("#categorys").append($(tr.join('')));
    });

    $.getJSON('http://localhost:8080/author', function(json) {
        var tr=[];
        for (var i = 0; i < json.length; i++) {
            tr.push('<option value="'+json[i].id+'" id="authors">' + json[i].authorName + '</option>');
        }
        $("#authors").append($(tr.join('')));
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
        let form = $('#add')[0];
            console.log(form);
            let blogFrom = new FormData(form);
            // console.log(blogFrom)
            $.ajax({
                type: "POST",
                contentType: false,
                enctype: 'multipart/form-data',
                processData: false,
                data: blogFrom,
                url: "http://localhost:8080/blog/create",
               // cache: false,
                success: function (result){
                    console.log(result)
                    $("#msg").html("<span style='color: green'>Company added successfully</span>");
                    window.setTimeout(function (){location.reload()}, 1000)
                },
                error: function (err){
                    console.log(err)
                    $("#msg").html("<span style='color: red'>Lỗi</span>");
                }
            });
            event.preventDefault();
    });

    $(document).delegate('#find', 'click', function (){
        if (confirm("find?")){
            var x = document.getElementById('find');

            console.log(text);
            var parent = $(this).parent().parent();
            $.ajax({
                 type: "GET",
                 url: "http://localhost:8080/blog/find?text=" + x,
                success: function (json){



                    //  console.log(json);
                    // parent.fadeOut('slow', function (){
                    //     $(this).get();
                    //     console.log("ok!");
                    // });
                   // location.reload(true);
                },
                error: function () {
                  console.log("loi!");
                }
            });
        }
    });


});