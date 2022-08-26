<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
        integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
        integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
<title>Patients</title>
</head>
<body>

<a href="New_Patient"> New Patient</a> 

<table class="table table-bordered">
    <thead>
      <tr>
        <th> Id </th>
		<th> Name </th>
		<th> Vaccine </th>
		<th> 1st Dose</th>
		<th> 2nd Dose </th> 
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${Ptable}" var="patient">
	<tr> 
		<td> ${patient.id} </td>
		<td> ${patient.name} </td>
		<td> ${patient.vaccine_name} </td>
		<td> ${patient.fst_dose_date} </td>
		<c:choose>
    		<c:when test="${patient.scd_dose_date=='Received'}">
        	<td> <a href="Received?id=${patient.id}"> Received</a>  </td>     
    		</c:when>    
    		<c:otherwise>
        	<td> ${patient.scd_dose_date} </td>  
            </c:otherwise>
		</c:choose>		
	</tr>
	</c:forEach>
    </tbody>
</table>
 <a href="Vaccine_Table"> Vaccines</a>

</body>
</html>