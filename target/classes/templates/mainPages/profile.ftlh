<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>
<#import "../parts/footer.ftlh" as f>
<#import "../parts/getIdWithInput.ftlh" as g>

<@c.page "../../css/styleDirectories.css">
    <!--Шапка-->
    <@h.header />

    <!--Тело-->
    <section class="about">
        <div class="container">
            <div class="row">
                <h2>Профиль</h2>
                <h4>
                Имя: ${user.username}
                <br>
                Почта: ${user.email}
                <br>
                Роли:<ul>
                    <#list user.roles as role>
                        <li>${role.name}</li>
                    </#list>
                    </ul>
                </h4>
                <a class="btn btn-outline-light"  href="/logout">Выйти</a>
            </div>
        </div>
    </section>
    <!--Подвал-->
    <@f.footer />
    <@g.getId />

</@c.page>