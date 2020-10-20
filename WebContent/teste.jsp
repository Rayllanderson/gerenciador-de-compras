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
    

html {
  box-sizing: border-box;
}
*,*::before,*::after {
  box-sizing: inherit;
}
body {
  margin: 0;
}
.wave-container {
  background: #EEE;
}
.wave-container > svg {
  display: block;
  background: #fff;
}

body {
  margin: 0;
  font-family: system-ui, sans-serif;
}
.wave-container {
  position: relative;
  background: #09F;
  color: #FFF;
  text-align: center;
  overflow: hidden;
}
h1 {
  font-size: 5rem;
  margin: 7rem 1.25rem 2.5rem 1.25rem;
}
p {
  font-size: 1.5rem;
  margin: 0 1.25rem 5rem 1.25rem;
}

@keyframes animateWave {
  0% {
    transform: scale(1,0);
  }
  100% {
    transform: scale(1,1);
  }
}
.wave-container > svg {
  display: block;
  transform-origin: bottom;
  animation: animateWave 1000ms cubic-bezier(0.23, 1, 0.32, 1) forwards;
}

	</style>

</head>

<body>


<div class="wave-container">
  <h1>Hello, world!</h1>
  <p>Check out my awesome waves!</p>
  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="#0099ff" fill-opacity="1" d="M0,192L48,170.7C96,149,192,107,288,85.3C384,64,480,64,576,106.7C672,149,768,235,864,266.7C960,299,1056,277,1152,234.7C1248,192,1344,128,1392,96L1440,64L1440,0L1392,0C1344,0,1248,0,1152,0C1056,0,960,0,864,0C768,0,672,0,576,0C480,0,384,0,288,0C192,0,96,0,48,0L0,0Z"></path></svg>
</div>






<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>

<script type="text/javascript">

</script>


</body>
</html>