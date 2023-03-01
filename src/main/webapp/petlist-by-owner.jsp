<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java II Week 6 adding Join to WebPetListWithJoin Project</title>
</head>
<body>
<header>
	<h1 style="background-color: hsl(230, 55%, 25%); color: aliceblue;">MockVet Clinic Pet Database -<br/>
	PetList by Owner:</h1>
</header>
<section>
<form action="petListNavigationServlet" method="post">
<table>
<!-- ??? If change this file - decide if display all pet owners w/ their listOfPets or just
one specified pet owner if do latter then add way to display owner's listOfPets to 
petowner-list.jsp??? -->
<!-- ???Change this jsp file completely? Display pet owner then their listOfPets arrayList -
with ability to select a pet or an option so only can add dog to an existing petowner's
listOfPets but can't delete a list since only should delete if both petowner & pet are removed
from database - to prevent orphan records???  -->

<!-- 
<input type="hidden" name="id" value="${ownerToEdit.id}"> -->

<c:forEach items="${requestScope.listOfPets}" var="currentpet">
<tr><td><input type="radio" name="id" value="${currentpet.id}"></td>
<td><h2>PetList ID: ${currentpet.petListId} ${currentpet.toString()}</h2></td></tr>
<tr><td colspan="3">Owner: ${currentpet.petowner.ownerName}</td></tr>
<c:forEach var="listVal" items="${currentpet.listOfPets}">
	<tr><td></td>
	<td colspan="3">${listVal.dog}, ${listVal.petOwner}</td></tr>
</c:forEach>
</c:forEach>
</table>
<input type="submit" value="edit" name="doThisToPetList">
<input type="submit" value="delete" name="doThisToPetList">
<input type="submit" value="add" name="doThisToPetList">
</form>
<br>
<a href="viewPetListServlet">View Pet list for owner</a><br />
<a href="viewAllDogsServlet">View all dogs in database</a><br />
<a href="viewAllOwnersServlet">View all owners in database</a><br />
<br />
<!-- ???remove link below? Or change to link to only add pet to existing pet owner's listOfPets???-->
<a href="addPetsForPetListServlet">Create a new PetList</a><br />
<!-- ??? remove link below - as currently only need index.html link to add pet & pet owner 
together since no reason to create pet owner w/o an associated pet???-->
<a href="addOwnerServlet">Add a new owner to database</a><br />
<!-- <a href="index.html">Add a new pet to database</a> -->
<!-- ???change below so addDogServlet adds dog if existing owner but does index.html direct if both 
owner and dog are new? -->
<a href="addDogServlet">Add a new dog to database</a><br />
<a href="index.html">Add a new pet and a new owner to database</a>
</section>
</body>
</html>