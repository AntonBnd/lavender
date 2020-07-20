<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${sessionScope.language == null}">
    <fmt:setLocale value="ru_Ru"/>
</c:if>
<c:if test="${sessionScope.language != null}" >
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="resources.caption"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="title.payPage"/></title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/sticky-footer-navbar.css">
</head>

<body>
<c:import url="../header.jsp"></c:import>
<div class="container">
    <div class="col-md-4 col-md-offset-4" style="margin-top:110px">
        <h1 class="text-center"><fmt:message key="label.pay"/></h1>
        <form name="payForm" action="/lavender" method="post">
            <input type="hidden" name="command" value="pay">
            <input type="hidden" name="orderId" value="${param.orderId}">
            <input type="hidden" name="status" value="4">
            <div class="form-group">
                <label for="payOrder"><fmt:message key="label.payField"/></label>
                <input type="text" class="form-control" name="cardNumber" required pattern="[0-9]{16}" title="<fmt:message key="message.cardNumber"/>" id="payOrder" placeholder=<fmt:message key="label.payField"/> >
            </div>
            <h6 style="color: red">${warn}</h6>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="label.pay"/></button>
        </form>
    </div>
</div>

<c:import url="../footer.jsp"></c:import>
<script src="../../js/jquery-1.11.3.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>
</html>