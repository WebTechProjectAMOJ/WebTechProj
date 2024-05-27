<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reviews</title>
    <link rel="stylesheet" type="text/css" href="path/to/your/css/file.css">
</head>
<body>
<div class="reviews_container">
    <h1>Comments</h1>
    <c:forEach var="rating" items="${ratings}">
        <div class="review">
            <div class="user">Commenter: <c:out value="${rating.consumerId}" /></div>
            <div class="star-rating">
                Stars:
                <c:forEach var="i" begin="1" end="${rating.rating}">
                    &#9733;
                </c:forEach>
            </div>
            <div class="comment"><c:out value="${rating.feedback}" /></div>
            <div class="date"><c:out value="${rating.id}" /></div> <!-- Assuming `id` contains date info, adjust as needed -->
        </div>
    </c:forEach>
</div>
</body>
</html>
