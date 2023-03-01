<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java II Week 6 adding join to WebPetList Project</title>
</head>
<body>
<header>
	<h1 style="background-color: hsl(230, 55%, 25%); color: aliceblue;">MockVet Clinic Pet Database -<br/>
	PetOwner Table List:</h1>
</header>
<section>
<h2>Owners: <!--Dog's Name | Breed | Gender | DOB | Owner's Name | Vet's Name --></h2>
<form method="post" action="petOwnerNavigationServlet">
<table>
<c:forEach items="${requestScope.allOwners}" var="currentowner">
<tr><td colspan="2"></td></tr>
<tr><td colspan="2"></td></tr>
<tr><td colspan="2"></td></tr>
<tr><td><input type="radio" name="id" value="${currentowner.ownerId}"></td>
	<td><h3>Owner Name: ${currentowner.ownerName}</h3></td></tr>
	<tr><td><h3>Pet List:</h3></td></tr>
		<c:forEach var="listVal" items="${currentowner.listOfPets}">
		<tr><td colspan="2">
		PetName: ${listVal.name} | Breed: ${listVal.breed} | Gender: ${listVal.gender} 
		| DOB: ${listVal.dogDOB} | Primary Vet: ${listVal.primaryVet} </td></tr>
		</c:forEach>
	<!-- <td>${currentdog.name}</td>
	<td>${currentdog.breed}</td>
	<td>${currentdog.gender}</td>
	<td>${currentdog.dogDOB}</td>
	<td>${currentowner.ownerName}</td>
	<td>${currentdog.primaryVet}</td> 
</tr>-->
</c:forEach>
<tr><td colspan="2"></td></tr>
</table>
<br />
<!-- 
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
</table> -->

<!-- ???Change below text? Change to not allow delete of owner or warn if delete owner will also
delete associated pets from database??? -->
<p>Please select which pet owner you wish to edit or delete from the pet database petowner table.<br />
Then, select button below to either edit or delete the selected pet owner. <br />
Otherwise, press add button if you wish to return to the add owner entry page.</p>

<!-- ???have 'edit' option be where petOwnerNavigationServlet directs to way to display & 
select a pet to edit in selected petOwner's listOfPets??? -->
<input type="submit" value="edit" name="doThisToOwner">
<input type="submit" value="delete" name="doThisToOwner">
<!-- ???have this add be where petOwnerNavigationServlet directs to adding new dog/pet to the
selected petOwner's listOfPets and dog table???-->
<input type="submit" value="add" name="doThisToOwner">
</form>
<br>
<a href="viewPetListServlet">View Pet list for owner</a><br />
<a href="viewAllDogsServlet">View all dogs in database</a><br />
<a href="viewAllOwnersServlet">View all owners in database</a><br />
<br>
<!-- ???remove link below? Or if change to just allow adding pet to existing petowner's listOfPets-->
<a href="addPetsForPetListServlet">Link to add new pet to existing petowner's listOfPets></a><br />
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