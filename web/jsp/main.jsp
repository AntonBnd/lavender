<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<c:if test="${sessionScope.language == null}">
    <fmt:setLocale value="ru_Ru"/>
</c:if>
<c:if test="${not empty sessionScope.language}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="resources.caption"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="title.main"/></title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/sticky-footer-navbar.css">
    <style>
        .btn {width: 135px;}
    </style>
</head>
<body>
<!-- Fixed navbar -->
<c:import url="header.jsp"></c:import>

<!-- Begin page content -->
<div class="container">
    <div class="col-md-1 col-md-offset-11">
        <div class="dropdown">
            <button class="btn btn-primary dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">
                <fmt:message key="label.changeLanguage"/><span class="caret"></span></button>
            <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
                <li role="presentation"><a role="menuitem" tabindex="-1" href="/lavender?command=changeLanguage&language=en_Us">En</a></li>
                <li role="presentation"><a role="menuitem" tabindex="-1" href="/lavender?command=changeLanguage&language=ru_Ru">Ru</a></li>
            </ul>
        </div>
    </div>
    <h1 class="text-center"><fmt:message key="label.main"/></h1>
    <hr>
    <c:if test="${not empty tours}">
        <h3 class="text-center"><fmt:message key="label.burnTour"/></h3>
        <hr>
        <c:if test="${param.message eq 'warning'}">
            <h4 class="text-danger text-center"><fmt:message key="message.items" /></h4>
        </c:if>
    </c:if>
    <c:forEach var="temp" items="${tours}" varStatus="number">
        <c:if test="${(number.count) == 1 || (number.count mod 3) == 1}">
            <div class="row">
        </c:if>
        <div class="col-md-4 ">
            <div class="well">
                <ul class="list-unstyled">
                    <li><strong><fmt:message key="label.begDate"/></strong> <c:out value="${temp.begDate}" /></li>
                    <li><strong><fmt:message key="label.endDate"/></strong> <c:out value="${temp.endDate}" /></li>
                    <li><strong><fmt:message key="label.tourType"/></strong> <c:out value="${temp.tourType.typeName}" /></li>
                    <li><strong><fmt:message key="label.resort"/></strong> <c:out value="${temp.resort.resortName}" /></li>
                    <li><strong><fmt:message key="label.country"/></strong> <c:out value="${temp.resort.country.countryName}" /></li>
                    <li><strong><fmt:message key="label.price"/></strong> <c:out value="${temp.price}" /></li>
                    <li><strong><fmt:message key="label.discription"/></strong> <c:out value="${temp.discription}" /></li>
                    <li><strong><fmt:message key="label.operator"/></strong> <c:out value="${temp.tourOperator.operatorName}" /></li>
                    <li><strong><fmt:message key="label.seats"/></strong> <span class="badge"><c:out value="${temp.numberOfSeats}" /></span></li>
                    <li><strong><fmt:message key="label.tourStatus"/></strong> <span class="label label-danger"><fmt:message key="label.berning"/></span></li>
                </ul>

                <form name="order" action="lavender" method="post">
                    <input type="hidden" name="command" value="orderTour">
                    <input type="hidden" name="tourId" value="${temp.tourId}">
                    <input type="hidden" name="userId" value="${sessionScope.user.userId}">
                    <input type="hidden" name="totalNum" value="${temp.numberOfSeats}">
                    <c:if test="${sessionScope.role eq 'user'}">
                        <td class="col-md-1">
                            <button class="btn btn-group-sm btn-primary" type="submit"><fmt:message key="label.order"/></button>
                        </td>
                        <td class="col-md-1">
                            <input type="number" name="num" required min="1" max="${temp.numberOfSeats}" style="width: 50px;">
                            <fmt:message key="label.number"/>
                        </td>
                    </c:if>
                </form>
                <div class="row">
                    <div class="col-md-2">
                        <form name="update" action="lavender" method="post">
                            <input type="hidden" name="command" value="getUpdateData">
                            <input type="hidden" name="tourId" value="${temp.tourId}">
                            <input type="hidden" name="begDate" value="${temp.begDate}">
                            <input type="hidden" name="endDate" value="${temp.endDate}">
                            <input type="hidden" name="typeId" value="${temp.tourType.typeId}">
                            <input type="hidden" name="resortId" value="${temp.resort.resortId}">
                            <input type="hidden" name="price" value="${temp.price}">
                            <input type="hidden" name="discription" value="${temp.discription}">
                            <input type="hidden" name="operatorId" value="${temp.tourOperator.operatorId}">
                            <input type="hidden" name="seats" value="${temp.numberOfSeats}">
                            <input type="hidden" name="status" value="${temp.tourStatus}">
                            <c:if test="${sessionScope.role eq 'admin'}">
                                <button class="btn btn-group-sm btn-primary" type="submit"><fmt:message key="label.editTour"/></button>
                            </c:if>
                        </form>
                    </div>
                    <div class="col-md-1 col-md-offset-3">
                        <form name="delete" action="lavender" method="post">
                            <input type="hidden" name="command" value="deleteTour">
                            <input type="hidden" name="tourId" value="${temp.tourId}">
                            <input type="hidden" name="previousCommand" value="${param.command}">
                            <c:if test="${sessionScope.role eq 'admin'}">
                                <button class="btn btn-group-sm btn-primary" type="submit"><fmt:message key="label.deleteTour"/></button>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${(number.count) == fn:length(tours) || (number.count mod 3) == 0}">
            </div>
            <hr>
        </c:if>
    </c:forEach>
</div>
<c:import url="footer.jsp"></c:import>
<script src="../js/jquery-1.11.3.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</body>
</html>