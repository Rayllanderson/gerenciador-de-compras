<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">


<!--   -->
<link href="resource/css/account.css" type="text/css" rel="stylesheet" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<style type="text/css">

/* Container */
.container{
   margin: 0 auto;
   border: 0px solid black;
   width: 50%;
   height: 250px;
   border-radius: 3px;
   background-color: ghostwhite;
   text-align: center;
}
/* Preview */
.preview{
   width: 100px;
   height: 100px;
   border: 1px solid black;
   margin: 0 auto;
   background: white;
}

.preview img{
   display: none;
}
/* Button */
.button{
   border: 0px;
   background-color: deepskyblue;
   color: white;
   padding: 5px 15px;
   margin-left: 10px;
}

</style>

<title>My Account</title>
</head>



<div class="container">




<img alt="" src=> <path fill-rule="evenodd" d="M2 13.5a.5.5 0 0 0 .5.5h6a.5.5 0 0 0 0-1H3.707L13.854 2.854a.5.5 0 0 0-.708-.708L3 12.293V7.5a.5.5 0 0 0-1 0v6z"/> 


<div class="container">
    <form method="post" action="" enctype="multipart/form-data" id="myform">
        <div class='preview'>
            <img src="" id="img" width="100" height="100">
        </div>
        <div >
            <input type="file" id="file" name="file" />
            <input type="button" class="button" value="Upload" id="but_upload">
        </div>
    </form>
</div>







</div>





<script>
  /*  $(function() {
        $('#upload-form').ajaxForm({
            success: function(msg) {
                alert("File has been uploaded successfully");
            },
            error: function(msg) {
                $("#upload-error").text("Couldn't upload file");
            }
        });
    });
	function upload() {
	var target = document.querySelector("#target");
	var file = document.querySelector("input[type=file]").files[0];
	
	
	var fd = new FormData();    
	fd.append( 'file', document.querySelector("input[type=file]").files[0] );
	console.log(fd)
	
	
	var reader = new FileReader();
			
	reader.onloadend = function () {
				
		
		
	    target.src = reader.result;
	    $('#aa').val(reader.result)
				
	    // Upload Ajax
	    $.ajax({
	        method: "POST",
		url: "my-account?action=base64",
		data: { 
		    file: reader.result
		}
	    })
	    .done(function(response) {
	        alert("Sucesso: " + response);
	    })
	    .fail(function(xhr, status, errorThrown) {
	        alert("Error: " + xhr.responseText);
	    });
	};
			
	if (file) {						
	    reader.readAsDataURL(file);	
	    reader.readAsDataURL(input.files[0]);
	} else {
	    target.src = "";
	}
	}	
	 */
</script>








<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<script src="resource/javascript/accountAjax.js"></script>



<script type="text/javascript">

$(document).ready(function(){

    $("#but_upload").click(function(){

        var fd = new FormData();
        var files = $('#file')[0].files;
        
        // Check file selected or not
        if(files.length > 0 ){
           fd.append('file',files[0]);

           $.ajax({
              url: 'my-account?action=base64',
              type: 'post',
              data: {file: fd } ,
              contentType: false ,
              processData: false,
              success: function(response){
                 console.log('...')
              },
           });
        }else{
           alert("Please select a file.");
        }
    });
});


</script>


</body>
</html>