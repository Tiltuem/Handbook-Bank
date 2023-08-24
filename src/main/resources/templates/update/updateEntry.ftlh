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
                    <h2>Редактирование записи</h2>
                    <form action="/message-${messageId}/directory-entry/update" method="patch" >
                            <input type="hidden" name="id" value="${bicDirectoryEntry.id}">
                            БИК: <@spring.formInput "bicDirectoryEntry.bic"/>
                            <#if bindingResult?has_content>
                                <#list bindingResult.allErrors as error>
                                    <div class="error"><li>${error.defaultMessage}</li></div>
                                </#list>
                            </#if>
                            <br>
                            Наименование участника: <input type="text" name="participantInfo.participantName"
                                                           value="${bicDirectoryEntry.participantInfo.participantName}">
                            <br>
                            Иностранное наименование:
                            <#if bicDirectoryEntry.participantInfo.englishName?has_content>
                                <input type="text" name="participantInfo.englishName"
                                       value="${bicDirectoryEntry.participantInfo.englishName}">
                            <#else>
                                <input type="text" name="participantInfo.englishName">
                            </#if>
                            <br>
                            Регистрационный номер:
                            <#if bicDirectoryEntry.participantInfo.registrationNumber?has_content>
                                <input type="text" name="participantInfo.registrationNumber"
                                       value="${bicDirectoryEntry.participantInfo.registrationNumber}">
                            <#else>
                                <input type="text" name="participantInfo.registrationNumber">
                            </#if>
                            <br>
                            Код страны:
                            <#if bicDirectoryEntry.participantInfo.countryCode?has_content>
                                <input type="text" name="participantInfo.countryCode"
                                       value="${bicDirectoryEntry.participantInfo.countryCode}">
                            <#else>
                                <input type="text" name="participantInfo.countryCode">
                            </#if>
                            <br>
                            Код территории: <input type="text" name="participantInfo.region"
                                                   value="${bicDirectoryEntry.participantInfo.region}">

                            <br>
                            Индекс:
                            <#if bicDirectoryEntry.participantInfo.index?has_content>
                                <input type="text" name="participantInfo.index"
                                       value="${bicDirectoryEntry.participantInfo.index}">
                            <#else>
                                <input type="text" name="participantInfo.index">
                            </#if>
                            <br>
                            Тип населенного пункта:
                            <#if bicDirectoryEntry.participantInfo.localityType?has_content>
                                <input type="text" name="participantInfo.localityType"
                                       value="${bicDirectoryEntry.participantInfo.localityType}">
                            <#else>
                                <input type="text" name="participantInfo.localityType">
                            </#if>
                            <br>
                            Наименование пункта:
                            <#if bicDirectoryEntry.participantInfo.localityName?has_content>
                                <input type="text" name="participantInfo.localityName"
                                       value="${bicDirectoryEntry.participantInfo.localityName}">
                            <#else>
                                <input type="text" name="participantInfo.localityName">
                            </#if>
                            <br>
                            Адрес:
                            <#if bicDirectoryEntry.participantInfo.address?has_content>
                                <input type="text" name="participantInfo.address"
                                       value="${bicDirectoryEntry.participantInfo.address}">
                            <#else>
                                <input type="text" name="participantInfo.address">
                            </#if>
                            <br>
                            БИК головной организации:
                            <#if  bicDirectoryEntry.participantInfo.bicParent?has_content>
                                <input type="text" name="participantInfo.bicParent"
                                       value="${bicDirectoryEntry.participantInfo.bicParent}">
                            <#else>
                                <input type="text" name="participantInfo.bicParent">
                            </#if>
                            <br>
                            Дата включения: <input type="date" name="participantInfo.dateIn"
                                                   value="${bicDirectoryEntry.participantInfo.dateIn}">

                            <br>
                            Дата исключения:
                            <#if bicDirectoryEntry.participantInfo.dateOut?has_content>
                                <input type="date" name="participantInfo.dateOut"
                                       value="${bicDirectoryEntry.participantInfo.dateOut}">
                            <#else>
                                <input type="date" name="participantInfo.dateOut">
                            </#if>
                            <br>
                            Тип участника перевода:
                            <select type="text" name="participantInfo.participantType">
                                <#list participantTypeCodeList as code>
                                    <option value="${code.code}">${code.code}</option>
                                </#list>
                            </select>

                            <br>
                            Доступные сервисы перевода:
                            <select type="text" name="participantInfo.serviceCs">
                                <#list serviceCsCodeList as code>
                                    <option value="${code.code}">${code.code}</option>
                                </#list>
                            </select>
                            <br>
                            Участник обмена:
                            <select type="text" name="participantInfo.exchangeParticipant">
                                <#list exchangeParticipantCodeList as code>
                                    <option value="${code.code}">${code.code}</option>
                                </#list>
                            </select>
                            <br>
                            УИС: <input type="text" name="participantInfo.uid"
                                        value=${bicDirectoryEntry.participantInfo.uid}>

                            <br>
                            Тип изменения в Справочнике:
                            <#if bicDirectoryEntry.changeTypeCode?has_content>
                                <select type="text" name="participantInfo.changeType">
                                    <#list changeTypeCodeList as code>
                                        <option value="${code.code}">${code.code}</option>
                                    </#list>
                                </select>
                            <#else>
                                <select type="text" name="participantInfo.changeType">
                                    <#list changeTypeCodeList as code>
                                        <option value="${code.code}">${code.code}</option>
                                    </#list>
                                </select>
                            </#if>
                            Статус участника:
                            <select type="text" name="participantInfo.participantStatus">
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

</@c.page>