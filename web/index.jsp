<%-- 
    Document   : index
    Created on : Oct 20, 2018, 3:59:03 PM
    Author     : josedg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image Gallery</title>
        <link href="css/style.css" rel="stylesheet">
    </head>
    <h1>Image gallery</h1>
        <form method="post" action="ImageManagerServlet" enctype="multipart/form-data">
            <p>Select file to upload:</p>
            <input type="file" name="uploadFile" /><br>
            <input type="submit" value="Upload" />
        </form>
</html>
