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
	<h1 style="background-color: hsl(230, 55%, 25%); color: aliceblue;">MockVet Clinic Pet Database <br/>Dog Entry Editing Page</h1>
</header>
<section>
<h2>Please edit name of dog, gender, breed, owner's name, and/or primary vetenerian's name:</h2>
<form action="editDogServlet" method="post">
<input type="hidden" name="id" value="${dogToEdit.id}">
Enter/Update Dog's name: <input type="text" name="dogNameInput" value="${dogToEdit.name}"><br />
<br>
Current breed listed as:  ${dogToEdit.breed} <br />
<br>
<!-- <input list="breed" name="breedInput" value="${dogToEdit.breed}"><br />
<br> -->
Enter/Update dog's breed: <!-- <input list="breed" name="breedInput">-->
<!-- Enter/Update dog's breed: <input list="breed" name="breedInput" value="${dogToEdit.breed}">-->
	<!-- <label for="breed">Enter/Update dog's breed:</label> -->
	<!-- <datalist id="breed" >-->
	<select id="breed" name="breedInput" >
		<option value="${dogToEdit.breed}">${dogToEdit.breed}</option>
		<!-- <option>--select breed--</option></option> -->
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
	</select>
<br />
	<!-- </datalist> <br /> 
 Added drop down menu for breed input <input type="text" id="breed" name="breedInput" size="25" required="required"> 
 -->

<!-- Decide between two below versions of gender input 
Current gender listed as:  ${dogToEdit.gender} <br />
<br>
Enter/Update dog's gender:<input list="gender" name="genderInput" required="required" value="${dogToEdit.gender}">
<datalist id="gender">
	<option >-select gender-</option>
	<option value="male">
	<option value="female">
</datalist><br/> -->
<br /> 
Current gender listed as:  ${dogToEdit.gender} <br />
<!--Current gender listed as:<input name="genderInput" value="${dogToEdit.gender}"><br/> -->
<br>
Enter/Update dog's gender: <!-- <input name="genderInput" value="${dogToEdit.gender}"> -->
	<!-- <label for="gender">Enter/Update dog's gender:</label> -->
	<select id="gender" name="genderInput">
		<!-- <option >-select gender-</option> -->
		<option value="${dogToEdit.gender}">${dogToEdit.gender}</option>
		<option value="male">male</option>
		<option value="female">female</option>
	</select><br />
<br>
Enter/Update dog owner's name: <input type="text" id="ownerName" name="ownerNameInput" size="25" required="required" value="${dogToEdit.ownerName}"><br />
<br>

<!-- Decide between two below versions of vet name input -->
Enter/Update primary vetenerian's name: <input list="vetName" name="vetNameInput" required="required" value="${dogToEdit.primaryVet}">
<datalist id="vetName">
	<option value="Dr. Caterson">
	<option value="Dr. Doghouser">
	<option value="Dr. Hamsterville">
</datalist><br />
<br>
<!--
	<label>Enter primary vetenerian's name:</label><br> 
	<input type="radio" id="doghouser" name="vetNameInput" value="Dr. Doghouser">
	<label for="doghouser">Dr. Doghouser</label><br>
	<input type="radio" id="caterson" name="vetNameInput" value="Dr. Caterson">
	<label for="caterson">Dr. Caterson</label><br>
	<input type="radio" id="hamsterville" name="vetNameInput" value="Dr. Hamsterville">
	<label for="hamsterville">Dr. Hamsterville</label><br><br>
 -->	
	
	<input type="submit" value="Save Edited Dog Entry to Database"/>	
</form>
</section>
</body>
</html>