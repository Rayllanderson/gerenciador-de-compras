if (!window.sendPost) {
    window.sendPost = function (url, obj) {
        //Define o formulário
        var myForm = document.createElement("form");
        myForm.action = url;
        myForm.method = "post";

        for (var key in obj) {
            var input = document.createElement("input");
            input.type = "hidden";
            input.value = obj[key];
            input.name = key;
            myForm.appendChild(input);
        }
        //Adiciona o form ao corpo do documento
        document.body.appendChild(myForm);
        //Envia o formulário
        myForm.submit();
    };
}