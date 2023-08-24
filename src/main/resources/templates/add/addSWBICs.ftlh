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
                    <form action="/message-${messageId}/entry-${entryId}/swbics-add" method="post">
                        <div class="col-25">
                            Код СВИФТ:
                        </div>
                        <div class="col-75">
                            <@spring.formInput "swbics.swbic"/>
                            <#if bindingResult?has_content>
                                <#list bindingResult.allErrors as error>
                                    <#if error.field = "swbic">
                                        <div class="error">
                                            <li>${error.defaultMessage}</li>
                                        </div>
                                    </#if>
                                </#list>
                            </#if>
                        </div>
                        <br>
                        <div class="col-25">
                        Признак использования БИК
                        (СВИФТ):
                        </div>
                        <#if swbics.defaultSWBIC?has_content>
                            <#if swbics.defaultSWBIC>
                                    <input type="checkbox" name="defaultSWBIC" checked>
                                <#else>
                                    <input type="checkbox" name="defaultSWBIC">
                            </#if>
                            <#else>
                                <input type="checkbox" name="defaultSWBIC">
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