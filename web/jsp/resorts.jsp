<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
    <c:when test="${sessionScope.language == null}">
        <fmt:setLocale value="ru_Ru"/>
    </c:when>
    <c:when test="${sessionScope.language eq 'ru_Ru'}">
        <fmt:setLocale value="ru_Ru"/>
    </c:when>
    <c:when test="${sessionScope.language eq 'en_Us'}">
        <fmt:setLocale value="en_Us"/>
    </c:when>
</c:choose>
<fmt:setBundle basename="resources.caption"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="title.resorts"/></title>
    <link rel="stylesheet" href="../../css/top-buffer.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/sticky-footer-navbar.css">
</head>
<body>
<c:import url="header.jsp"></c:import>
<div class="container">
    ${warn}
    <c:forEach var="temp" items="${resorts}" varStatus="number">
        <c:if test="${(number.count) == 1 || (number.count mod 4) == 1}">
            <div class="row top-buffer">
        </c:if>
        <div class="col-md-2 col-md-offset-1">
            <a href="lavender?command=resort&id=${temp.resortId}" class="btn btn-default btn-block" role="button">${temp.resortName}</a>
        </div>
        <c:if test="${(number.count) == fn:length(resorts) || (number.count mod 4) == 0}">
            </div>
        </c:if>
    </c:forEach>
</div>

<c:import url="footer.jsp"></c:import>

<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
