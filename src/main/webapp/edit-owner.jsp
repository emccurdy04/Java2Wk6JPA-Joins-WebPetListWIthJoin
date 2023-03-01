<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java II Week 6 add Join to WebPetList Project</title>
</head>
<body>
<header>
	<h1 style="background-color: hsl(230, 55%, 25%); color: aliceblue;">MockVet Clinic Pet Database:<br/>
	Owner Entry Editing Page</h1>
</header>
<section>
<!-- Deciding whether can edit more/add pet to owners listOfPets arrayList -->
<h2>Please edit/update owner's name and/or pet's information:</h2>

<form method="post" action="editOwnerServlet" >
<input type="hidden" name="id" value="${ownerToEdit.ownerId}">
<!-- Enter/Update Owner's name: <input type="text" name="ownerNameInput" value="${ownerToEdit.ownerName}"><br />
<br> -->
<br>
Enter/Update Owner's name: <input type="text" id="ownerName" name="ownerNameInput" size="25" required="required" value="${ownerToEdit.ownerName}"><br />
<br>
<!-- ? if can display owners current listOfPets for user to select which pet want to edit 
or if want to add a pet to owners listOfPets -->
<!-- ???If able to somehow also edit dog's in owners listOfPets might need stuff from edit-dog.jsp? -->
	
	<input type="submit" value="Save Edited Owner Entry to Database"/>	
</form>
</section>
</body>
</html>