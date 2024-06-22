<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<body>
<form:form method="POST" action="/images/saveImage?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Выберите изображение для загрузки:      </td>
            <td><input type="file" name="file" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form:form>
<br><br>
<input type="button" value="Мои изображения"
       onclick="window.location.href = '/my'"
/>
<input type="button" value="Все изображения"
       onclick="window.location.href = '/images'"
/>

</body>

</html>