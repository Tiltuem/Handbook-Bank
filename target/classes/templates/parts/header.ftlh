<#macro header>
    <#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
    <header>
    <div class="container">
        <div class="row">
            <div class="col-md-auto">
              <div class="header-icon"><i class="bi bi-bank"></i></div>
            </div>
            <div class="col">
                <h4 class="text-white">Справочник сведений ЦБ РФ о
                    кредитно-финансовых учреждениях</h4>
            </div>

            <div class="col-md-auto">
                <@security.authorize access="isAuthenticated()">
                    <a class="btn btn-outline-light"  href="/profile">Профиль</a>
                </@security.authorize>
                <@security.authorize access="!isAuthenticated()">
                    <a class="btn btn-outline-light"  href="/registration">Регистрация</a>
                </@security.authorize>

            </div>
            <div class="col-md-auto">
                <a class="btn btn-outline-light"  href="/">Главная</a>
            </div>
        </div>
    </div>
</header>
</#macro>