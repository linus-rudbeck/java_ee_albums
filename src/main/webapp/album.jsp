<%@ page import="se.distansakademin.java_ee_albums.models.Album" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% var album = (Album) request.getAttribute("album"); %>
<!DOCTYPE html>
<html>
<head>
    <title>Albums</title>

    <style>
        h1{
            text-align: center;
        }
        body{
            background: lightblue;
        }
    </style>

</head>
<body>
<h1><%= album.getName() %></h1>

<p>
    <b>Album id:</b>
    <%= album.getId() %>
</p>

<hr>

<form action="?id=<%= album.getId() %>&action=delete" method="post">

    <button type="submit">Delete</button>

</form>


</body>
</html>