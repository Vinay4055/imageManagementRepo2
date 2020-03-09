<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>

function editImage(count,divId,id,name,size,image){
	console.log("function ");
	

	
	document.getElementById(divId).innerHTML="<td>"+count+"</td><td><form method=\"POST\" action=\"/project/updateImageName\"><input type=\"text\" name=\"name\" value="+name+" /><input type=\"hidden\" name=\"imageId\" value="+id+" /><button type=\"reset\" value=\"Reset\">Cancel</button><input type=\"submit\" value=\"Submit\"/></form></td><td>"+size+"</td><td><img alt=\"Image\" src=\"data:image/jpg;base64,"+image+"\"width=\"100\" height=\"150\" /><form action=\"/session/updateImage\" method=\"POST\" enctype=\"multipart/form-data\"><input type=\"hidden\" name=\"imageId\"  value="+id+"><input type=\"file\" name=\"myfile\" /><button type=\"reset\" value=\"Reset\">Cancel</button><input type=\"submit\" value=\"Submit\"/></form></td><td></td>";
	
}


</script>
<script>
function calculateSizeInMb(number){
	
	
}
</script>




</head>
<style>
tr, td {
	padding: 10px;
	border: 1px solid black;
	border-collapse: collapse;
	white-space: nowrap;
}

table, th {
	padding: 10px;
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
<body>
<center>
<div style="border-style:solid;width:75%">

	<input type=button onClick="location.href='/logout'" value='Logout'
		style="float: right;">
	<br>
	
	<table style="padding-left:20px">

		<tr style="padding-right: 200px">
			<center>
				<h1>Image Management Utility</h1>
			</center>
		</tr>
		<hr style="border-style:inset;border-width:1px">
		<tr>
			Please Select an image file to upload (Max Size 1MB)
			<br>
			<form method="POST" action="/project/upload"
				enctype="multipart/form-data">
				<input type="file" name="file"/><br />
				<button type="reset" value="Reset" style="padding-right:2px;">Cancel</button>&nbsp;&nbsp;&nbsp;

				<input type="submit" value="Submit" style="padding-right:2px;" />

				${ImageSizeExceed } <br>
				${TotalImageSizeExceed } <br>
				${exception }
			</form>

		</tr>
		<tr>
			<br>
			<br>
			<b style="flost:left">Uploaded Images</b>

		</tr>
	</table>
	<br>

	<c:if test="${fn:length(images) gt 0}">
		<table>
			<tr>
				<th>S.No</th>
				<th>Name</th>
				<th>Size</th>
				<th>Preview</th>
				<th style="width:100px">Actions</th>
			</tr>





			<c:set var="count" value="0" scope="page" />



			<c:forEach var="object" items="${ images }" varStatus="loop">
			
				<c:set var="count" value="${count + 1}" scope="page" />
					<tr id='image${count }'>
					<td>
						<c:out value="${count}" />
					</td>
					
					
					<td><c:out value="${object.name}" /></td>
					<td><c:out value="${object.size }Mb"></c:out></td>
					<td><img alt="Image" src="data:image/jpg;base64,${object.base64Image}"
						width="100" height="150" /></td>
					<td style="width:100px">
					<form:form  action="/project/updateImage" method="POST">
					<input type="hidden" name="imageId" value="${ object.imageId }">
					
					<button type="submit" class="btn btn-default btn-sm">
          		<span class="glyphicon glyphicon-edit"></span> 
        </button>
     
        </form:form>
					<form:form  action="/project/deleteImage" method="POST">
					<input type="hidden" name="imageId" value="${ object.imageId }">
					
					<button type="submit" class="btn btn-default btn-sm">
          <span class="glyphicon glyphicon-remove"></span> 
        </button>
       
        </form:form>
   		</td>
				</tr>
			</c:forEach>

		</table>
		Total Size Of Uploades Images = ${totalSizeOfUploadedImages}Mb
	</c:if>
	<c:if test="${fn:length(images) eq 0}">
		<c:out value="No Image Uploaded Yet" />
	</c:if>

	<br>
	<br>
</div>
</center>
</body>
</html>