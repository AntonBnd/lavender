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
    <title><fmt:message key="title.registration"/></title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="col-md-4 col-md-offset-4" style="margin-top:100px">
        <a href="/index.jsp"><h3 class="text-center"><fmt:message key="label.lavenderTravel"/></h3></a>
        <form name="signin" action="/lavender" method="post">
            <input type="hidden" name="command" value="registrate">

            <div class="form-group">
                <label for="inputFname"><fmt:message key="label.firstName"/></label>
                <input type="text" class="form-control" name="fname" value="${param.fname}" required pattern="^([A-Za-z]{1,40}|[а-яА-ЯЁё]{1,40})$" title="<fmt:message key="message.nameReg"/>" id="inputFname" placeholder=<fmt:message key="label.firstName"/>>
            </div>

            <div class="form-group">
                <label for="inputLname"><fmt:message key="label.lastName"/></label>
                <input type="text" class="form-control" name="lname" value="${param.lname}" required pattern="^([A-Za-z]{1,40}|[а-яА-ЯЁё]{1,40})$" title="<fmt:message key="message.lastNameReg"/>" id="inputLname" placeholder=<fmt:message key="label.lastName"/>>
            </div>

            <div class="form-group">
                <label for="inputTelNumber"><fmt:message key="label.phoneNumber"/></label>
                <input type="text" class="form-control" name="phoneNumber" value="${param.phoneNumber}" required pattern="^(\+375)(25|29|33|44)([1-9]){1}([0-9]){6}$" title="<fmt:message key="message.telNum"/>" id="inputTelNumber" placeholder=<fmt:message key="label.phoneNumber"/> >
            </div>

            <div class="form-group">
                <label for="inputEmail"><fmt:message key="label.username"/></label>
                <input type="text" class="form-control" name="username" value="${param.username}"  required pattern="^(([a-zA-Z]{1})([a-zA-Z0-9]){2,18}([a-zA-z]))$" title="<fmt:message key="message.login"/>" id="inputEmail" placeholder=<fmt:message key="label.username"/>>
            </div>

            <div class="form-group">
                <label for="inputPassword"><fmt:message key="label.password"/></label>
                <input type="password" class="form-control" name="password" required pattern="^([a-zA-Z0-9]{4,20})$" title="<fmt:message key="message.pass"/>" id="inputPassword" placeholder=<fmt:message key="label.password"/>>
            </div>

            <h6 style="color: red">${warn}</h6>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="label.signUp"/></button>
        </form>
    </div>
</div>
</body>
</html>
