<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Справочник ЦБ РФ</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" href="../../css/styleDirectories.css">
</head>
<body>
<#--Шапка-->
<header>
    <div class="container">
        <div class="row">
            <div class="col-md-auto">
                <div class="header-icon"><i class="bi bi-bank" ></i></div>
            </div>
            <div class="col">
                <h4 class="text-white">Справочник сведений ЦБ РФ о
                    кредитно-финансовых учреждениях</h4>
            </div>
            <div class="col-md-auto">
                <a class="btn btn-outline-light"  href="/registration">Авторизация</a>
            </div>
            <div class="col-md-auto">
                <a class="btn btn-outline-light"  href="/">Главная</a>
            </div>
        </div>
    </div>
</header>
<#--Тело-->
<section class="about">
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-sm-12">
                <div class="accordion accordion-flush" id="accordion1">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading1">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapse1" aria-expanded="false" aria-controls="collapse1">
                                Причина создания эс
                            </button>
                        </h2>
                        <div id="collapse1" class="accordion-collapse collapse" aria-labelledby="heading1"
                             data-bs-parent="#accordion1">
                            <div class="accordion-body fst-italic">
                                <a href="/codes/creationReason/0">Таблица значений</a>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading2">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapse2" aria-expanded="false" aria-controls="collapse2">
                                Вид представления информации
                            </button>
                        </h2>
                        <div id="collapse2" class="accordion-collapse collapse" aria-labelledby="heading2"
                             data-bs-parent="#accordion1">
                            <div class="accordion-body fst-italic">
                                <a href="/codes/informationType/0">Таблица значений</a>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header fst-italic" id="heading3">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapse3" aria-expanded="false" aria-controls="collapse3">
                                Тип счета в соответствии с нормативом
                            </button>
                        </h2>
                        <div id="collapse3" class="accordion-collapse collapse" aria-labelledby="heading3"
                             data-bs-parent="#accordion1">
                            <div class="accordion-body fst-italic">
                                <a href="/codes/regulationAccountType/0">Таблица значений</a>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading4">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapse4" aria-expanded="false" aria-controls="collapse4">
                                Тип изменения в справочнике
                            </button>
                        </h2>
                        <div id="collapse4" class="accordion-collapse collapse" aria-labelledby="heading4"
                             data-bs-parent="#accordion1">
                            <div class="accordion-body fst-italic">
                                <a href="/codes/changeType/0">Таблица значений</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-12">
                <div class="accordion accordion-flush" id="accordion2">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading5">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapse5" aria-expanded="false" aria-controls="collapse5">
                                Тип участника перевода
                            </button>
                        </h2>
                        <div id="collapse5" class="accordion-collapse collapse" aria-labelledby="heading5"
                             data-bs-parent="#accordion2">
                            <div class="accordion-body fst-italic">
                                <a href="/codes/participantType/0">Таблица значений</a>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading6">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapse6" aria-expanded="false" aria-controls="collapse6">
                                Доступные сервисы перевода
                            </button>
                        </h2>
                        <div id="collapse6" class="accordion-collapse collapse" aria-labelledby="heading6"
                             data-bs-parent="#accordion2">
                            <div class="accordion-body fst-italic">
                                <a href="/codes/serviceCs/0">Таблица значений</a>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading7">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapse7" aria-expanded="false" aria-controls="collapse7">
                                Участник обмена
                            </button>
                        </h2>
                        <div id="collapse7" class="accordion-collapse collapse" aria-labelledby="heading7"
                             data-bs-parent="#accordion2">
                            <div class="accordion-body fst-italic">
                                <a href="/codes/exchangeParticipant/0">Таблица значений</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-12">
                <div class="accordion accordion-flush" id="accordion3">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading8">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapse8" aria-expanded="false" aria-controls="collapse8">
                                Ограничения сервисов перевода
                            </button>
                        </h2>
                        <div id="collapse8" class="accordion-collapse collapse" aria-labelledby="heading8"
                             data-bs-parent="#accordion3">
                            <div class="accordion-body fst-italic">
                                <a href="/codes/restriction/0">Таблица значений</a>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading9">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapse9" aria-expanded="false" aria-controls="collapse9">Статус
                                участника
                            </button>
                        </h2>
                        <div id="collapse9" class="accordion-collapse collapse" aria-labelledby="heading9"
                             data-bs-parent="#accordion3">
                            <div class="accordion-body fst-italic">
                                <a href="/codes/participantStatus/0">Таблица значений</a>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading10">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapse10" aria-expanded="false" aria-controls="collapse10">
                                Ограничения операций по счету
                            </button>
                        </h2>
                        <div id="collapse10" class="accordion-collapse collapse" aria-labelledby="heading10"
                             data-bs-parent="#accordion3">
                            <div class="accordion-body fst-italic">
                                <a href="/codes/accountRestriction/0">Таблица значений</a>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading11">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapse11" aria-expanded="false" aria-controls="collapse11">
                                Статус счета
                            </button>
                        </h2>
                        <div id="collapse11" class="accordion-collapse collapse" aria-labelledby="heading11"
                             data-bs-parent="#accordion3">
                            <div class="accordion-body fst-italic">
                                <a href="/codes/accountStatus/0">Таблица значений</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</section>
<#--Подвал-->
<footer id="footer">
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <a class="btn btn-outline-light" target="_blank" href="https://vk.com/tony996"><i
                            class="bi bi-bootstrap"></i></a>
                <a class="btn btn-outline-light" target="_blank" href="https://github.com/Tiltuem"><i
                            class="bi bi-github"></i></a>
                <a class="btn btn-outline-light" target="_blank" href="mailto:dekerilljr@gmail.com"><i
                            class="bi bi-envelope-at"></i></a>
            </div>
        </div>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
</body>
</html>