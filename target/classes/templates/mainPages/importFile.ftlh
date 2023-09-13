<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>
<#import "../parts/footer.ftlh" as f>
<#import "../parts/loadingModal.ftlh" as l>
<#import "../parts/pagination.ftlh" as p>

<@c.page "../../css/styleDirectories.css">

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
                                    <th>
                                        Наименование
                                    </th>
                                    <th>Дата и время создания
                                    </th>
                                    <th>Файл
                                    </th>
                                    <th>Пользователь</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list files.iterator() as fileInfo>
                                    <#if !fileInfo.message.deleted>
                                    <tr>
                                        <td>
                                            ${fileInfo.id}
                                        </td>
                                        <td>
                                            ${fileInfo.name}
                                        </td>
                                        <td>
                                            ${fileInfo.importDateTime}
                                        </td>
                                        <td>
                                            <a href="${fileInfo.fileLink}">${fileInfo.name}</a>
                                        </td>
                                        <td>
                                            ${fileInfo.message.user.username}
                                        </td>
                                    </tr>
                                    </#if>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                        <@p.pagination "import" files />
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
                                                <div class="error">
                                                    <li>${error.defaultMessage}</li>
                                                </div>
                                            </#list>
                                        </#if>

                                        <button type="submit" class="btn btn-my" data-bs-toggle="modal"
                                                data-bs-target="#loadingModal">Получить данные
                                        </button>
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
                                        Прикрепите файл в формале '.xml' или '.zip': <input type="file" name="fileXml"/><br><br>
                                        <input type="hidden" name="page" value="${page}">
                                        <button type="submit" class="btn btn-my" data-bs-toggle="modal"
                                                data-bs-target="#loadingModal">Получить данные
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--Подвал-->
    <@f.footer />
    <!--Окно загрузки-->
    <@l.loading />
</@c.page>

