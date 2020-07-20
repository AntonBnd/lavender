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
    <title><fmt:message key="title.addTour"/></title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/sticky-footer-navbar.css">
</head>
<body>
<c:import url="../header.jsp"></c:import>
<div class="container">
    <h1 align="center"><fmt:message key="label.addTour"/></h1>
    <h4 align="center" style="color: red">${warn}</h4>
    <c:if test="${param.message == 'success'}">
        <h4 align="center" style="color: #0F28FF"><fmt:message key="message.add"/></h4>
    </c:if>
    <div class="col-md-4 col-md-offset-4">
        <form name="addTour" action="/lavender" method="post">
            <input type="hidden" name="command" value="addTour">
            <input type="hidden" name="previousCommand" value="${param.command}">

            <div class="form-group">
                <label for="type"><fmt:message key="label.tourType"/></label>
                <select name="tourTypeId" class="form-control" id="type">
                    <c:forEach var="temp" items="${tourTypes}">
                        <c:if test="${param.tourTypeId == temp.typeId}">
                            <option value="${temp.typeId}" selected="selected">${temp.typeName}</option>
                        </c:if>
                        <c:if test="${param.tourTypeId != temp.typeId}">
                            <option value="${temp.typeId}">${temp.typeName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="resort"><fmt:message key="label.resorts"/></label>
                <select name="resortId" class="form-control" id="resort">
                    <c:forEach var="temp" items="${resorts}">
                        <c:if test="${param.resortId == temp.resortId}">
                            <option value="${temp.resortId}" selected="selected">${temp.resortName}</option>
                        </c:if>
                        <c:if test="${param.resortId != temp.resortId}">
                            <option value="${temp.resortId}">${temp.resortName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="price"><fmt:message key="label.price"/></label>
                <input type="number" min="0" class="form-control" name="price" pattern="^([0]{1}|[1][0-9]+)$" value="${param.price}" required id="price" placeholder=<fmt:message key="label.price"/> >
            </div>

            <div class="form-group">
                <label for="comment"><fmt:message key="label.discription"/></label>
                <textarea name="discription" required class="form-control" rows="5" id="comment" placeholder=<fmt:message key="label.discription"/>>${param.discription}</textarea>
            </div>

            <div class="form-group">
                <label for="operator"><fmt:message key="label.operator"/></label>
                <select name="tourOperatorId" class="form-control" id="operator">
                    <c:forEach var="temp" items="${tourOperators}">
                        <c:if test="${param.tourOperatorId == temp.operatorId}">
                            <option value="${temp.operatorId}" selected="selected">${temp.operatorName}</option>
                        </c:if>
                        <c:if test="${param.tourOperatorId != temp.operatorId}">
                            <option value="${temp.operatorId}">${temp.operatorName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="seats"><fmt:message key="label.itemNumber"/></label>
                <input type="number" min="1" class="form-control" name="itemNumber" pattern="^([1]{1}|[1-9]{1}|[0-9]{1,3})$" value="${param.itemNumber}" required id="seats" placeholder=<fmt:message key="label.itemNumber"/> >
            </div>

            <div class="form-group">
                <label for="begDate"><fmt:message key="label.begDate"/></label>
                <input type="date" class="form-control" name="begDate" value="${param.begDate}" required id="begDate" placeholder=<fmt:message key="label.begDate"/> >
            </div>

            <div class="form-group">
                <label for="endDate"><fmt:message key="label.endDate"/></label>
                <input type="date" class="form-control" name="endDate" value="${param.endDate}" required id="endDate" placeholder=<fmt:message key="label.endDate"/> >
            </div>

            <div class="form-group">
                <label for="status"><fmt:message key="label.tourStatus"/></label>
                <select name="status" class="form-control" id="status">
                    <c:choose>
                        <c:when test="${param.status eq '1'}">
                            <option value="1" selected="selected"><fmt:message key="label.berning"/></option>
                            <option value="0"><fmt:message key="label.nBerning"/></option>
                        </c:when>
                        <c:otherwise>
                            <option value="0" selected="selected"><fmt:message key="label.nBerning"/></option>
                            <option value="1"><fmt:message key="label.berning"/></option>
                        </c:otherwise>
                    </c:choose>
                </select>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="label.addTour"/></button>
        </form>
    </div>
</div>

<c:import url="../footer.jsp"></c:import>
<script src="../../js/jquery-1.11.3.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>
</html>