<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

<title>Game of Life</title>

<style>

body {

text-align: center;
background-color: powderblue;
}

table, td {
  
  border: 1px solid black;
 	
  border-collapse: collapse;
  
}
 
table {

width:800px;
height:800px;

}


</style>
</head>


<body>

<h1 style="color:white">${counter}</h1>

<h3 style="color:#ffbf00">${patternName}</h3>

<table align="center">

<c:forEach begin="0" end="${row}" varStatus="i">

<tr>

<c:forEach begin="0" end="${row}" varStatus="j">
<td id="${i.index*50+j.index}" style="background: white"></td>
</c:forEach>

</tr>
</c:forEach>
</table><br><hr>


<form style="float: left" action="GameServlet">

<select name="pattern">

<c:forEach items="${names}" var="name">
<option  value="${name}">${name}</option>
</c:forEach>

</select>

<input type="submit" value="Load">
</form>


<form id="start" action="GameServlet">

</form>


<button  onclick="start()">Start</button>
<button  onclick="stop()">Stop</button>

<button style="float: right" onclick="exit()">Exit</button>

<script>


var table=${pattern};
var run=${run};
var go;
var i=0;


	
	while(i<table.length) {
		
		
		
		if(table[i]) {
			
			document.getElementById(i).style.backgroundColor="#ffbf00";
			
		}
		
		i++;
	}
	
		
	
	function running() {
		
		i=0;
		window.location.reload();
		
	}
	

	if(run) {
		
		go=setInterval(running, 400);
	}
	
	function start() {
		
		document.getElementById("start").submit();
	}
	
	
	function stop() {
		
		clearInterval(go);
	}
	
	function exit() {
		
		if(confirm("Would you like to exit?")){

            window.close();

		}    
	}
</script>


</body>
</html>