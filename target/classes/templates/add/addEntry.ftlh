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
                        <div class="row">
                            <div class="col-md-6">
                                <div class="col-25">БИК:</div>
                                <div class="col-75"><@spring.formInput "bicDirectoryEntry.bic"/>
                                    <#if bicDirectoryEntryBindingResult?has_content>
                                        <#list bicDirectoryEntryBindingResult.allErrors as error>
                                            <#if error.field = "bic">
                                                <div class="error">
                                                    <li>${error.defaultMessage}</li>
                                                </div>
                                            </#if>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="col-25">Наименование участника:</div>
                                <div class="col-75">
                                    <@spring.formInput "participantInfo.participantName"/>
                                    <#if participantInfoBindingResult?has_content>
                                        <#list participantInfoBindingResult.allErrors as error>
                                            <#if error.field = "participantName">
                                                <div class="error">
                                                    <li>${error.defaultMessage}</li>
                                                </div>
                                            </#if>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="col-25">Иностранное наименование:</div>
                                <div class="col-75">
                                    <@spring.formInput "participantInfo.englishName"/>
                                    <#if participantInfoBindingResult?has_content>
                                        <#list participantInfoBindingResult.allErrors as error>
                                            <#if error.field = "englishName">
                                                <div class="error">
                                                    <li>${error.defaultMessage}</li>
                                                </div>
                                            </#if>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="col-25">Регистрационный номер:</div>
                                <div class="col-75">
                                    <@spring.formInput "participantInfo.registrationNumber"/>
                                    <#if participantInfoBindingResult?has_content>
                                        <#list participantInfoBindingResult.allErrors as error>
                                            <#if error.field = "registrationNumber">
                                                <div class="error">
                                                    <li>${error.defaultMessage}</li>
                                                </div>
                                            </#if>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-md-6">
                                <div class="col-25">Код страны:</div>
                                <div class="col-75">
                                    <@spring.formInput "participantInfo.countryCode"/>
                                    <#if participantInfoBindingResult?has_content>
                                        <#list participantInfoBindingResult.allErrors as error>
                                            <#if error.field = "countryCode">
                                                <div class="error">
                                                    <li>${error.defaultMessage}</li>
                                                </div>
                                            </#if>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="col-25">Код территории:</div>
                                <div class="col-75">
                                    <@spring.formInput "participantInfo.region"/>
                                    <#if participantInfoBindingResult?has_content>
                                        <#list participantInfoBindingResult.allErrors as error>
                                            <#if error.field = "region">
                                                <div class="error">
                                                    <li>${error.defaultMessage}</li>
                                                </div>
                                            </#if>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="col-25">Индекс:</div>
                                <div class="col-75">
                                    <@spring.formInput "participantInfo.index"/>
                                    <#if participantInfoBindingResult?has_content>
                                        <#list participantInfoBindingResult.allErrors as error>
                                            <#if error.field = "index">
                                                <div class="error">
                                                    <li>${error.defaultMessage}</li>
                                                </div>
                                            </#if>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="col-25">Тип населенного пункта:</div>
                                <div class="col-75">
                                    <@spring.formInput "participantInfo.localityType"/>
                                    <#if participantInfoBindingResult?has_content>
                                        <#list participantInfoBindingResult.allErrors as error>
                                            <#if error.field = "localityType">
                                                <div class="error">
                                                    <li>${error.defaultMessage}</li>
                                                </div>
                                            </#if>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-md-6">
                                <div class="col-25">Наименование пункта:</div>
                                <div class="col-75">
                                    <@spring.formInput "participantInfo.localityName"/>
                                    <#if participantInfoBindingResult?has_content>
                                        <#list participantInfoBindingResult.allErrors as error>
                                            <#if error.field = "localityName">
                                                <div class="error">
                                                    <li>${error.defaultMessage}</li>
                                                </div>
                                            </#if>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="col-25">Адрес:</div>
                                <div class="col-75">
                                    <@spring.formInput "participantInfo.address"/>
                                    <#if participantInfoBindingResult?has_content>
                                        <#list participantInfoBindingResult.allErrors as error>
                                            <#if error.field = "address">
                                                <div class="error">
                                                    <li>${error.defaultMessage}</li>
                                                </div>
                                            </#if>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="col-25">БИК головной организации:</div>
                                <div class="col-75">
                                    <@spring.formInput "participantInfo.bicParent"/>
                                    <#if participantInfoBindingResult?has_content>
                                        <#list participantInfoBindingResult.allErrors as error>
                                            <#if error.field = "bicParent">
                                                <div class="error">
                                                    <li>${error.defaultMessage}</li>
                                                </div>
                                            </#if>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                            <div class="col-md-6">

                                <div class="col-25">УИС:</div>
                                <div class="col-75">
                                    <@spring.formInput "participantInfo.uid"/>
                                    <#if participantInfoBindingResult?has_content>
                                        <#list participantInfoBindingResult.allErrors as error>
                                            <#if error.field = "uid">
                                                <div class="error">
                                                    <li>${error.defaultMessage}</li>
                                                </div>
                                            </#if>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-2">
                                <div class="col-50">Тип участника перевода:</div>

                                <div class="col-50">
                                    <select type="text" name="participantType">
                                        <#list participantTypeCodeList as code>
                                            <option value="${code.code}">${code.code}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="col-50">Доступные сервисы перевода:</div>

                                <div class="col-50">
                                    <select type="text" name="serviceCs">
                                        <#list serviceCsCodeList as code>
                                            <option value="${code.code}">${code.code}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="col-50">Участник обмена:</div>
                                <div class="col-50">
                                    <select type="text" name="exchangeParticipant">
                                        <#list exchangeParticipantCodeList as code>
                                            <option value="${code.code}">${code.code}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>



                            <div class="col-md-2">
                                <div class="col-50">Тип изменения в Справочнике:</div>
                                <div class="col-50">
                                    <#if bicDirectoryEntry.changeTypeCode?has_content>
                                        <select type="text" name="changeType">
                                            <#list changeTypeCodeList as code>
                                                <option value="${code.code}">${code.code}</option>
                                            </#list>
                                        </select>
                                    <#else>
                                        <select type="text" name="changeType">
                                            <#list changeTypeCodeList as code>
                                                <option value="${code.code}">${code.code}</option>
                                            </#list>
                                        </select>
                                    </#if>
                                </div>
                            </div>
                            <div class="col-md-2">

                                <div class="col-50">Статус участника:</div>
                                <div class="col-50">
                                    <select type="text" name="participantStatus">
                                        <#list participantStatusCodeList as code>
                                            <option value="${code.code}">${code.code}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <div class="col-25">Дата включения:</div>
                                <div class="col-75">
                                    <#if participantInfo.dateOut?has_content>
                                        <input type="date" name="dateIn"
                                               value="${participantInfo.dateIn}">
                                    <#else>
                                        <input type="date" name="dateIn">
                                    </#if>
                                    <#if participantInfoBindingResult?has_content>
                                        <#list participantInfoBindingResult.allErrors as error>
                                            <#if error.field = "dateIn">
                                                <div class="error">
                                                    <li>${error.defaultMessage}</li>
                                                </div>
                                            </#if>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="col-25">Дата исключения:</div>

                                <div class="col-75">
                                    <#if participantInfo.dateOut?has_content>
                                        <input type="date" name="dateOut"
                                               value="${participantInfo.dateOut}">
                                    <#else>
                                        <input type="date" name="dateOut">
                                    </#if>
                                </div>
                            </div>
                        </div>
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
                        </div>
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