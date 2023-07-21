<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Справочник ЦБ РФ</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" href="../../css/styleImport.css">
    <link rel="stylesheet" href="../../css/styleDirectories.css">
</head>
<body>
<#--Шапка-->
<header>
    <div class="container">
        <div class="row">
            <div class="col-md-auto">
                <div class="header-icon"><i class="bi bi-bank" ></i></div>
            </div>
            <div class="col">
                <h4 class="text-white">Справочник сведений ЦБ РФ о
                    кредитно-финансовых учреждениях</h4>
            </div>
            <div class="col-md-auto">
                <a class="btn btn-outline-light"  href="/registration">Авторизация</a>
            </div>
            <div class="col-md-auto">
                <a class="btn btn-outline-light"  href="/">Главная</a>
            </div>
        </div>
    </div>
</header>
<#--Тело-->
<section class="about">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h2>Добавленные файлы</h2>
                <#if messages.getTotalPages() != 0>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-sm">
                        <thead>
                        <tr>
                            <th></th>
                            <th></th>
                            <th>Номер ЭС</th>
                            <th>Дата составления</th>
                            <th>Уникальный id составителя</th>
                            <th>Уникальный id получателя</th>
                            <th>Код причины формирования</th>
                            <th>Дата и время создания </th>
                            <th>Вид представления информации</th>
                            <th>Дата ОД</th>
                            <th>Номер версии Справочника</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list messages.iterator() as message>
                        <tr>
                            <td>
                                <form action="/messages/delete/${message.id}" method="post">
                                    <input type="hidden" name="page" value="${page}">
                                    <button type="submit" class="bi bi-trash"></button>
                                </form>
                            </td>
                            <td >
                                ${message.id}
                            </td>
                            <td >
                                ${message.edNumber}
                            </td>
                            <td >
                                ${message.edDate}
                            </td>
                            <td >
                                ${message.edAuthor}
                            </td>
                            <td >
                                ${message.edReceiver}
                            </td>
                            <td >
                                ${message.creationReasonCode.code}
                            </td>
                            <td >
                                ${message.creationDateTime}
                            </td>
                            <td >
                                ${message.informationTypeCode.code}
                            </td>
                            <td >
                                ${message.businessDay}
                            </td>
                            <td >
                                ${message.directoryVersion}
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="/messages/0">Первая
                                страница</a></li>
                            <#if page != 0>
                            <li class="page-item"><a class="page-link" href="/messages/${prevPage}">${prevPage+1}</a>
                            </li>
                        </#if>
                        <li class="page-item active"><a class="page-link" href="#">${page+1}</a></li>
                        <#if page != maxPage>
                        <li class="page-item"><a class="page-link" href="/messages/${nextPage}">${nextPage+1}</a>
                        </li>
                    </#if>
                    <li class="page-item"><a class="page-link" href="/messages/${maxPage}">Последняя
                        страница</a></li>
                    </ul>
                    </nav>
                </div>
                <#else>
                <h3>Записи отсутствуют</h3>
            </#if>
        </div>
    </div>
    </div>
</section>
<#--Подвал-->
<footer id="footer">
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <a class="btn btn-outline-light" target="_blank" href="https://vk.com/tony996"><i
                        class="bi bi-bootstrap"></i></a>
                <a class="btn btn-outline-light" target="_blank" href="https://github.com/Tiltuem"><i
                        class="bi bi-github"></i></a>
                <a class="btn btn-outline-light" target="_blank" href="mailto:dekerilljr@gmail.com"><i
                        class="bi bi-envelope-at"></i></a>
            </div>
        </div>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
</body>
</html>


