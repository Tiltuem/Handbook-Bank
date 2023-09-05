<#import "/spring.ftl" as spring />
<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>
<#import "../parts/footer.ftlh" as f>
<#import "../parts/editModal.ftlh" as e>
<#import "../parts/loadingModal.ftlh" as l>
<#import "../parts/searchCode.ftlh" as s>
<#import "../parts/tableCode.ftlh" as t>
<#import "../parts/addCode.ftlh" as a>
<#import "../parts/getId.ftlh" as g>
<#import "../parts/pagination.ftlh" as p>
<@c.page "../../css/styleDirectories.css">
    <!--Шапка-->
    <@h.header />
    <!--Тело-->
    <section class="about">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <h2>Доступные сервисы перевода</h2>
                    <@t.tableCode "serviceCs"/>
                </div>
                <div class="col-md-4">
                    <@a.addCode "serviceCs" "(одна цифра)"/>
                </div>
            </div>
        </div>
    </section>
    <!--Подвал-->
    <@f.footer />
    <!--Окно редактирования-->
    <@e.edit "/codes/serviceCs/update">
        Введите новое значение (одна цифра): <input class="form-control"
                                                                              type="text"
                                                                              pattern="[0-9]"
                                                                              name="newCode" maxlength="1"
                                                                              required/>
    </@e.edit>
    <!--Окно загрузки-->
    <@l.loading />
    <!--Скрипт для получения id-записи в модальном окне-->
    <@g.getId />
</@c.page>