<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>
<#import "../parts/footer.ftlh" as f>
<#import "../parts/loadingModal.ftlh" as l>

<#import "/spring.ftl" as spring />
<@c.page "../../css/styleDirectories.css">
    <!--Шапка-->
    <@h.header />

    <!--Тело-->
    <section class="about">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Редактирование записи</h2>

                    <form action="/message-${messageId}/directory-entry/add" method="post">
                            БИК: <input type="text" name="bic">
                            <br>
                            Наименование участника: <input type="text" name="participantName">
                            <br>
                            Иностранное наименование: <input type="text" name="englishName">

                            <br>
                            Регистрационный номер: <input type="text" name="registrationNumber">

                            <br>
                            Код страны: <input type="text" name="countryCode">

                            <br>
                            Код территории: <input type="text" name="region">

                            <br>
                            Индекс: <input type="text" name="index">
                            <br>
                            Тип населенного пункта: <input type="text" name="localityType">
                            <br>
                            Наименование пункта: <input type="text" name="localityName">

                            <br>
                            Адрес: <input type="text" name="address">

                            <br>
                            БИК головной организации: <input type="text" name="bicParent">

                            <br>
                            Дата включения: <input type="date" name="dateIn">

                            <br>
                            Дата исключения: <input type="date" name="dateOut">

                            <br>
                            Тип участника перевода:
                            <select type="text" name="participantType">
                                <#list participantTypeCodeList as code>
                                    <option value="${code.code}">${code.code}</option>
                                </#list>
                            </select>

                            <br>
                            Доступные сервисы перевода:
                            <select type="text" name="serviceCs">
                                <#list serviceCsCodeList as code>
                                    <option value="${code.code}">${code.code}</option>
                                </#list>
                            </select>
                            <br>
                            Участник обмена:
                            <select type="text" name="exchangeParticipant">
                                <#list exchangeParticipantCodeList as code>
                                    <option value="${code.code}">${code.code}</option>
                                </#list>
                            </select>
                            <br>
                            УИС: <input type="number" name="uid">

                            <br>
                            Тип изменения в Справочнике:
                                <select type="text" name="changeType">
                                    <#list changeTypeCodeList as code>
                                        <option value="${code.code}">${code.code}</option>
                                    </#list>
                                </select>

                            Статус участника:
                            <select type="text" name="participantStatus">
                                <#list participantStatusCodeList as code>
                                    <option value="${code.code}">${code.code}</option>
                                </#list>
                            </select>
                            <br>
                        <input type="hidden" name="page" value="${page}">
                            <button type="submit" class="btn-my">Сохранить</button>

                    </form>
                </div>
            </div>
        </div>
    </section>
    <!--Подвал-->
    <@f.footer />
    <!--Окно загрузки-->
    <@l.loading />
</@c.page>