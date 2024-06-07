<%--
  Created by IntelliJ IDEA.
  User: margotrichez
  Date: 02/05/2024
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%--${user.getAddress()[0].get("name")}--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="header_user_row">
    <div class="search_container">
        <div class="address_bar">
            <a> Selected Address: </a>
            <div class="search_block"><select name="pets" id="address-select">
                <c:forEach items="${user.getAddress()}" var="item" varStatus="loop">
                    <option value="${loop.index}">${item.get("name")}
                    </option>
                </c:forEach>
            </select></div>
        </div>
        <div class="search_bar">
            <div class="search_block">
                <input id="search-input" name="search-term" placeholder="Search for restaurant or food"/>
            </div>
            <button>SEARCH</button>
        </div>
    </div>
    <img src="${pageContext.request.contextPath}/assets/food-dispatch-logo.png" alt="logo">
</div>
