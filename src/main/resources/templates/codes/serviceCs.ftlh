<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Справочник ЦБ РФ</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" href="../../css/styleDirectories.css">
</head>
<body>
<#--Шапка-->
<header>
    <div class="container">
        <div class="row">
            <div class="col-md-auto">
                <div class="header-icon"><i class="bi bi-bank"></i></div>
            </div>
            <div class="col">
                <h4 class="text-white">Справочник сведений ЦБ РФ о
                    кредитно-финансовых учреждениях</h4>
            </div>
            <div class="col-md-auto">
                <a class="btn btn-outline-light" href="/registration">Авторизация</a>
            </div>
            <div class="col-md-auto">
                <a class="btn btn-outline-light" href="/">Главная</a>
            </div>
        </div>
    </div>
</header>
<#--Тело-->
<section class="about">
    <div class="container">
        <div class="row">
            <div class="col-md-5">
                <h2>Доступные сервисы перевода</h2>
                <#if codes.getTotalPages() != 0>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-sm">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Код</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list codes.iterator() as code>
                        <tr>
                            <td>
                                <form action="/codes/serviceCs/delete/${code.id}" method="post">
                                    <input type="hidden" name="page" value="${page}">
                                    <button type="submit" class="bi bi-trash"></button>
                                </form>
                            </td>
                            <td width="40%">
                                ${code.code}
                            </td>
                            <td>
                                <button type="button" class="bi bi-pencil" data-bs-toggle="modal"
                                        data-bs-target="#editModal" onclick="getId(${code.id})">Редактировать</button>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="/codes/serviceCs/0">Первая
                                страница</a></li>
                            <#if page != 0>
                            <li class="page-item"><a class="page-link" href="/codes/serviceCs/${prevPage}">${prevPage+1}</a>
                            </li>
                        </#if>
                        <li class="page-item active"><a class="page-link" href="#">${page+1}</a></li>
                        <#if page != maxPage>
                        <li class="page-item"><a class="page-link" href="/codes/serviceCs/${nextPage}">${nextPage+1}</a>
                        </li>
                    </#if>
                    <li class="page-item"><a class="page-link" href="/codes/serviceCs/${maxPage}">Последняя
                        страница</a></li>
                    </ul>
                    </nav>
                </div>
                <#else>
                <h3>Записи отсутствуют</h3>
            </#if>
        </div>
        <div class="col-md-7">
            <h2>Добавить запись</h2>
            <form action="/codes/serviceCs/add" method="post">
                <div>
                    Введите цифру: <input class="form-control"
                                                                                              type="text"
                                                                                              pattern="[0-9]{1}"
                                                                                              name="code" maxlength="1"
                                                                                              required/><br>
                </div>
                <div class="row">
                    <div class="col">
                        <input type="submit" value="Добавить" class="btn btn-my"/>
                    </div>
                    <div class="col-md-auto">
                        <a class="btn btn-my" href="/codes">Назад</a>
                    </div>
                </div>
            </form>
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
<!--Окно редактирования-->
<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel" >Редактирование записи</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
            </div>
            <div class="modal-body">
                <form action="/codes/serviceCs/update" method="post">
                    <div>
                        Введите цифру: <input class="form-control"
                                              type="text"
                                              pattern="[0-9]{1}"
                                              name="newCode" maxlength="1"
                                              required/><br>
                        <input id="id" type="hidden" name="id">
                        <input type="hidden" name="page" value="${page}">
                        <input type="submit" value="Сохранить" class="btn btn-my"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!--Скрипт для получения id-записи в модальном окне-->
<script>
     function getId(id){
        document.getElementById("id").value = id;
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
</body>
</html>