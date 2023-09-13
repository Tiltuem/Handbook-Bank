<#import "parts/common.ftlh" as c>
<#import "parts/header.ftlh" as h>
<#import "parts/footer.ftlh" as f>

<@c.page "../../css/error.css">
    <!--Шапка-->
    <@h.header />

<#--Тело-->
    <section class="about">
        <div class="container">
            <div class="row">
                <div class="baG">
                    <h3>Ooooops!</h3>
                    <p>Страница не найдена</p>

                            <a class="btn btn-my" onclick="history.back()">Назад</a>
                            <a class="btn btn-my" href="/">На главную</a>

                </div>
            </div>
    </section>
    <!--Подвал-->
    <@f.footer />

</@c.page>



