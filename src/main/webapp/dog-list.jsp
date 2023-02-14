<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="Elizabeth McCurdy">
<title>Java 2 Week 5 WebPetList Project Assessment</title>
</head>
<body>
<header>
	<h1 style="background-color: hsl(230, 55%, 25%); color: aliceblue;">MockVet Clinic Pet Database -<br/>Dogs Table List:</h1>
</header>
<section>
<h3>Dog's Name | Breed | Gender | Owner's Name | Vet's Name</h3>
<form method="post" action="navigationServlet">
<table>
<c:forEach items="${requestScope.allDogs}" var="currentdog">
<tr>
	<td><input type="radio" name="id" value="${currentdog.id}"></td>
	<td>${currentdog.name}</td>
	<td>${currentdog.breed}</td>
	<td>${currentdog.gender}</td>
	<td>${currentdog.ownerName}</td>
	<td>${currentdog.primaryVet}</td>
</tr>
</c:forEach>
</table>

<p>Please select which dog you wish to edit or delete from the pet database dogs table.<br />
Then, select button below to either edit or delete the selected dog. <br />
Otherwise, press add button if you wish to return to the add dog entry page.</p>

<input type="submit" value="edit" name="doThisToDog">
<input type="submit" value="delete" name="doThisToDog">
<input type="submit" value="add" name="doThisToDog">

</form>
</section>
</body>
</html>