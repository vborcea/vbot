
function ajaxCall(url, postData){
    var resultData = null;

    $.ajax({
        async: false,
        dataType: "json",
        type: "POST",
        data: typeof postData === "object" ? JSON.stringify(postData) : postData,
        url: url,
        contentType:"application/json; charset=utf-8",
        success: function(response){
            resultData = response;
        },
        error: function(){
            resultData = null;
        }
    });

    return resultData;
}

function ajaxCallGet(url, postData){
    var resultData = null;

    $.ajax({
        async: false,
        dataType: "json",
        type: "GET",
        data: typeof postData === "object" ? JSON.stringify(postData) : postData,
        url: url,
        contentType:"application/json; charset=utf-8",
        success: function(response){
            resultData = response;
        },
        error: function(){
            resultData = null;
        }
    });

    return resultData;
}

function ajaxCallGetAsync(url, postData){
    var resultData = null;

    $.ajax({
        dataType: "json",
        type: "GET",
        data: typeof postData === "object" ? JSON.stringify(postData) : postData,
        url: url,
        contentType:"application/json; charset=utf-8",
        success: function(response){
            resultData = response;
        },
        error: function(){
            resultData = null;
        }
    });

    return resultData;
}


function ajaxCallPostAsync(url, postData){
    var resultData = null;

    $.ajax({
        dataType: "json",
        type: "POST",
        data: typeof postData === "object" ? JSON.stringify(postData) : postData,
        url: url,
        contentType:"application/json; charset=utf-8"
    });

    return resultData;
}