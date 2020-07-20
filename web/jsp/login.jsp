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
    <title><fmt:message key="title.signIn"/></title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="col-md-4 col-md-offset-4" style="margin-top:150px">
        <a href="/index.jsp"><h3 class="text-center"><fmt:message key="label.lavenderTravel"/></h3></a>
        <form name="signin" action="/lavender" method="post">
            <input type="hidden" name="command" value="login">

            <div class="form-group">
                <label for="inputEmail"><fmt:message key="label.username"/></label>
                <input type="text" class="form-control" name="username" value="${param.username}" required pattern="^(([a-zA-Z]{1})([a-zA-Z0-9]){2,18}([a-zA-z]))$" title="<fmt:message key="message.login"/>" id="inputEmail" placeholder=<fmt:message key="label.username"/>>
            </div>

            <div class="form-group">
                <label for="inputPassword"><fmt:message key="label.password"/></label>
                <input type="password" class="form-control" name="password" value="${param.password}" required pattern="^([a-zA-Z0-9]{4,20})$" title="<fmt:message key="message.pass"/>" id="inputPassword" placeholder=<fmt:message key="label.password"/>>
            </div>

            <h6 style="color: red">${warn}</h6>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="label.signIn"/></button>
        </form>
    </div>
</div>
</body>
</html>