<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>
<#import "../parts/footer.ftlh" as f>
<#import "../parts/loadingModal.ftlh" as l>

<#import "/spring.ftl" as spring />

<@c.page "../../css/styleDirectories.css">
    <!--?????-->
    <@h.header />

    <!--????-->
    <section class="about">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Редактирование списка ограничений</h2>
                    <form action="/message-${messageId}/entry-${entryId}/restriction-list-edit" method="post">
                        <input type="hidden" name="id" value="${restrictionList.id}">
                        <div class="col-25">
                            Код ограничения:
                        </div>
                        <div class="col-75">
                            <select type="text" name="restrictionCode">
                                <#list restrictionCodeList as code>
                                    <#if restrictionList.restrictionCode = code.code>
                                        <option value="${code.code}" selected="selected">${code.code}</option>
                                    </#if>
                                    <option value="${code.code}">${code.code}</option>
                                </#list>
                            </select>
                        </div>
                        <br>
                        <div class="col-25">
                            Дата начала действия ограничения:
                        </div>
                            <input type="date" name="restrictionDate" value="${restrictionList.restrictionDate}">
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
                        <div class="row">
                            <div class="col-md-2">
                                <a class="btn btn-my" onclick="history.back()">Назад</a>
                            </div>
                            <div class="col-md-2">
                                <button type="submit" value="submit" class="btn btn-my" data-bs-toggle="modal"
                                        data-bs-target="#loadingModal">Сохранить
                                </button>
                            </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <!--??????-->
    <@f.footer />
    <!--Окно загрузки-->
    <@l.loading />
</@c.page>