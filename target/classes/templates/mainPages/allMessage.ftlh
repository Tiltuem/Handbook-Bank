<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>
<#import "../parts/footer.ftlh" as f>

<@c.page "../../css/styleDirectories.css">
<!--Шапка-->
<@h.header />

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
                            <th></th>
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
                            <td >
                                <a href="/message-${message.id}/directory-entry/0">Подробнее..</a>
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
                                <li class="page-item"><a class="page-link" href="/messages/${page-1}">${page}</a>
                                </li>
                            </#if>
                            <li class="page-item active"><a class="page-link" href="#">${page+1}</a></li>
                            <#if page != maxPage>
                                <li class="page-item"><a class="page-link" href="/messages/${page+1}">${page+2}</a>
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
<!--Подвал-->
<@f.footer />

</@c.page>



