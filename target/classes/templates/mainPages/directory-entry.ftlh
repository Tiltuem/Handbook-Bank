<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>
<#import "../parts/footer.ftlh" as f>

<@c.page>
<!--Шапка-->
<@h.header />

<!--Тело-->
<#--Тело-->
<section class="about">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <#if directoryEntries.getTotalPages() != 0>
        <div class="table-responsive">
          <table class="table table-bordered table-hover table-sm">
            <thead>
            <tr>
              <th></th>
              <th>Списки</th>
              <th>ID ЭС</th>
              <th>БИК</th>
              <th>Наименование участника</th>
              <th>Регистрационный порядковый
                номер</th>
              <th>Код страны</th>
              <th>Код территории</th>
              <th>Индекс</th>
              <th>Тип населенного пункта</th>
              <th>Наименование пункта</th>
              <th>Адрес</th>
              <th>БИК головной организации</th>
              <th>Дата включения в состав участников перевода</th>
              <th>Дата исключения в состав участников перевода</th>
              <th>Тип участника перевода</th>
              <th>Доступные сервисы перевода</th>
              <th>Участник обмена</th>
              <th>УИС</th>
              <th>Статус участника</th>
            </tr>
            </thead>
            <tbody>
            <#list directoryEntries.iterator() as directoryEntry>
            <tr>
              <td>
                <form action="/message-${messageId}/directory-entry/delete/${directoryEntry.id}" method="post">
                  <input type="hidden" name="page" value="${page}">
                  <button type="submit" class="bi bi-trash"></button>
                </form>
              </td>
                      <td>
                        <div class="dropdown">
                          <button class="btn btn-my" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="bi bi-plus-square"></i>
                          </button>
                          <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">

                            <div class="row">
                              <div class="col-md-6">
                                <h2>Аккаунты</h2>
                            <div class="table-responsive">
                              <table class="table table-bordered table-hover table-sm">
                                <thead>
                                <tr>
                                  <th>Номер счета</th>
                                  <th>Тип счета в соответствии с нормативом</th>
                                  <th>Контрольный ключ</th>
                                  <th>БИК ПБР</th>
                                  <th>Дата открытия</th>
                                  <th>Дата исключения</th>
                                  <th>Статус счета</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list directoryEntry.accounts as account>
                                <tr>
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
                                </tr>
                                </#list>
                                </tbody>
                              </table>
                            </div>
                            </div>

                              <div class="col-md-3">
                                <h2>Перечень ограничений участника</h2>
                                <div class="table-responsive">
                                  <table class="table table-bordered table-hover table-sm">
                                    <thead>
                                    <tr>
                                      <th>Код ограничения, наложенного на
                                        участника</th>
                                      <th>Дата начала действия ограничения
                                        участника</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#list directoryEntry.participantInfo.restrictionList as restriction>
                                    <tr>
                                      <td>
                                        ${restriction.restrictionCode.code}
                                      </td>
                                      <td>
                                        ${restriction.restrictionDate}
                                      </td>
                                    </tr>
                                    </#list>
                                    </tbody>
                                  </table>
                                </div>
                              </div>

                              <div class="col-md-3">
                                <h2>Перечень БИК (СВИФТ)</h2>
                                <div class="table-responsive">
                                  <table class="table table-bordered table-hover table-sm">
                                    <thead>
                                    <tr>
                                      <th>БИК</th>
                                      <th>Признак использования БИК
                                        (СВИФТ), «по умолчанию»</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#list directoryEntry.swbics as swbic>
                                    <tr>
                                      <td>
                                        ${swbic.swbic}
                                      </td>
                                      <td>
                                        ${swbic.defaultSwbic}
                                      </td>
                                    </tr>
                                    </#list>
                                    </tbody>
                                  </table>
                                </div>
                              </div>



                            </div>
                          </ul>
                        </div>
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
                      <td >
                        <#if directoryEntry.participantInfo.registrationNumber?has_content>
                        ${directoryEntry.participantInfo.registrationNumber}
                      </#if>
                      </td>
                      <td >
                        <#if directoryEntry.participantInfo.countryCode?has_content>
                        ${directoryEntry.participantInfo.countryCode}
                      </#if>
                      </td>
                      <td >
                        ${directoryEntry.participantInfo.region}
                      </td>
                      <td >
                        <#if directoryEntry.participantInfo.index?has_content>
                        ${directoryEntry.participantInfo.index}
                      </#if>
                      </td>
                      <td >
                        <#if directoryEntry.participantInfo.localityType?has_content>
                        ${directoryEntry.participantInfo.localityType}
                      </#if>
                      </td>
                      <td >
                        <#if directoryEntry.participantInfo.localityName?has_content>
                        ${directoryEntry.participantInfo.localityName}
                      </#if>
                      </td>
                      <td >
                        <#if directoryEntry.participantInfo.address?has_content>
                        ${directoryEntry.participantInfo.address}
                      </#if>
                      </td>
                      <td >
                        <#if  directoryEntry.participantInfo.bicParent != 0>
                        ${directoryEntry.participantInfo.bicParent}
                      </#if>
                      </td>
                      <td >
                        ${directoryEntry.participantInfo.dateIn}
                      </td>
                      <td >
                        <#if directoryEntry.participantInfo.dateOut?has_content>
                        ${directoryEntry.participantInfo.dateOut}
                      </#if>
                      </td>
                      <td>
                        ${directoryEntry.participantInfo.participantTypeCode.code}
                      </td>
                      <td >
                        ${directoryEntry.participantInfo.serviceCsCode.code}
                      </td>
                      <td >
                        ${directoryEntry.participantInfo.exchangeParticipantCode.code}
                      </td>
                      <td >
                        ${directoryEntry.participantInfo.uid}
                      </td>
                      <td >
                        ${directoryEntry.participantInfo.participantStatusCode.code}
                      </td>
              <!--<div class="row">
                <div class="col-md-6 col-sm-12">
                  </div>
                      <div class="table-responsive">
                        <table class="table table-bordered table-hover table-sm">
                          <thead>
                            <tr>
                            <th>Номер счета</th>
                            <th>Тип счета в соответствии с нормативом</th>
                              <th>Контрольный ключ</th>
                              <th>БИК ПБР</th>
                              <th>Дата открытия</th>
                              <th>Дата исключения</th>
                              <th>Статус счета</th>
                            </tr>
                          </thead>
                          <tbody>
                          <#list directoryEntry.accounts as account>
                              <tr>
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
                              </tr>
                              </#list>
                          </tbody>
                        </table>
                        </div>
                        -->
            </tr>
            </#list>
            </tbody>
          </table>
          <nav aria-label="Page navigation example">
            <ul class="pagination">
              <li class="page-item"><a class="page-link" href="/message-${messageId}/directory-entry/0">Первая
                страница</a></li>
              <#if page != 0>
              <li class="page-item"><a class="page-link" href="/message-${messageId}/directory-entry/${page-1}">${page}</a>
              </li>
            </#if>
            <li class="page-item active"><a class="page-link" href="#">${page+1}</a></li>
            <#if page != maxPage>
            <li class="page-item"><a class="page-link" href="/message-${messageId}/directory-entry/${page+1}">${page+2}</a>
            </li>
          </#if>
          <li class="page-item"><a class="page-link" href="/message-${messageId}/directory-entry/${maxPage}">Последняя
            страница</a></li>
          </ul>
          </nav>
        </div>
        <#else>
        <h3>Записи отсутствуют</h3>
      </#if>
    </div>
  </div>
  </div>
</section>
<!--Подвал-->
<@f.footer />

</@c.page>
