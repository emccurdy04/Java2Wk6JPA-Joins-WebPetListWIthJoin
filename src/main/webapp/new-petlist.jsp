<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java II Week 6 adding join to WebPetList project</title>
</head>
<body>
<header>
	<h1 style="background-color: hsl(230, 55%, 25%); color: aliceblue;">MockVet Clinic Pet Database -<br/>
	Edit/add pet/dog to owner's pet list form:</h1>
</header>
<section>
<!--  <form action="createNewPetListServlet" method="post">-->
<form method="post" action="addPetsForPetListServlet" >
<!-- Not sure if need this jsp -->
<h2>This is the new-petlist.jsp - Input entered in this form when 
submitted is directed to addPetsForPetListServlet & used to 
add a new pet to owners pets list, select pet to list from their petlist &
display current pet list for specified owner</h2>

<input type="hidden" name="id" value="${ownerToEdit.ownerId}">
<table>
<tr><td><h2>Owner Name: ${ownerToEdit.ownerName}</h2></td></tr>
	<tr><td><h3>Current Pet List:</h3></td></tr>
		<c:forEach var="listVal" items="${ownerToEdit.listOfPets}">
		<tr><td></td><td colspan="3">
		PetName: ${listVal.name} | Breed: ${listVal.breed} | Gender: ${listVal.gender} 
		| DOB: ${listVal.dogDOB} | Primary Vet: ${listVal.primaryVet} </td></tr>
		</c:forEach>
</table>
<!-- Enter/Update Owner's name: <input type="text" name="ownerNameInput" value="${ownerToEdit.ownerName}"><br />
<br> 
<br>
Enter/Update Owner's name: <input type="text" id="ownerName" name="ownerNameInput" size="25" required="required" value="${ownerToEdit.ownerName}"><br />
<br>-->
<!-- ? if can display owners current listOfPets for user to select which pet want to edit 
or if want to add a pet to owners listOfPets -->
<!-- ???If able to somehow also edit dog's in owners listOfPets might need stuff from edit-dog.jsp? -->
<input type="hidden" name="id" value="${ownerToEdit.ownerId}">
<table>
<!-- <tr><td><h2>Owner Name: ${ownerToEdit.ownerName}</h2></td></tr> -->
<tr><td><h3>Select dog from current pet list to delete:</h3></td></tr>
<c:forEach items="${ownerToEdit.listOfPets}" var="currentpet">
<tr><td><input type="radio" name="dogId" value="${currentpet.id}"></td>
<!-- <td><h3>Pets:</h3></td> --></tr>
<tr><td colspan="3">
PetName: ${currentpet.name} | Breed: ${currentpet.breed} | Gender: ${currentpet.gender} 
| DOB: ${currentpet.dogDOB} | Primary Vet: ${currentpet.primaryVet} | Owner: ${currentpet.ownerName}</td></tr>
</c:forEach> 
</table> 
<br />
<input type="submit" value="delete" name="doThisToPetList"> 

</form>

<form method="post" action="addPetsForPetListServlet" >
<input type="hidden" name="id" value="${ownerToEdit.ownerId}">
<h3>Input information for new dog to add to current pet owner's pet list: </h3>
<!--Enter new Dog's name: <input type="text" name="dogNameInput" value="${dogToEdit.name}"><br />
<br> ??? change  from dogToEdit to dogToAdd??? value="${dogToAdd.breed}" <input list="breed" name="breedInput" value="${dogToEdit.breed}"><br />-->
<br> 
<label for="dogName">Enter new dog's name:</label>
	<!-- <input type="text" id="dogName" name="dogNameInput" size="25" required="required">  -->
	<input type="text" id="dogName" name="dogNameInput" size="25">
	<br>
	<br>
	<label for="gender">Enter dog's gender:</label>
	<select id="gender" name="genderInput">
		<option >-select gender-</option>
		<option value="male">male</option>
		<option value="female">female</option>
	</select><br>
	<br>
	<label for="breed">Enter dog's breed:</label>
	<select id="breed" name="breedInput" required="required">
		<option >-select breed-</option>
		<option value="Airedale Terrier">Airedale Terrier</option>
		<option value="Akita">Akita</option>
		<option value="Alaskan Malamute">Alaskan Malamute</option>
		<option value="American Bulldog">American Bulldog</option>
		<option value="Basset Hound">Basset Hound</option>
		<option value="Beagle">Beagle</option>
		<option value="Bichon Frise">Bichon Frise</option>
		<option value="Bloodhound">Bloodhound</option>
		<option value="Boxer">Boxer</option>
		<option value="Chihuahua">Chihuahua</option>
		<option value="Cocker Spaniel">Cocker Spaniel</option>
		<option value="Collie">Collie</option>
		<option value="Corgi">Corgi</option>
		<option value="Dachshund">Dachshund</option>
		<option value="Dalmatian">Dalmatian</option>
		<option value="Doberman Pinscher">Doberman Pinscher</option>
		<option value="German Shepherd">German Shepherd</option>
		<option value="Golden Doodle">Golden Doodle</option>
		<option value="Golden Retriever">Golden Retriever</option>
		<option value="Great Dane">Great Dane</option>
		<option value="Greyhound">Greyhound</option>
		<option value="Irish Setter">Irish Setter</option>
		<option value="Irish Wolfhound">Irish Wolfhoun</option>
		<option value="Labrador Retriever">Labrador Retriever</option>
		<option value="Lhasa Apso">Lhasa Apso</option>
		<option value="Maltese">Maltese</option>
		<option value="Mastiff">Mastiff</option>
		<option value="Mixed-Breed">Mixed-Breed</option>
		<option value="Newfoundland">Newfoundland</option>
		<option value="Norfolk Terrier">Norfolk Terrier</option>
		<option value="Pekingese">Pekingese</option>
		<option value="Pointer">Pointer</option>
		<option value="Pomeranian">Pomeranian</option>
		<option value="Poodle">Poodle</option>
		<option value="Pug">Pug</option>
		<option value="Rottweiler">Rottweiler</option>
		<option value="Saint Bernard">Saint Bernard</option>
		<option value="Samoyed">Samoyed</option>
		<option value="Shar-Pei">Shar-Pei</option>
		<option value="Shetland Sheepdog">Shetland Sheepdog</option>
		<option value="Shiba Inu">Shiba Inu</option>
		<option value="Shih Tzu">Shih Tzu</option>
		<option value="Siberian Husky">Siberian Husky</option>
		<option value="Terrier">Terrier</option>
		<option value="West Highland Terrier">West Highland Terrier</option>
		<option value="Yorkshire Terrier">Yorkshire Terrier</option>
		<option value="Terrier">Terrier</option>
		<option value="Unknown">Unknown</option>
	</select><br /> 
	<br /> 
	Enter dog's DOB: <input type="text" name="month" placeholder="mm" size="4"> 
<input type="text" name="day" placeholder="dd" size="4">, 
<input type="text" name="year" placeholder="yyyy" size="4"><br />	
	<br>
	<!-- Decide which of below methods want to use for dog owner's name input?
 <label for="ownerName">Enter dog owner's name:</label>
	<input type="text" id="ownerName" name="ownerNameInput" size="25" required="required"><br> 
	<input type="text" id="ownerName" name="ownerNameInput" size="25" ><br> -->
Confirm/Update dog owner's name: <input type="text" id="ownerName" name="ownerNameInput" size="25" required="required" value="${ownerToEdit.ownerName}"><br />
<br>
	
	<label>Enter primary vetenerian's name:</label><br> 
	<input type="radio" id="doghouser" name="vetNameInput" value="Dr. Doghouser">
	<label for="doghouser">Dr. Doghouser</label><br>
	<input type="radio" id="caterson" name="vetNameInput" value="Dr. Caterson">
	<label for="caterson">Dr. Caterson</label><br>
	<input type="radio" id="hamsterville" name="vetNameInput" value="Dr. Hamsterville">
	<label for="hamsterville">Dr. Hamsterville</label><br><br>

<!-- ??? if have delete pet from pet list option here ? will it work??? doesn't seem to???
<input type="submit" value="edit" name="doThisToPetList"> 
<input type="submit" value="delete" name="doThisToPetList">--> 
<input type="submit" value="add" name="doThisToPetList">

</form>

<br>
<a href="viewPetListServlet">View Pet list for owner</a><br />
<a href="viewAllDogsServlet">View all dogs in database</a><br />
<a href="viewAllOwnersServlet">View all owners in database</a><br />
<br />
<!-- ??? remove link below - as currently only need index.html link to add pet & pet owner 
together since no reason to create pet owner w/o an associated pet???-->
<a href="addOwnerServlet">Add a new owner to database</a><br />
<!-- ???remove link below? -->
<a href="addPetsForPetListServlet">Add a new pet to owner's pet list</a><br />
<br>
<!-- <a href="index.html">Add a new pet to database</a> -->
<!-- ???change below so addDogServlet adds dog if existing owner but does index.html direct if both 
owner and dog are new? -->
<a href="addDogServlet">Add a new dog to database</a><br />
<a href="index.html">Add a new pet and a new owner to database</a>
</section>
</body>
</html>