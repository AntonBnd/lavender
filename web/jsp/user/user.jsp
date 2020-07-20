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
    <title><fmt:message key="title.user"/></title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/sticky-footer-navbar.css">
    <link rel="stylesheet" href="../../css/top-buffer.css">
</head>
<body>
<c:import url="../header.jsp"></c:import>

<div class="container">
    <h1 align="center"><fmt:message key="label.user"/></h1>
    <h4 align="center">${warn}</h4>
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
                    <li><strong><fmt:message key="label.tourType"/></strong> <c:out value="${temp.tour.tourType.typeName}" /></li>
                    <li><strong><fmt:message key="label.resort"/></strong> <c:out value="${temp.tour.resort.resortName}" /></li>
                    <li><strong><fmt:message key="label.country"/></strong> <c:out value="${temp.tour.resort.country.countryName}" /></li>
                    <li><strong><fmt:message key="label.price"/></strong> <c:out value="${temp.tour.price}" /></li>
                    <li><strong><fmt:message key="label.discription"/></strong> <c:out value="${temp.tour.discription}" /></li>
                    <li><strong><fmt:message key="label.operator"/></strong> <c:out value="${temp.tour.tourOperator.operatorName}" /></li>
                    <li><strong><fmt:message key="label.orderDate"/></strong> <c:out value="${temp.orderDate}" /></li>
                    <li><strong><fmt:message key="label.itemNumber"/></strong> <span class="badge"><c:out value="${temp.itemNumber}" /></span></li>
                    <li><strong><fmt:message key="label.status"/></strong> <ctg:status tourStatus="${temp.orderStatus.statusName}" /></li>
                    <div class="row top-buffer">
                        <div class="col-md-2">
                            <c:if test="${temp.orderStatus.statusName eq 'processed' || temp.orderStatus.statusName eq 'approved'}">
                                <form name="cancelOrder" action="lavender" method="post">
                                    <input type="hidden" name="command" value="cancelOrder">
                                    <input type="hidden" name="tourId" value="${temp.tour.tourId}">
                                    <input type="hidden" name="orderId" value="${temp.orderId}">
                                    <input type="hidden" name="totalNum" value="${temp.tour.numberOfSeats}">
                                    <input type="hidden" name="itemNum" value="${temp.itemNumber}">
                                    <button class="btn btn-group-sm btn-primary" type="submit"><fmt:message key="label.cancelOrder"/></button>
                                </form>
                            </c:if>
                        </div>
                        <c:if test="${temp.orderStatus.statusName eq 'approved'}">
                            <div class="col-md-2 col-md-offset-3">
                                <form name="pay" action="jsp/user/payPage.jsp" method="post">
                                    <input type="hidden" name="orderId" value="${temp.orderId}">
                                    <button class="btn btn-group-sm btn-info" type="submit"><fmt:message key="label.pay"/></button>
                                </form>
                            </div>
                        </c:if>
                    </div>
                    <hr>
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