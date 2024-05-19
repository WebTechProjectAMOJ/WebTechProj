<%--
  Created by IntelliJ IDEA.
  User: fujintao
  Date: 06/05/2024
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mongodb.client.MongoClients"%>
<%@ page import="com.mongodb.client.MongoClient"%>
<%@ page import="com.mongodb.client.MongoDatabase"%>
<%@ page import="com.mongodb.client.MongoCollection"%>
<%@ page import="com.mongodb.client.FindIterable"%>
<%@ page import="org.bson.Document"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>reviews</title>
</head>
<body>
<h1>comments</h1>

<%
    MongoClient mongoClient = null;
    try {
        String uri = "mongodb://mongo:example@localhost:27017/";
        mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("yourdatabase");
        MongoCollection<Document> collection = database.getCollection("reviews");
        FindIterable<Document> reviews = collection.find().sort(new Document("date", -1));

        for (Document review : reviews) {
            String username = review.getString("username");
            int rating = review.getInteger("rating", 0);
            String comment = review.getString("comment");
            String date = review.getDate("date").toString();
%>
<div class="review">
    <div class="user">commenter: <%=username %></div>
    <div class="star-rating">stars: <% for (int i = 0; i < rating; i++) { %>&#9733;<% } %></div>
    <div class="comment"><%=comment %></div>
    <div class="date"><%=date%></div>
</div>
<%
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
%>
</body>
</html>
