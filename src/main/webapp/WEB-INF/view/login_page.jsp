<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
    <title th:text="${title}"></title>
</head>

<body>
<div>
    <form name="f" th:action="@{/perform-login}" method="post">
        <fieldset>
            <legend>�������</legend>
            <div th:if="${param.error}" class="alert alert-error">
                ������������ ��� � ������.
            </div>

            <div th:if="${param.logout}" class="alert alert-success">
                You have been logged out.
            </div>
            <label for="user">���</label>
            <input type="text" id="user" name="user"/>
            <label for="pass">������</label>
            <input type="password" id="pass" name="pass"/>
            <div class="form-actions">
                <button type="submit" class="btn">�����</button>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>