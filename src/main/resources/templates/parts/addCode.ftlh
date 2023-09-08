<#macro addCode nameCode condition>
    <h2>Добавить запись</h2>
    <form action="/codes/${nameCode}/add" method="post" style="font-size: 110%">
        Введите значение ${condition}:
        <@spring.formInput "${nameCode}Code.code"/>
        <#if bindingResult?has_content>
            <#list bindingResult.allErrors as error>
                <div class="error">
                    <li>${error.defaultMessage}</li>
                </div>
            </#list>
        </#if>
        <br>
        <br>
        <div class="row">
            <div class="col">
                <button type="submit" value="submit" class="btn btn-my" data-bs-toggle="modal"
                        data-bs-target="#loadingModal">Добавить
                </button>
            </div>
            <div class="col-md-auto">
                <a class="btn btn-my" href="/codes">Назад</a>
            </div>
        </div>
    </form>
</#macro>