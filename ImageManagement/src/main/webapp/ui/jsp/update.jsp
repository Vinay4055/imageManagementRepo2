<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

			<br>
			
			<c:set var="imageId" value="${imageId }" scope="session" />
			<form method="POST" action="/project/doUpdate"
				enctype="multipart/form-data">
				<center>
				<div style="border:1px solid black;border-style:double;width:50%;padding:15px" >
				<b style="color:RED">Edit Image Section</b></br>
				<b>Please Select New Image File to Upload (Max Size 1MB)</b><br><br>
				
				<input type="file" name="file" /><br><br>
				<button type="reset" value="Reset" >Cancel</button>

				<input type="submit" value="Submit"  />
				${exception }
				</div>

	</center>
			</form>
			
</body>
</html>