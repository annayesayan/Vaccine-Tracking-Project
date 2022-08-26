<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
        integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
        integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
<title>Vaccines</title>
</head>
<body>

<a href="New_Vaccine"> New Vaccine</a> |
<a href="New_Doses"> New Doses</a>
<table class="table table-bordered">
    <thead>
      <tr>
        <th> Vaccine </th>
		<th> Doses Required </th>
		<th> Days Between Doses </th>
		<th> Total Doses Received </th>
		<th> Total Doses Left </th> 
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${table}" var="vaccine">
	<tr> 
		<td> ${vaccine.name} </td>
		<td> ${vaccine.doses} </td>
		<td> ${vaccine.dose_days} </td>
		<td> ${vaccine.doses_recieved} </td>
		<td> ${vaccine.doses_left} </td> 
		<td> <a href="Edit_Vaccine?id=${vaccine.id}"> Edit </a></td>
	</tr>
	</c:forEach>	
    </tbody>
  </table>
<a href="Patient_Table"> Patients</a>

</body>
</html>