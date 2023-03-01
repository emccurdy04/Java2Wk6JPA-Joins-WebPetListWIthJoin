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
	<h1 style="background-color: hsl(230, 55%, 25%); color: aliceblue;">MockVet Clinic Pet Database -<br/>
	Dogs Table List:</h1>
</header>
<section>
<h3>Dog's Name | Breed | Gender | DOB | Owner's Name | Vet's Name</h3>
<form method="post" action="navigationServlet">
<table>
<c:forEach items="${requestScope.allDogs}" var="currentdog">
<tr>
	<td><input type="radio" name="id" value="${currentdog.id}"></td>
	<td>${currentdog.name}</td>
	<td>${currentdog.breed}</td>
	<td>${currentdog.gender}</td>
	<td>${currentdog.dogDOB}</td>
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

<a href="viewPetListServlet">View Pet list for owner</a><br />
<a href="viewAllDogsServlet">View all dogs in database</a><br />
<a href="viewAllOwnersServlet">View all owners in database</a><br />
<br />
<!-- ???remove link below? Or change to only adding pet to existing pet owner's listOPets???-->
<a href="addPetsForPetListServlet">Create a new PetList</a><br />
<!-- ??? remove link below - as currently only need index.html link to add pet & pet owner 
together since no reason to create pet owner w/o an associated pet???-->
<a href="addOwnerServlet">Add a new owner to database</a><br />
<!-- <a href="index.html">Add a new pet to database</a> -->
<br>
<!-- ???change below so addDogServlet adds dog if existing owner but does index.html direct if both 
owner and dog are new? -->
<a href="addDogServlet">Add a new dog to database</a><br />
<a href="index.html">Add a new pet and a new owner to database</a>
</section>
</body>
</html>