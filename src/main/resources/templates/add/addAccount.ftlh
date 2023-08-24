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
                    <h2>Добавление аккаунта</h2>
                    <form action="/message-${messageId}/entry-${entryId}/account-add" method="post">
                        <input type="hidden" name="page" value="${page}">
                        <div class="col-25">
                            Номер аккаунта:
                        </div>
                        <div class="col-75">
                            <@spring.formInput "account.accountNumber"/>
                            <#if bindingResult?has_content>
                                <#list bindingResult.allErrors as error>
                                    <#if error.field = "accountNumber">
                                        <div class="error">
                                            <li>${error.defaultMessage}</li>
                                        </div>
                                    </#if>
                                </#list>
                            </#if>
                        </div>
                        <br>
                        <div class="col-25">
                            БИК ПБР:
                        </div>
                        <div class="col-75">
                            <@spring.formInput "account.bicCbr"/>
                            <#if bindingResult?has_content>
                                <#list bindingResult.allErrors as error>
                                    <#if error.field = "bicCbr">
                                        <div class="error">
                                            <li>${error.defaultMessage}</li>
                                        </div>
                                    </#if>
                                </#list>
                            </#if>
                        </div>
                        <br>
                        <div class="col-25">
                            Контрольный ключ:
                        </div>
                        <div class="col-75">
                            <@spring.formInput "account.controlKey"/>
                            <#if bindingResult?has_content>
                                <#list bindingResult.allErrors as error>
                                    <#if error.field = "controlKey">
                                        <div class="error">
                                            <li>${error.defaultMessage}</li>
                                        </div>
                                    </#if>
                                </#list>
                            </#if>
                        </div>
                        <br>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="col-35">
                                    Статус счета:
                                </div>
                                <div class="col-65">
                                    <select type="text" name="accountStatusCode">
                                        <#list accountStatusCodeList as code>
                                            <option value="${code.code}">${code.code}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="col-50">
                                    Дата включения:
                                </div>
                                <div class="col-50">
                                    <#if account.dateIn?has_content>
                                        <input type="date" name="dateIn" value="${account.dateIn}">;
                                        <#else>
                                            <input type="date" name="dateIn">;
                                    </#if>
                                </div>
                                <#if bindingResult?has_content>
                                    <#list bindingResult.allErrors as error>
                                        <#if error.field = "dateIn">
                                            <div class="error">
                                                <li>${error.defaultMessage}</li>
                                            </div>
                                        </#if>
                                    </#list>
                                </#if>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="col-35">
                                    Тип счета:
                                </div>
                                <div class="col-65">
                                    <select type="text" name="regulationAccountTypeCode">
                                        <#list regulationAccountTypeCodeList as code>
                                            <option value="${code.code}">${code.code}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="col-50">
                                    Дата исключения:
                                </div>
                                <div class="col-50">
                                    <#if account.dateOut?has_content>
                                        <input type="date" name="dateOut" value="${account.dateOut}">;
                                    <#else>
                                        <input type="date" name="dateOut">;
                                    </#if>
                                </div>
                            </div>
                        </div>

                        <br>
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