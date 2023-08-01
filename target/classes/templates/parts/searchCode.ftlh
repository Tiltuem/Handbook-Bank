<#macro searchCode nameCodeSearch>
<form action="/codes/${nameCodeSearch}/${page}" method="get">
    <div class="search">
        <input placeholder="Введите код" type="text" name="code">
        <input type="hidden" name="page" value="${page}">
        <button type="submit" data-bs-toggle="modal" data-bs-target="#loadingModal">Поиск</button>
    </div>
    <div class="checkbox">
        <input id="checkbox1" class="checkbox__input" type="checkbox"  name="deleted">
        <label for="checkbox1" class="checkbox__label">
            <span class="checkbox__custom"></span>
            Показать удалённые
        </label>
    </div>
    <#if search??>
        <br>
        <a class="btn btn-my" href="/codes/${nameCodeSearch}/0">Отменить поиск</a>
    </#if>
</form>
</#macro>