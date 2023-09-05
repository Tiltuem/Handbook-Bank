<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>
<#import "../parts/footer.ftlh" as f>
<#import "/spring.ftl" as spring />

<@c.page "../../css/error.css">
    <!--Шапка-->
    <@h.header />

<#--Тело-->
    <section class="about">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Регистрация</h2>
                    <form action="/signup" method="post">
                        <div class="col-25">
                            Введите ник:
                        </div>
                        <div class="col-75">
                            <@spring.formInput "registrationUserDto.username"/>
                            <#if bindingResult?has_content>
                                <#list bindingResult.allErrors as error>
                                    <#if error.field = "username">
                                        <div class="error">
                                            <li>${error.defaultMessage}</li>
                                        </div>
                                    </#if>
                                </#list>
                            </#if>
                        </div>
                        <br>
                        <div class="col-25">
                            Введите электронную почту:
                        </div>
                        <div class="col-75">
                            <@spring.formInput "registrationUserDto.email"/>
                            <#if bindingResult?has_content>
                                <#list bindingResult.allErrors as error>
                                    <#if error.field = "email">
                                        <div class="error">
                                            <li>${error.defaultMessage}</li>
                                        </div>
                                    </#if>
                                </#list>
                            </#if>
                        </div>
                        <br>
                        <div class="col-25">
                            Введите пароль:
                        </div>
                        <div class="col-75">
                            <@spring.formInput "registrationUserDto.password"/>
                            <#if bindingResult?has_content>
                                <#list bindingResult.allErrors as error>
                                    <#if error.field = "password">
                                        <div class="error">
                                            <li>${error.defaultMessage}</li>
                                        </div>
                                    </#if>
                                </#list>
                            </#if>
                        </div>
                        <br>
                        <div class="col-25">
                            Подтвердите пароль:
                        </div>
                        <div class="col-75">
                            <@spring.formInput "registrationUserDto.confirmPassword"/>
                            <#if bindingResult?has_content>
                                <#list bindingResult.allErrors as error>
                                    <#if error.field = "confirmPassword">
                                        <div class="error">
                                            <li>${error.defaultMessage}</li>
                                        </div>
                                    </#if>
                                </#list>
                            </#if>
                        </div>
                        <br>
                        <a href="/login">Уже есть аккаунт? Войти</a>
                        <div class="col">
                            <button type="submit" value="submit" class="btn btn-my" data-bs-toggle="modal"
                                    data-bs-target="#loadingModal">Подтвердить
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



