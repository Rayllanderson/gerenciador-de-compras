

$(document).on("click", "#teste", function() {             // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
    $.get("produtos?acao=teste", function(responseXml) {                // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response XML...
        $("#tabela").html($(responseXml).find("data").html()); // Parse XML, find <data> element and append its HTML to HTML DOM element with ID "somediv".
    });
});