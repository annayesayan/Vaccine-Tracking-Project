<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
        integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
        integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
<title>New Patient</title>
</head>
<body>

<form method="post">
Patient <input type="text" name="name" class="form-control"> <br>
Vaccine <select name= "vacc_names" class="form-control">
				<c:forEach items="${table}" var="vaccine">
				<c:if test="${vaccine.doses_left!='0'}">
					<option> ${vaccine.name}</option>	
				</c:if>					
				</c:forEach>
				</select><br>

<button class="btn btn-default"> Add </button>
</form>

</body>
</html>