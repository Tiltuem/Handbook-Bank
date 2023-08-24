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
                    <button type="submit" value="submit" class="btn btn-my">Просмотр всех файлов</button>
                    <br>
                    <button type="submit" value="submit" class="btn btn-my">Назад</button>
                </div>
            </div>
        </div>
    </section>
    <!--Подвал-->
    <@f.footer />

</@c.page>



