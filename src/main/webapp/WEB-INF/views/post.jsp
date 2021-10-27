<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
    <script>
        function reply() {
            $('#btnForm').attr('hidden', true);
            $('#replyForm').attr('hidden', false);
        }

        function cancel() {
            $('#comment').val('');
            $('#btnForm').attr('hidden', false);
            $('#replyForm').attr('hidden', true);
        }
    </script>

    <title><c:out value="${post.name}"/></title>
</head>
<body>
<div class="container mt-3">
    <div class="row justify-content-center">
        <h4>Форум job4j</h4>
    </div>

    <div class="row justify-content-end">
        <ul class="nav">
            <li class="nav-item"><a class="nav-link" href="<c:url value="/index"/>">Форум</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value="/post/create"/>">Новая тема</a></li>
            <li class="nav-item"><a class="nav-link" href="#"><c:out value="${user.username}"/></a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value="/logout"/>">Выход</a></li>
        </ul>
    </div>

    <div class="row">
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th colspan="2"><c:out value="${post.name}"/></th>
                </tr>
            </thead>
            <tbody>
            <fmt:setLocale value="ru_RU" scope="session"/>
            <tr>
                <td style="width: 20%">
                    <strong><c:out value="${post.author.username}"/></strong>
                    <br>
                    <small>от <fmt:formatDate pattern="dd MMMM yyyy, HH:mm" value="${post.created.time}" type="date"/></small>
                </td>
                <td>
                    <pre><c:out value="${post.description}"/></pre>
                </td>
            </tr>
            <c:forEach items="${post.comments}" var="comment">
                <tr>
                    <td style="width: 20%">
                        <strong><c:out value="${comment.author.username}"/></strong>
                        <br>
                        <small>от <fmt:formatDate pattern="dd MMMM yyyy, HH:mm" value="${comment.created.time}" type="date"/></small>
                    </td>
                    <td>
                        <pre><c:out value="${comment.text}"/></pre>
                    </td>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <c:if test="${user != null}">
        <div class="row">
            <form id="btnForm" style="width: 100%">
                <button type="button" class="btn btn-dark pull-left" onclick="reply()">Ответить</button>
                <c:if test="${user.equals(post.author)}">
                    <input type="hidden" name="id" value="<c:out value='${post.id}'/>">
                    <button type="submit" class="btn btn-dark pull-right ml-2" formaction="<c:url value='/post/edit'/>"
                            formmethod="GET">Редактировать тему</button>
                </c:if>
            </form>
            <form id="replyForm" style="width: 100%" action="<c:url value="/comment/save?postId=${post.id}"/>" method="POST" hidden>
                <div class="form-group">
                    <textarea class="form-control" id="comment" name="text" rows="3" required
                              oninvalid="this.setCustomValidity('В теме нельзя оставить пустой комментарий!')"
                              oninput="this.setCustomValidity('')"></textarea>
                </div>
                <button type="submit" class="btn btn-dark pull-left">Отправить</button>
                <button type="button" class="btn btn-light pull-right ml-2" onclick="cancel()">Отмена</button>
            </form>
        </div>
    </c:if>
</div>
</body>
</html>
