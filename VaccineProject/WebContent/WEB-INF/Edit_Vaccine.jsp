<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
        integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
        integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
<title>Edit Vaccine</title>
</head>
<body>

<form action="Edit_Vaccine" method="post">
<input type="hidden" name="id" value="${vaccine.id}" class="form-control">
Name: <input type="text" name="name" value="${vaccine.name}" class="form-control"> <br>
Doses Required: <select name= "doses" value="${vaccine.doses}" class="form-control"> 
					<option value="1">1</option>
					<option value="2">2</option> 
				</select> <br>

Days Between Doses: <input type="text" name="dose_days" value="${vaccine.dose_days}" class="form-control"> <br>
<button class="btn btn-default"> Save </button>
</form>

</body>
</html>