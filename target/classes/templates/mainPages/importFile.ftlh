<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>
<#import "../parts/footer.ftlh" as f>

<@c.page "../../css/styleImport.css">

<!--Шапка-->
<@h.header />

<!--Тело-->
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
                            <th>
                                <a href="/import/${page}-sort?column=name&direction=ASC" class="bi bi-caret-up-fill color1"></a>
                                Наименование
                                <a href="/import/${page}-sort?column=name&direction=DESC" class="bi bi-caret-down-fill"></a>
                            </th>
                            <th>Дата и время создания
                                <a href="/import/{page}?column=name&directrion=ASC" class="bi bi-caret-down-fill"></a>
                            </th>
                            <th>Файл
                                <i class="bi bi-caret-down"></i>
                            </th>
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
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="/import/0">Первая
                                страница</a></li>
                            <#if page != 0>
                            <li class="page-item"><a class="page-link" href="/import/${page-1}">${page}</a>
                            </li>
                        </#if>
                        <li class="page-item active"><a class="page-link" href="#">${page+1}</a></li>
                        <#if page != maxPage>
                        <li class="page-item"><a class="page-link" href="/import/${page+1}">${page+2}</a>
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

                                    <#if bindingResult?has_content>
                                    <#list bindingResult.allErrors as error>
                                    <div class="error"><li>${error.defaultMessage}</li></div>
                                </#list>
                            </#if>

                                    <button type="submit" class="btn btn-my">Получить данные</button>
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
                                    <#if bindingResult?has_content>
                                    <#list bindingResult.allErrors as error>
                                    <div class="error"><li>${error.defaultMessage}</li></div>
                                </#list>
                            </#if>

                                    <button type="submit" class="btn btn-my">Получить данные</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</section>
<!--Подвал-->
<@f.footer />
</@c.page>

