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
  
  font-size: 10px;
  
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

<c:forEach begin="0" end="${row-1}" varStatus="i">

<tr>

<c:forEach begin="0" end="${row-1}" varStatus="j">


<c:if test="${i.index*row+j.index lt row}">
<td id="${i.index*row+j.index}" style="background: white"><font style="color: black">${i.index*row+j.index}</font></td>
</c:if>

<c:if test="${i.index*row+j.index ge row && j.index%row == 0}">
<td id="${i.index*row+j.index}" style="background: white"><font style="color: black">${i.index*row+j.index}</font></td>
</c:if>

 
<c:if test="${i.index*row+j.index ge row+1 && j.index%row!=0}">
<td id="${i.index*row+j.index}" style="background: white"><font style="color: white">${i.index*row+j.index}</font></td>
</c:if>


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


<form style="float: left" action="GameServlet">
<input size="4" type="text" name="input">
<input type="submit" value="Send">
</form>

<button  onclick="start()">Start</button>
<button  onclick="stop()">Stop</button>


<button style="float: right" onclick="exit()">Exit</button>


<script>

var table=${pattern};
var i=0;
var run=${run};
var go;
var bad=${badvalue};



while(i<table.length) {
	
	
	if(table[i]) {
		
		document.getElementById(i).style.backgroundColor="#ffbf00";
		
	}
	
	i++;
}


function running() {
	
	
	window.location.reload();
	
}

if(run) {
	
	go=setInterval(running, 600);
}

function start() {
	
	document.getElementById("start").submit();
}


function stop() {
	
	clearInterval(go);
}

if(bad){
	
	alert("Invalid input value!");
}

function exit() {
	
	if(confirm("Would you like to exit?")){

       window.close();

	}    

}
</script>


</body>
</html>