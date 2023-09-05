<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#macro tableCode nameCode>
    <#if codes.getTotalPages() != 0>
        <@s.searchCode nameCode/>
        <br>
        <div class="col-md-12">
            <div class="table-responsive">
                <table class="table table-striped table-hover table-sm rounded">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Код</th>
                        <th>Кем создано</th>
                        <th>Когда создано</th>
                        <th>Кем изменено</th>
                        <th>Когда изменено</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list codes.iterator() as code>
                        <tr>
                            <td style="text-align: center">
                                <@security.authorize access="hasRole('ROLE_ADMIN')">
                                <#if code.deleted = false>
                                    <form action="/codes/${nameCode}/delete/${code.id}" method="post">
                                        <input type="hidden" name="page" value="${page}">
                                        <button type="submit" class="btn-delete" data-bs-toggle="modal"
                                                data-bs-target="#loadingModal"><i class="bi bi-trash"></i></button>
                                    </form>
                                </#if>
                                </@security.authorize>
                            </td>
                            <td>
                                ${code.code}
                            </td>
                            <td>
                                ${code.createdBy}
                            </td>
                            <td>
                                ${code.createdDate}
                            </td>
                            <td>
                                ${code.modifiedBy}
                            </td>
                            <td>
                                ${code.modifiedDate}
                            </td>
                            <td>
                                <@security.authorize access="hasRole('ROLE_ADMIN')">
                                <#if code.deleted = false>
                                    <button type="button" class="btn btn-light" data-bs-toggle="modal"
                                            data-bs-target="#editModal" onclick="getId(${code.id})"><i
                                                class="bi bi-pencil edit-svgIcon"></i>Редактировать
                                    </button>
                                <#else>
                                    <form action="/codes/${nameCode}/recovery/${code.id}" method="post">
                                        <button type="submit" class="btn btn-light" data-bs-toggle="modal"
                                                data-bs-target="#loadingModal">Восстановить
                                        </button>
                                    </form>
                                </#if>
                                </@security.authorize>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
                <@p.pagination nameCode/>
            </div>
        </div>
    <#else>
        <h3>Записи отсутствуют</h3>
        <#if search??>
            <a class="btn btn-my" href="/codes/${nameCode}/0">Отменить поиск</a>
        </#if>
    </#if>
</#macro>