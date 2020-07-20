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
    <title><fmt:message key="title.countries"/></title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/sticky-footer-navbar.css">
</head>
<body>
<c:import url="header.jsp"></c:import>
<div class="container" style="margin-top: 5%">
    <c:forEach var="temp" items="${countries}" varStatus="number">
        <c:if test="${(number.count) == 1 || (number.count mod 4) == 1}">
            <div class="row">
        </c:if>
        <div class="col-md-2 col-md-offset-1">
                ${warn}
            <a href="/lavender?command=country&id=${temp.countryId}">
                <img src="images/flags/${temp.countryName}.png" class="img-thumbnail center-block" width="100" height="100">
            </a>
            <h5 class="text-center">${temp.countryName}</h5>
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