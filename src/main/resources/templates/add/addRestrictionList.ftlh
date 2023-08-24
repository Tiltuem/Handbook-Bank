<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>
<#import "../parts/footer.ftlh" as f>
<#import "/spring.ftl" as spring />

<@c.page "../../css/styleDirectories.css">
    <!--Шапка-->
    <@h.header />

    <!--Тело-->
    <section class="about">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Добавление SWBIC</h2>
                    <form action="/message-${messageId}/entry-${entryId}/restriction-list-add" method="post">
                        <div class="col-25">
                            Код ограничения:
                        </div>
                        <div class="col-75">
                            <select type="text" name="restrictionCode">
                                <#list restrictionCodeList as code>
                                        <option value="${code.code}">${code.code}</option>
                                </#list>
                            </select>
                        </div>
                        <br>
                        <div class="col-25">
                        Дата начала действия ограничения:
                        </div>
                        <#if restrictionList.restrictionDate?has_content>
                            <input type="date" name="restrictionDate" value="${restrictionList.restrictionDate}">
                            <#else>
                                <input type="date" name="restrictionDate">
                        </#if>
                        <#if bindingResult?has_content>
                            <#list bindingResult.allErrors as error>
                                <#if error.field = "restrictionDate">
                                    <div class="error">
                                        <li>${error.defaultMessage}</li>
                                    </div>
                                </#if>
                            </#list>
                        </#if>
                        <br>
                        <input type="hidden" name="page" value="${page}">
                        <div class="col">
                            <button type="submit" value="submit" class="btn btn-my" data-bs-toggle="modal"
                                    data-bs-target="#loadingModal">Добавить
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <!--Подвал-->
    <@f.footer />

</@c.page>