<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>
<#import "../parts/footer.ftlh" as f>
<#import "../parts/getIdWithInput.ftlh" as g>
<#import "../parts/pagination.ftlh" as p>

<@c.page "../../css/styleDirectories.css">
    <!--Шапка-->
    <@h.header />

    <!--Тело-->
    <section class="about">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                        <h2>Записи в Справочнике БИК</h2>
                        <br>
                        <br>
                        <form action="/message-${messageId}/directory-entry/${page}-search" method="get">
                            <div class="search">
                                <input placeholder="Введите значение" type="text" name="value">
                                <input type="hidden" name="page" value="${page}">
                                <button type="submit" data-bs-toggle="modal" data-bs-target="#loadingModal">Поиск
                                </button>
                            </div>
                            <select name="column" class="btn btn-outline-light">
                                <option disabled>Выберите параметр</option>
                                <option value="bic">БИК</option>
                                <option value="participantInfo.participantName">Наименование участника</option>
                                <option value="participantInfo.englishName">Иностранное наименование</option>
                                <option value="participantInfo.registrationNumber">Регистрационный номер</option>
                                <option value="participantInfo.countryCode">Код страны</option>
                                <option value="participantInfo.region">Код территории</option>
                                <option value="participantInfo.index">Индекс</option>
                                <option value="participantInfo.localityType">Тип населенного пункта</option>
                                <option value="participantInfo.localityName">Наименование пункта</option>
                                <option value="participantInfo.address">Адрес</option>
                                <option value="participantInfo.bicParent">БИК головной организации</option>
                                <option value="participantInfo.participantTypeCode.code">Тип участника перевода</option>
                                <option value="participantInfo.serviceCsCode.code">Доступные сервисы перевода</option>
                                <option value="participantInfo.uid">УИС</option>
                                <option value="participantInfo.participantStatusCode.code">Статус участника</option>
                                <option value="changeTypeCode.code">Тип изменения в Справочнике</option>
                            </select>
                            <br>
                            <br>
                            <h5>
                                Дата включения:
                            </h5>
                            с <input type="date" name="dateFrom"> по <input type="date" name="dateBy">
                            <div class="checkbox">
                                <input id="checkbox1" class="checkbox__input" type="checkbox" name="deleted">
                                <label for="checkbox1" class="checkbox__label">
                                    <span class="checkbox__custom"></span>
                                    Показать удалённые
                                </label>
                            </div>
                            <#if search??>
                                <br>
                                <a class="btn btn-my" href="/message-${messageId}/directory-entry/${page}">Отменить поиск</a>
                            </#if>
                        </form>
                    <#if entries.getTotalPages() != 0>
                        <div class="row">
                            <div class="col-md-2">
                                <form action="/message-${messageId}/directory-entry/new-entry" method="get">
                                    <input type="hidden" name="page" value="${page}">
                                    <button type="submit" class="btn btn-my">Добавить</button>
                                </form>
                            </div>
                            <div class="col-md-2">
                                <form action="/message-${messageId}/directory-entry/edit-entry" method="get">
                                    <input name="id" type="hidden" id="id-entry">
                                    <input type="hidden" name="page" value="${page}">
                                    <button type="submit" class="btn btn-my" id="btn-entry" disabled="disabled">
                                        Редактировать
                                    </button>
                                </form>
                            </div>
                        </div>
                        <br>
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover table-sm rounded">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th></th>
                                    <th>ID ЭС</th>
                                    <th>БИК</th>
                                    <th>Наименование участника</th>
                                    <th>Иностранное наименование</th>
                                    <th>Регистрационный номер</th>
                                    <th>Код страны</th>
                                    <th>Код территории</th>
                                    <th>Индекс</th>
                                    <th>Тип населенного пункта</th>
                                    <th>Наименование пункта</th>
                                    <th>Адрес</th>
                                    <th>БИК головной организации</th>
                                    <th>Дата включения</th>
                                    <th>Дата исключения</th>
                                    <th>Тип участника перевода</th>
                                    <th>Доступные сервисы перевода</th>
                                    <th>Участник обмена</th>
                                    <th>УИС</th>
                                    <th>Статус участника</th>
                                    <th>Тип изменения в Справочнике</th>
                                    <th>Кем создано</th>
                                    <th>Когда создано</th>
                                    <th>Кем изменено</th>
                                    <th>Когда изменено</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list entries.iterator() as directoryEntry>
                                    <tr onclick="getIdEntry(${directoryEntry.id})">
                                        <td>
                                            <#if !directoryEntry.deleted>
                                                <button class="btn btn-light" type="button" data-bs-toggle="collapse"
                                                        data-bs-target="#collapse-${directoryEntry.id}"
                                                        aria-expanded="false" aria-controls="collapseExample"><i
                                                            class="bi bi-plus"></i></button>
                                                <#else>
                                                    <form action="/message-${messageId}/directory-entry/recovery/${directoryEntry.id}" method="post">
                                                        <input type="hidden" name="page" value="${page}">
                                                        <button type="submit" class="btn btn-light" data-bs-toggle="modal" data-bs-target="#loadingModal">Восстановить
                                                        </button>
                                                    </form>
                                            </#if>
                                        </td>
                                        <td>
                                            <form action="/message-${messageId}/directory-entry/delete/${directoryEntry.id}"
                                                  method="post">
                                                <input type="hidden" name="page" value="${page}">
                                                <button type="submit" class="btn-delete" data-bs-toggle="modal"
                                                        data-bs-target="#loadingModal"><i class="bi bi-trash"></i>
                                                </button>
                                            </form>
                                        </td>
                                        <td>
                                            ${messageId}
                                        </td>
                                        <td>
                                            ${directoryEntry.bic}
                                        </td>
                                        <td>
                                            ${directoryEntry.participantInfo.participantName}
                                        </td>
                                        <td>
                                            <#if directoryEntry.participantInfo.englishName?has_content>
                                                ${directoryEntry.participantInfo.englishName}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if directoryEntry.participantInfo.registrationNumber?has_content>
                                                ${directoryEntry.participantInfo.registrationNumber}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if directoryEntry.participantInfo.countryCode?has_content>
                                                ${directoryEntry.participantInfo.countryCode}
                                            </#if>
                                        </td>
                                        <td>
                                            ${directoryEntry.participantInfo.region}
                                        </td>
                                        <td>
                                            <#if directoryEntry.participantInfo.index?has_content>
                                                ${directoryEntry.participantInfo.index}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if directoryEntry.participantInfo.localityType?has_content>
                                                ${directoryEntry.participantInfo.localityType}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if directoryEntry.participantInfo.localityName?has_content>
                                                ${directoryEntry.participantInfo.localityName}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if directoryEntry.participantInfo.address?has_content>
                                                ${directoryEntry.participantInfo.address}
                                            </#if>
                                        </td>
                                        <td>
                                            <#if  directoryEntry.participantInfo.bicParent?has_content>
                                                ${directoryEntry.participantInfo.bicParent}
                                            </#if>
                                        </td>
                                        <td>
                                            ${directoryEntry.participantInfo.dateIn}
                                        </td>
                                        <td>
                                            <#if directoryEntry.participantInfo.dateOut?has_content>
                                                ${directoryEntry.participantInfo.dateOut}
                                            </#if>
                                        </td>
                                        <td>
                                            ${directoryEntry.participantInfo.participantTypeCode.code}
                                        </td>
                                        <td>
                                            ${directoryEntry.participantInfo.serviceCsCode.code}
                                        </td>
                                        <td>
                                            ${directoryEntry.participantInfo.exchangeParticipantCode.code}
                                        </td>
                                        <td>
                                            ${directoryEntry.participantInfo.uid}
                                        </td>
                                        <td>
                                            ${directoryEntry.participantInfo.participantStatusCode.code}
                                        </td>
                                        <td>
                                            <#if directoryEntry.changeTypeCode?has_content>
                                                ${directoryEntry.changeTypeCode.code}
                                            </#if>
                                        </td>
                                        <td>
                                            ${directoryEntry.createdBy}
                                        </td>
                                        <td>
                                            ${directoryEntry.createdDate}
                                        </td>
                                        <td>
                                            ${directoryEntry.modifiedBy}
                                        </td>
                                        <td>
                                            ${directoryEntry.modifiedDate}
                                        </td>
                                    </tr>
                                    <tr class="collapse out" id="collapse-${directoryEntry.id}">
                                        <td colspan="20">
                                            <div class="col-md-8">
                                                <div><h5>Аккаунты</h5></div>
                                                <div class="row">
                                                    <div class="col-md-auto">
                                                        <form action="/message-${messageId}/entry-${directoryEntry.id}/new-account"
                                                              method="get">
                                                            <input type="hidden" name="page" value="${page}">
                                                            <button type="submit" class="btn btn-light">Добавить
                                                            </button>
                                                        </form>
                                                    </div>
                                                    <div class="col-md-auto">
                                                        <form action="/message-${messageId}/entry-${directoryEntry.id}/account-edit"
                                                              method="get">
                                                            <input name="id" type="hidden" id="id-account">
                                                            <input type="hidden" name="page" value="${page}">
                                                            <button type="submit" class="btn btn-light" id="btn-account"
                                                                    disabled="disabled">Редактировать
                                                            </button>
                                                        </form>
                                                    </div>
                                                </div>
                                                <div class="table-responsive">
                                                    <table class="table table-my table-striped table-primary table-sm">
                                                        <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th>Номер счета</th>
                                                            <th>Тип счета</th>
                                                            <th>Контрольный ключ</th>
                                                            <th>БИК ПБР</th>
                                                            <th>Дата открытия</th>
                                                            <th>Дата исключения</th>
                                                            <th>Статус счета</th>
                                                            <th>Кем создано</th>
                                                            <th>Когда создано</th>
                                                            <th>Кем изменено</th>
                                                            <th>Когда изменено</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <#if directoryEntry.accounts?has_content>
                                                            <#list directoryEntry.accounts as account>
                                                                <#if !account.deleted>
                                                                    <tr onclick="getIdAccount(${account.id})">
                                                                        <td>
                                                                            <form action="/message-${messageId}/entry-${directoryEntry.id}/account-delete/${account.id}"
                                                                                  method="post">
                                                                                <input type="hidden" name="page"
                                                                                       value="${page}">
                                                                                <button type="submit"
                                                                                        class="btn-delete" data-bs-toggle="modal"
                                                                                        data-bs-target="#loadingModal"><i
                                                                                            class="bi bi-trash"></i>
                                                                                </button>
                                                                            </form>
                                                                        </td>
                                                                        <td>
                                                                            ${account.accountNumber}
                                                                        </td>
                                                                        <td>
                                                                            ${account.regulationAccountTypeCode.code}
                                                                        </td>
                                                                        <td>
                                                                            ${account.controlKey}
                                                                        </td>
                                                                        <td>
                                                                            ${account.bicCbr}
                                                                        </td>
                                                                        <td>
                                                                            ${account.dateIn}
                                                                        </td>
                                                                        <td>
                                                                            <#if account.dateOut?has_content>
                                                                                ${account.dateOut}
                                                                            </#if>
                                                                        </td>
                                                                        <td>
                                                                            ${account.accountStatusCode.code}
                                                                        </td>
                                                                        <td>
                                                                            ${account.createdBy}
                                                                        </td>
                                                                        <td>
                                                                            ${account.createdDate}
                                                                        </td>
                                                                        <td>
                                                                            ${account.modifiedBy}
                                                                        </td>
                                                                        <td>
                                                                            ${account.modifiedDate}
                                                                        </td>
                                                                    </tr>
                                                                </#if>
                                                            </#list>
                                                        </#if>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <h5>Перечень ограничений участника</h5>
                                                <div class="row">
                                                    <div class="col-md-auto">
                                                        <form action="/message-${messageId}/entry-${directoryEntry.id}/new-restriction-list"
                                                              method="get">
                                                            <input type="hidden" name="page" value="${page}">
                                                            <button type="submit" class="btn btn-light">
                                                                Добавить
                                                            </button>
                                                        </form>
                                                    </div>
                                                    <div class="col-md-auto">
                                                        <form action="/message-${messageId}/entry-${directoryEntry.id}/restriction-list-edit"
                                                              method="get">
                                                            <input name="id" type="hidden" id="id-restriction-list">
                                                            <input type="hidden" name="page" value="${page}">
                                                            <button type="submit" class="btn btn-light"
                                                                    id="btn-restriction-list" disabled="disabled">
                                                                Редактировать
                                                            </button>
                                                        </form>
                                                    </div>
                                                </div>
                                                <div class="table-responsive">
                                                    <table class="table table-my table-striped table-primary table-sm">
                                                        <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th>Код ограничения
                                                            </th>
                                                            <th>Дата начала действия ограничения
                                                            </th>
                                                            <th>Кем создано</th>
                                                            <th>Когда создано</th>
                                                            <th>Кем изменено</th>
                                                            <th>Когда изменено</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <#if directoryEntry.participantInfo.restrictionList?has_content>
                                                            <#list directoryEntry.participantInfo.restrictionList as restriction>
                                                                <#if !restriction.deleted>
                                                                    <tr onclick="getIdRstrList(${restriction.id})">
                                                                        <td>
                                                                            <form action="/message-${messageId}/entry-${directoryEntry.id}/restriction-list-delete/${restriction.id}"
                                                                                  method="post">
                                                                                <input type="hidden" name="page"
                                                                                       value="${page}">
                                                                                <button type="submit"
                                                                                        class="btn-delete"  data-bs-toggle="modal"
                                                                                        data-bs-target="#loadingModal"><i
                                                                                            class="bi bi-trash"></i>
                                                                                </button>
                                                                            </form>
                                                                        </td>
                                                                        <td>
                                                                            ${restriction.restrictionCode.code}
                                                                        </td>
                                                                        <td>
                                                                            ${restriction.restrictionDate}
                                                                        </td>
                                                                        <td>
                                                                            ${restriction.createdBy}
                                                                        </td>
                                                                        <td>
                                                                            ${restriction.createdDate}
                                                                        </td>
                                                                        <td>
                                                                            ${restriction.modifiedBy}
                                                                        </td>
                                                                        <td>
                                                                            ${restriction.modifiedDate}
                                                                        </td>
                                                                    </tr>
                                                                </#if>
                                                            </#list>
                                                        </#if>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <h5>Перечень БИК (СВИФТ)</h5>
                                                <div class="row">
                                                    <div class="col-md-auto">
                                                        <form action="/message-${messageId}/entry-${directoryEntry.id}/new-swbics"
                                                              method="get">
                                                            <input type="hidden" name="page" value="${page}">
                                                            <button type="submit" class="btn btn-light">Добавить
                                                            </button>
                                                        </form>
                                                    </div>
                                                    <div class="col-md-auto">
                                                        <form action="/message-${messageId}/entry-${directoryEntry.id}/swbics-edit"
                                                              method="get">
                                                            <input name="id" type="hidden" id="id-swbics">
                                                            <input type="hidden" name="page" value="${page}">
                                                            <button type="submit" class="btn btn-light" id="btn-swbics"
                                                                    disabled="disabled">Редактировать
                                                            </button>
                                                        </form>
                                                    </div>
                                                </div>
                                                <div class="table-responsive">
                                                    <table class="table table-striped table-primary table-sm">
                                                        <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th>БИК</th>
                                                            <th>Признак использования БИК
                                                                (СВИФТ)
                                                            </th>
                                                            <th>Кем создано</th>
                                                            <th>Когда создано</th>
                                                            <th>Кем изменено</th>
                                                            <th>Когда изменено</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <#if directoryEntry.SWBICs?has_content>
                                                            <#list directoryEntry.SWBICs as SWBIC>
                                                                <#if !SWBIC.deleted>
                                                                    <tr onclick="getIdSWBICs(${SWBIC.id})">
                                                                        <td>
                                                                            <form action="/message-${messageId}/entry-${directoryEntry.id}/swbics-delete/${SWBIC.id}"
                                                                                  method="post">
                                                                                <input type="hidden" name="page"
                                                                                       value="${page}">
                                                                                <button type="submit"
                                                                                        class="btn-delete"  data-bs-toggle="modal"
                                                                                        data-bs-target="#loadingModal"><i
                                                                                            class="bi bi-trash"></i>
                                                                                </button>
                                                                            </form>
                                                                        </td>
                                                                        <td>
                                                                            ${SWBIC.swbic}
                                                                        </td>
                                                                        <td>
                                                                            <#if SWBIC.defaultSWBIC>
                                                                                +
                                                                            <#else>
                                                                                -
                                                                            </#if>
                                                                        </td>
                                                                        <td>
                                                                            ${SWBIC.createdBy}
                                                                        </td>
                                                                        <td>
                                                                            ${SWBIC.createdDate}
                                                                        </td>
                                                                        <td>
                                                                            ${SWBIC.modifiedBy}
                                                                        </td>
                                                                        <td>
                                                                            ${SWBIC.modifiedDate}
                                                                        </td>
                                                                    </tr>
                                                                </#if>
                                                            </#list>
                                                        </#if>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                        <@p.pagination "message-${messageId}/directory-entry" entries />
                    <#else>
                        <h3>Записи отсутствуют</h3>
                    </#if>
                </div>
            </div>
        </div>
    </section>
    <!--Подвал-->
    <@f.footer />
    <!--Скрипт для получения id-записи в модальном окне-->
    <@g.getId />

</@c.page>
