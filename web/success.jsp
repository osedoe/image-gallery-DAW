<%-- 
    Document   : message
    Created on : 09-oct-2018, 10:16:41
    Author     : Jose Diaz Gonzalez
--%>

<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image Gallery</title>
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        
        <h1>Image Gallery</h1>
        <p><strong>System info: </strong>${requestScope.message}</p>
        <br>
        <p><i>Go back to see all downloaded images</i></p>
        <form method="get" action="ImageManagerServlet">
            <input type="submit" value="Go back" />
        </form>

    </body>
</html>
