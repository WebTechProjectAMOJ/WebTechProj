<%--
  Created by IntelliJ IDEA.
  User: margotrichez
  Date: 06/05/2024
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="popup_window">
    <div class="header_row">
        <div class="box_container" style="cursor: auto;">
            <div class="box">
                <img src="${currentRestaurant.getIcon_url()}">
            </div>
            <jsp:include page="../components/rating_stars.jsp" />
        </div>
        <div class="header_text_container">
            <h2>${currentRestaurant.getName()}</h2>
            <h3> 5kms  <u>more info.</u> </h3>
        </div>
        <div class="corner_popup">
            <button  type="button" class="confirm_button" style="background-color: lightgrey; border-color: black;" onclick="closeMenu()">Close</button>
<%--            <div class="search_bar">--%>
<%--                <div class="search_block">--%>
<%--                    <a> Search item in restaurant</a>--%>
<%--                </div>--%>
<%--            </div>--%>
        </div>
    </div>
    <div class="popup_window">
        <jsp:include page="../components/section_grid_scroll.jsp" />
    </div>
</div>

<script>
    function closeMenu() {
        history.back();
    }
</script>