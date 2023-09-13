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
                <div class="col-25">
                    Имя: ${user.username}
                    <br>
                    Почта: ${user.email}
                    <br>
                    Роли:
                    <ul>
                        <#list user.roles as role>
                            <#if role.name = "ROLE_ADMIN">
                                <li>Администратор</li>
                            <#else>
                                <li>Пользователь</li>
                            </#if>
                        </#list>
                    </ul>
                    <div class="col-md-auto">
                        <a class="btn btn-my" href="/logout">Выйти</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--Подвал-->
    <@f.footer />
    <@g.getId />
    <#if success>
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Добро пожаловать, ${user.username}!</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                    </div>
                </div>
            </div>
        </div>
        <script>
            window.addEventListener('load', () => {
                new bootstrap.Modal(document.getElementById('exampleModal')).show();
            });
        </script>
    </#if>
</@c.page>