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
                    <h2>Тип изменения в справочнике</h2>
                    <@t.tableCode "changeType"/>
                </div>
                <div class="col-md-4">
                    <@a.addCode "changeType" "из 4 английских букв верхнего регистра"/>
                </div>
            </div>
        </div>
    </section>
    <!--Подвал-->
    <@f.footer />
    <!--Окно редактирования-->
    <@e.edit "/codes/changeType/update">
        Введите новое значение из 4 английских букв верхнего регистра: <input class="form-control"
                                                                              type="text"
                                                                              pattern="[A-Z]{4}"
                                                                              name="newCode" maxlength="4"
                                                                              required/>
    </@e.edit>
    <!--Окно загрузки-->
    <@l.loading />
    <!--Скрипт для получения id-записи в модальном окне-->
    <@g.getId />
</@c.page>