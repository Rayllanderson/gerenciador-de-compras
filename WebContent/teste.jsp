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


<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>jQuery Waves.js Demo</title>
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Make some waves!</title>


<style type="text/css">
body, html {
  height: 100%;
}

body {
  font-family: "Lato", sans-serif;
  color: #111;
  display: -webkit-box;
  display: flex;
  -webkit-box-orient: horizontal;
  -webkit-box-direction: normal;
          flex-direction: row;
  -webkit-box-align: center;
          align-items: center;
  align-content: center;
  -webkit-box-pack: center;
          justify-content: center;
  background-color: #ECECEC;
}

.container {
  width: auto;
  display: -webkit-box;
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
          flex-direction: column;
} 

/**nao preciso */

.btn {
  margin: 20px auto;
  border: none;
  padding: 10px 44px;
  font-size: 36px;
  position: relative;
  
}
.btn::before {
  -webkit-transition: all 0.85s cubic-bezier(0.68, -0.55, 0.265, 1.55);
  transition: all 0.85s cubic-bezier(0.68, -0.55, 0.265, 1.55);
  content: '';
  width: 50%;
  height: 100%;
  background: black;
  position: absolute;
  top: 0;
  left: 0;

}


.btn {
  border-radius: 50px;
}
.btn .text-green { /*texto*/
  color: #6e4af6;
}
.btn::before {
  border-radius: 50px;
  width: 1px;
  background: #6e4af6;
  mix-blend-mode: normal;
}

.btn:hover::before  {
  	background: #6e4af6;
  	width: 100%;
   mix-blend-mode: normal;
}















.hb {
      position: relative;
      box-sizing: border-box;
      display: inline-block;
      overflow: hidden;
      padding: 8px 20px;
      margin: 0px 3px 6px;
      text-align: center;
      text-decoration: none;
      color:  #6e4af6;
      white-space: nowrap;
      z-index: 0;
      border-radius: 1em;
      
} 
 


 

.hb::before {
      position: absolute;
      content: "";
      background: rgb(255, 255, 255);
      transition-duration: 0.8s;
      z-index: -1;
      top: 0px;
      right: 0px;
      bottom: auto;
      left: auto;
      width: 50%;
      height: 100%;
      opacity: 1;
} 
 

.hb:hover::before {
      width: 0px;
      height: 100%;
      opacity: 1;
} 
 

.hb:hover {
      background:  #6e4af6;
      color: #fff;
      transition: color 0.8s, background 0s;
} 
 

.hb::after {
      position: absolute;
      content: "";
      background: rgb(255, 255, 255);
      transition-duration: 0.8s;
      z-index: -1;
      top: 0px;
      right: auto;
      bottom: auto;
      left: 0px;
      width: 50%;
      height: 100%;
      opacity: 1;
} 
 

.hb:hover::after {
      width: 0px;
} 
 








</style>

</head>
<body>


<a href="#" class="hb">Reveal Middle</a>


<div class="container">
  <button class="btn"><span class="text-green">Login</span></button>
</div>
  
  
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>

</body>
</html>