<%@ page import="se.distansakademin.java_ee_albums.models.Album" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Albums</title>

    <style>
        h1 {
            text-align: center;
        }

        body {
            background: lightblue;
        }
    </style>

</head>
<body>
<h1><%= "Albums!" %>
</h1>

<form method="post" action="">
    <label for="name-label-id">Album name</label>
    <input type="text" name="album-name" id="name-label-id">

    <button type="submit">Save</button>
</form>

<hr>

<% for (Album album : ((List<Album>) request.getAttribute("albums"))) { %>

<p>
    <a href="?id=<%= album.getId() %>">
        <%= album.getName() %>
    </a>
</p>

<% } %>


</body>
</html>