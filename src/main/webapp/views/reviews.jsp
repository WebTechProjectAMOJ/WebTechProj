<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dbconnection.DbConnection"%>
<%@ page import="org.bson.Document"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reviews</title>
</head>
<body>
<h1>Comments</h1>
<%
    List<Document> reviews = DbConnection.getReviews();
    for (Document review : reviews) {
        String username = review.getString("username");
        int rating = review.getInteger("rating", 0);
        String comment = review.getString("comment");
        String date = review.getDate("date").toString();
%>
<div class="review">
    <div class="user">Commenter: <%=username %></div>
    <div class="star-rating">Stars: <% for (int i = 0; i < rating; i++) { %>&#9733;<% } %></div>
    <div class="comment"><%=comment %></div>
    <div class="date"><%=date %></div>
</div>
<%
    }
%>
</body>
</html>
