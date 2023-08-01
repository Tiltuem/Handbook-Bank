<#macro tableCode nameCode>
<#if codes.getTotalPages() != 0>
<@s.searchCode nameCode/>
<br>
<div class="table-responsive">
    <table class="table table-striped table-hover table-sm rounded">
        <thead>
        <tr>
            <th></th>
            <th>Код</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list codes.iterator() as code>
        <tr>
            <td>
                <#if code.deleted = false>
                <form action="/codes/${nameCode}/delete/${code.id}" method="post">
                    <input type="hidden" name="page" value="${page}">
                    <button type="submit" class="bi bi-trash" data-bs-toggle="modal" data-bs-target="#loadingModal"></button>
                </form>
                </#if>
            </td>
            <td width="40%">
                ${code.code}
            </td>
            <td>
                <#if code.deleted = false>
                <button type="button" class="bi bi-pencil" data-bs-toggle="modal" data-bs-target="#editModal" onclick="getId(${code.id})">Редактировать</button>
                <#else>
                <form action="/codes/${nameCode}/recovery/${code.id}" method="post">
                <button type="submit" class="bi bi-trash" data-bs-toggle="modal" data-bs-target="#loadingModal">Восстановить
                </button>
            </form>
            </#if>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
    <@p.pagination nameCode/>
</div>
<#else>
<h3>Записи отсутствуют</h3>
<#if search??>
<a class="btn btn-my" href="/codes/${nameCode}/0">Отменить поиск</a>
</#if>
</#if>
</#macro>