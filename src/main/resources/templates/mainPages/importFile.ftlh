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
            <div class="col-md-7">
               <h2>Добавленные файлы</h2>
                <#if files.getTotalPages() != 0>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-sm">
                        <thead>
                        <tr>
                            <th></th>
                            <th></th>
                            <th>Наименование</th>
                            <th>Дата и время создания</th>
                            <th>Файл</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list files.iterator() as fileInfo>
                        <tr>
                            <td>
                                <form action="/import/delete/${fileInfo.id}" method="post">
                                    <input type="hidden" name="page" value="${page}">
                                    <button type="submit" class="bi bi-trash"></button>
                                </form>
                            </td>
                            <td >
                                ${fileInfo.id}
                            </td>
                            <td >
                                ${fileInfo.name}
                            </td>
                            <td >
                                ${fileInfo.importDateTime}
                            </td>
                            <td >
                                <a href="${fileInfo.fileLink}">${fileInfo.name}</a>
                            </td>
                            <td >
                                <a href="">Подробнее..</a>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="/import/0">Первая
                                страница</a></li>
                            <#if page != 0>
                            <li class="page-item"><a class="page-link" href="/import/${prevPage}">${prevPage+1}</a>
                            </li>
                        </#if>
                        <li class="page-item active"><a class="page-link" href="#">${page+1}</a></li>
                        <#if page != maxPage>
                        <li class="page-item"><a class="page-link" href="/import/${nextPage}">${nextPage+1}</a>
                        </li>
                    </#if>
                    <li class="page-item"><a class="page-link" href="/import/${maxPage}">Последняя
                        страница</a></li>
                    </ul>
                    </nav>
                </div>
                <#else>
                <h3>Записи отсутствуют</h3>
            </#if>
            </div>
            <div class="col-md-5">
                <h2>Добавить новый файл</h2>
                <div class="accordion accordion-flush" id="accordion">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading1">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapse1" aria-expanded="false" aria-controls="collapse1">
                                Поиск Справочника БИК на заданную дату
                            </button>
                        </h2>
                        <div id="collapse1" class="accordion-collapse collapse" aria-labelledby="heading1"
                             data-bs-parent="#accordion">
                            <div class="accordion-body fst-italic">
                                <form action="/import/date" method="post">
                                    Выберите дату: <input type="date" name="date"/><br><br>
                                    <input type="hidden" name="page" value="${page}">
                                    <input type="submit" class="btn btn-my" value="Получить данные"/>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading2">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapse2" aria-expanded="false" aria-controls="collapse2">
                                Импорт справочника БИК
                            </button>
                        </h2>
                        <div id="collapse2" class="accordion-collapse collapse" aria-labelledby="heading2"
                             data-bs-parent="#accordion">
                            <div class="accordion-body fst-italic">
                                <form action="/import/file" method="post" enctype="multipart/form-data">
                                    Прикрепите файл: <input type="file" name="fileXml"/><br><br>
                                    <input type="hidden" name="page" value="${page}">
                                    <input type="submit" class="btn btn-my" value="Получить данные"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
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


