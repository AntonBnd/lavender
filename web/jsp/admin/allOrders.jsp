<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
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
    <title><fmt:message key="title.allOrders"/></title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/sticky-footer-navbar.css">
    <link rel="stylesheet" href="../../css/top-buffer.css">
</head>
<body>

<c:import url="../header.jsp"></c:import>
<div class="container">
    <div class="row">
        <button type="button" class="btn btn-primary" name="back" onclick="history.back()"><fmt:message key="label.back"/> </button>
    </div>

    <h1 align="center"><fmt:message key="label.admin"/></h1>
    <c:forEach var="temp" items="${orders}" varStatus="number">
        <c:if test="${(number.count) == 1 || (number.count mod 3) == 1}">
            <div class="row">
        </c:if>
        <div class="col-md-4 ">
            <div class="well">
                <ul class="list-unstyled">
                    <li><strong>№ </strong><c:out value="${number.count}" /></li>
                    <li><strong><fmt:message key="label.begDate"/></strong> <c:out value="${temp.tour.begDate}" /></li>
                    <li><strong><fmt:message key="label.endDate"/></strong> <c:out value="${temp.tour.endDate}" /></li>
                    <li><strong><fmt:message key="label.discription"/></strong> <c:out value="${temp.tour.discription}" /></li>
                    <li><strong><fmt:message key="label.country"/></strong> <c:out value="${temp.tour.resort.country.countryName}" /></li>
                    <li><strong><fmt:message key="label.firstName"/></strong> <c:out value="${temp.user.firstName}" /></li>
                    <li><strong><fmt:message key="label.lastName"/></strong> <c:out value="${temp.user.lastName}" /></li>
                    <li><strong><fmt:message key="label.orderDate"/></strong> <c:out value="${temp.orderDate}" /></li>
                    <li><strong><fmt:message key="label.resort"/></strong> <c:out value="${temp.tour.resort.resortName}" /></li>
                    <li><strong><fmt:message key="label.phoneNumber"/></strong> <c:out value="${temp.user.phoneNumber}" /></li>
                    <li><strong><fmt:message key="label.status"/></strong> <ctg:status tourStatus="${temp.orderStatus.statusName}" /></li>
                    <div class="row top-buffer">
                        <c:if test="${temp.orderStatus.statusName eq 'processed' || temp.orderStatus.statusName eq 'approved'}">
                            <form name="processOrder" action="lavender" method="post" class="form-inline">
                                <input type="hidden" name="command" value="processOrder">
                                <input type="hidden" name="orderId" value="${temp.orderId}">
                                <select name="status" class="form-control">
                                    <option value="2"><fmt:message key="label.approve"/></option>
                                    <option value="3"><fmt:message key="label.cancel"/></option>
                                </select>
                                <button class="btn btn-group-sm btn-primary" type="submit"><fmt:message key="label.processOrder"/></button>
                            </form>
                        </c:if>
                    </div>
                </ul>
            </div>
        </div>
        <c:if test="${(number.count) == fn:length(orders) || (number.count mod 3) == 0}">
            </div>
            <hr>
        </c:if>
    </c:forEach>
</div>

<c:import url="../footer.jsp"></c:import>
<script src="../../js/jquery-1.11.3.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>
</html>