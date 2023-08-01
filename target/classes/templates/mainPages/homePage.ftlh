<#import "../parts/common.ftlh" as c>
<#import "../parts/footer.ftlh" as f>

<@c.page "../../css/styleHomePage.css">
<#--Шапка-->
<header>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="header-icon"><i class="bi bi-bank"></i></div>
                <h1 class="text-center text-white">Справочник сведений ЦБ РФ о кредитно-финансовых учреждениях</h1>
            </div>
        </div>
    </div>
</header>
<#--Тело-->
<section class="about">
    <div class="container">
        <div class="row">
            <div class="col-xl-4 col-md-6 col-sm-12">
                <a href="/import/0"><div class="itd_circle"><i class="bi bi-filetype-xml"></i></div></a>
                <h5 class="text-center">
                    <a class="btn btn-my" href="/import/0">Импорт справочника БИК</a>
                </h5>
                <div class="line"></div>
            </div>
            <div class="col-xl-4 col-md-6 col-sm-12">
                <a href="/messages/0"><div class="itd_circle"><i class="bi bi-folder"></i></div></a>
                <h5 class="text-center">
                    <a class="btn btn-my" href="/messages/0">Просмотр справочников</a>
                </h5>
                <div class="line"></div>
            </div>
            <div class="col-xl-4 col-md-6 col-sm-12">
                <a href="/codes"><div class="itd_circle"><i class="bi bi-list-ul"></i></div></a>
                <h5 class="text-center">
                    <a class="btn btn-my" href="/codes">Просмотр кодовых значений</a>
                </h5>
                <div class="line"></div>
            </div>
        </div>
    </div>
</section>
<!--Подвал-->
<@f.footer />

</@c.page>