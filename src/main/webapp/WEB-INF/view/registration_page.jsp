<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<body>

<h2>Страница регистрации</h2>
<br>
<form:form action="saveUser" modelAttribute="user">
    Логин <form:input path="username"/>${errorLog}
    <form:errors path="username"/>
    <br><br>
    Пароль <form:input path="password"/>
    <form:errors path="password"/>
    <br><br>
    <input type="submit" value="Зарегистрироваться"/>
</form:form>

<br>
<input type="button" value="Уже есть аккаунт? Авторизуйтесь!"
       onclick="window.location.href = '/login'" />

</body>

</html>