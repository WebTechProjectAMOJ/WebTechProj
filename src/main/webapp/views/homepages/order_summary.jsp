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
                    <h1> ORDER# [Consumer Name]</h1>
                    <h3>Order time</h3>
                    <h2 id="status">Order Status</h2>
                </div>
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

            <div class="footer">
                <h2>TOTAL:  Amount </h2>
                <!-- If status === basket show pay button-->
               <button class="confirm_button" onclick="payOrder()">Confirm & Pay</button>
               <button  type="button" class="confirm_button" style="background-color: lightgrey; border-color: black;" onclick="closeBox()">Close</button>
               <!-- If status == history && consumer session
               <button  type="button" class="confirm_button" style="background-color: darkorange; border-color: white;" onclick="openReview()">Review</button>

               delete button for history orders
                <button  type="button" class="confirm_button" style="background-color: indianred; border-color: indianred;" onclick="deleteOrder()">Delete</button>

               purchase again button for history orders
               <button  type="button" class="confirm_button" style="background-color: cornflowerblue; border-color: cornflowerblue;" onclick="payOrder()">Order Again</button>
               -->
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

    function closeBox() {
        window.location.href = "./consumer_basket.jsp"; //temporary

        var status = document.getElementById("status").textContent;
        var session = request.getSession(false);

        if (status === "Basket") {
            window.location.href = "./consumer_basket.jsp";
        }
        if (status === "History" && session.getAttribute("accountType")==="consumer") {
            window.location.href = "./consumer_history.jsp";
        }
        if (session.getAttribute("accountType")==="consumer") {
            window.location.href = "./consumer_ongoing.jsp";
        }

        // need to create return to restaurant views and drivers

    }

    function openReview() {
        // connect to review view

    }
</script>
