<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>
<#import "../parts/footer.ftlh" as f>
<#import "../parts/loadingModal.ftlh" as l>

<@c.page "../../css/styleDirectories.css">
    <!--Шапка-->
    <@h.header />

<#--Тело-->
    <section class="about">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Добавленные ЭС</h2>
                    <form action="/messages/${page}-search" method="get">
                        <div class="search">
                            <input placeholder="Введите значение" type="text" name="value">
                            <input type="hidden" name="page" value="${page}">
                            <button type="submit" data-bs-toggle="modal" data-bs-target="#loadingModal">Поиск
                            </button>
                        </div>
                        <select name="column" class="btn btn-outline-light">
                            <option disabled>Выберите параметр</option>
                            <option value="edNumber">Номер ЭС</option>
                            <option value="edAuthor">Уникальный id составителя</option>
                            <option value="edReceiver">Уникальный id получателя</option>
                            <option value="creationReasonCode.code">Код причины формирования</option>
                            <option value="informationTypeCode.code">Вид представления информации</option>
                            <option value="directoryVersion">Номер версии Справочника</option>
                        </select>
                        <div class="checkbox">
                            <input id="checkbox1" class="checkbox__input" type="checkbox" name="showDeleted">
                            <label for="checkbox1" class="checkbox__label">
                                <span class="checkbox__custom"></span>
                                Показать удалённые
                            </label>
                        </div>
                        <br>
                        <br>
                        <select name="columnDate" class="btn btn-outline-light">
                            <option disabled>Выберите параметр</option>
                            <option value="edDate">Дата составления</option>
                            <option value="creationDateTime">Дата и время создания</option>
                            <option value="businessDay">Дата ОД</option>
                        </select>
                        с  <input type="date" name="dateFrom"> по <input type="date" name="dateBy">
                        <#if search??>
                            <br>
                            <br>
                            <a class="btn btn-my" href="">Отменить поиск</a>
                        </#if>
                    </form>
                    <br>
                    <#if messages.getTotalPages() != 0>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover table-sm rounded">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th></th>
                                    <th>Номер ЭС</th>
                                    <th>Дата составления</th>
                                    <th>Уникальный id составителя</th>
                                    <th>Уникальный id получателя</th>
                                    <th>Код причины формирования</th>
                                    <th>Дата и время создания</th>
                                    <th>Вид представления информации</th>
                                    <th>Дата ОД</th>
                                    <th>Номер версии Справочника</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list messages.iterator() as message>
                                    <tr>
                                        <td>
                                            <#if message.deleted = false>
                                                <form action="/messages/delete/${message.id}" method="post">
                                                    <input type="hidden" name="page" value="${page}">
                                                    <button type="submit" class="btn-delete" data-bs-toggle="modal"
                                                            data-bs-target="#loadingModal"><i class="bi bi-trash"></i>
                                                    </button>
                                                </form>
                                            </#if>
                                        </td>
                                        <td>
                                            ${message.id}
                                        </td>
                                        <td>
                                            ${message.edNumber}
                                        </td>
                                        <td>
                                            ${message.edDate}
                                        </td>
                                        <td>
                                            ${message.edAuthor}
                                        </td>
                                        <td>
                                            <#if message.edReceiver?has_content>
                                                ${message.edReceiver}
                                            </#if>
                                        </td>
                                        <td>
                                            ${message.creationReasonCode.code}
                                        </td>
                                        <td>
                                            ${message.creationDateTime}
                                        </td>
                                        <td>
                                            ${message.informationTypeCode.code}
                                        </td>
                                        <td>
                                            ${message.businessDay}
                                        </td>
                                        <td>
                                            ${message.directoryVersion}
                                        </td>
                                        <td>
                                            <#if message.deleted = true>
                                                <form action="/message/recovery/${message.id}" method="post">
                                                    <input type="hidden" name="page" value="${page}">
                                                    <button type="submit" class="btn btn-light" data-bs-toggle="modal" data-bs-target="#loadingModal">Восстановить
                                                    </button>
                                                </form>
                                                <#else>
                                                    <a class="btn btn-light" href="/message-${message.id}/directory-entry/0">Подробнее..</a>
                                            </#if>
                                        </td>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination">
                                <li class="page-item"><a class="page-link" href="/messages/0"><i class="bi bi-chevron-double-left"></i></a></li>
                                <#if page gt 0>
                                    <li class="page-item"><a class="page-link" href="/messages/${page-1}"><i class="bi bi-chevron-left"></i></a>
                                    </li>
                                </#if>
                                <li class="page-item active"><a class="page-link">
                                        <input id="num" type="text" value="${page+1}" size="2"/>
                                        из ${messages.getTotalPages()}
                                        <button onclick="location.href='/messages/'+(document.getElementById('num').value-1)">Перейти</button>
                                    </a></li>
                                <#if messages.getTotalPages() gt page + 1>
                                    <li class="page-item"><a class="page-link" href="/messages/${page+1}"><i class="bi bi-chevron-right"></i></a>
                                    </li>
                                </#if>
                                <li class="page-item"><a class="page-link"
                                                         href="/messages/${messages.getTotalPages()-1}"><i class="bi bi-chevron-double-right"></i></a></li>
                            </ul>
                        </nav>
                    <#else>
                        <h3>ЭС отсутствуют</h3>
                    </#if>
                </div>
            </div>
        </div>
    </section>
    <!--Подвал-->
    <@f.footer />

    <!--Окно загрузки-->
    <@l.loading />
</@c.page>



