<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>

<head>
    <title>Все изображения</title>
</head>
<body>

    <span>
        <input type="button" value="<<" onclick="window.location.href = '/images/page/1'"/>
        <input type="button" value="<" onclick="window.location.href = '/images/page/${pagInfo.currentPage - 1}'"/>

        <c:forEach var="pageNumber" items="${pagInfo.pages}">
            <c:if test="${pageNumber == pagInfo.currentPage}">
                <input type="button" style="background-color: aquamarine" value="${pageNumber}"
                       onclick="window.location.href = '/images/page/${pageNumber}' "/>
            </c:if>
            <c:if test="${pageNumber != pagInfo.currentPage}">
                <input type="button" value="${pageNumber}"
                       onclick="window.location.href = '/images/page/${pageNumber}' "/>
            </c:if>
        </c:forEach>

        <input type="button" value=">" onclick="window.location.href = '/images/page/${pagInfo.currentPage + 1}'"/>
        <input type="button" value=">>" onclick="window.location.href = '/images/page/${pagInfo.totalPages}'"/>
    </span>

    <input type="button" value="Добавить изображение"
           onclick="window.location.href = '/images/add'"
    />
    <input type="button" value="Мои изображения"
           onclick="window.location.href = '/my'"
    />
    <input type="button" value="Все изображения" style="background-color: aquamarine"
           onclick="window.location.href = '/images'"
    />

    <input type="button" value="Выйти из системы"
           onclick="window.location.href = '/logout'"
    />

    <br>

    <h1>На странице ${images.size()} изображений</h1>

    <table>
        <c:forEach var="image" items="${images}">
            <tr>
                <td>
                    Номер изображения: ${image.id}
                    <br>
                    Автор: ${image.user.username}
                </td>
                <td><img src="data:image/jpeg;base64,${image.image}" style="width: 500px;height: 300px;" alt=""></td>
                <c:set var="flag" value="false"/>
                <td>
                    <c:if test="${image.user.username == username}">
                            <input type="button" value="Удалить"
                                   onClick="window.location.href = '/images/delete/${image.id}/${redirect}'">
                        <c:set var="flag" value="true"/>
                    </c:if>
                    <security:authorize access="hasRole('ADMIN')">
                        <c:if test="${flag != 'true'}">
                            <input type="button" value="Удалить"
                                   onClick="window.location.href = '/images/delete/${image.id}/${redirect}'">
                        </c:if>
                    </security:authorize>
                </td>

            </tr>
        </c:forEach>

    </table>

    <br>

    <input type="button" value="Добавить изображение"
           onclick="window.location.href = '/images/add'"
    />
    <input type="button" value="Мои изображения"
           onclick="window.location.href = '/my'"
    />
    <input type="button" value="Все изображения" style="background-color: aquamarine"
           onclick="window.location.href = '/images'"
    />

</body>

</html>