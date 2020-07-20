<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
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
    <title><fmt:message key="title.error"/></title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <style>
        .error-template {padding: 40px 15px;text-align: center;}
        .error-actions {margin-top:15px;margin-bottom:15px;}
        .error-actions .btn { margin-right:10px; }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1><fmt:message key="message.error"/></h1>
                <h3 class="text-danger">${error}</h3>
                <div class="error-actions">
                    <a href="../index.jsp" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>
                        <fmt:message key="label.error"/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>