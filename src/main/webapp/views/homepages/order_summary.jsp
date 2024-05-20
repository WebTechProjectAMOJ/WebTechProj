<%--
  Created by IntelliJ IDEA.
  User: margotrichez
  Date: 13/05/2024
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Summary</title>

    <link rel="stylesheet" href="../../css/login-page-style.css">
    <link rel="stylesheet" href="../../css/style.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>

<body>
    <div class="page">
        <jsp:include page="../components/user_navbar.jsp" />
    <div class="window">
        <div class="popup_window" style="height: 90vh;">
            <div class="header_row" style="justify-content:normal;">
                <div class="box_container">
                    <div class="box"></div>
                </div>
                <div class="header_text_container">
                    <h2>ORDER# [Consumer Name]</h2>
                    <h3>Order time</h3>
                </div>
                <button class="order_exit" onclick="closeSummary()">X</button>
            </div>
            <div class="popup_window" style="top:0; margin: 5% 0;">
                <table>
                    <tr>
                        <th class="table_qty">QTY</th>
                        <th class="table_item">Item Name</th>
                        <th class="table_custom">Customisations</th>
                        <th class="table_price">Price</th>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>Burger</td>
                        <td>No cheese</td>
                        <td>7.00</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>Coke</td>
                        <td></td>
                        <td>3.00</td>
                    </tr>
                </table>
            </div>
            <div class="header_row">
                <div class="header_text_container">
                    <h2>TOTAL:  Amount </h2>
                </div>
                <button class="confirm_button" onclick="payOrder()">Confirm & Pay</button>
            </div>
        </div>
    </div>
</body>
</html>
<script>
    function payOrder() {
        window.location.href = "./consumer_ongoing.jsp";
        //add status change of order and send order to restaurant
    }
</script>
<script>
    function closeSummary() {
        window.location.href = "./consumer_basket.jsp";
    }
</script>
