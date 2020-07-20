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
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<footer class="footer">
    <div class="container">
        <p class="text-muted"><fmt:message key="label.footer"/></p>
    </div>
</footer>
</body>
</html>