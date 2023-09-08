<#import "../parts/common.ftlh" as c>
<#import "../parts/header.ftlh" as h>
<#import "../parts/footer.ftlh" as f>
<#import "../parts/loadingModal.ftlh" as l>

<#import "/spring.ftl" as spring />
<#assign known=Session.SPRING_SECURITY_CONTEXT??>


<@c.page "../../css/error.css">
    <!--Шапка-->
    <@h.header />

<#--Тело-->
    <section class="about">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Вход</h2>
                    <form>
                        <div class="col-25">
                            Введите логин:
                        </div>
                        <div class="col-75">
                            <input type="text" name="username" id="username">
                        </div>
                        <br>
                        <div class="col-25">
                            Введите пароль:
                        </div>
                        <div class="col-75">
                            <input type="password" name="password" id="password">
                        </div>
                        <br>
                        <#if error?has_content>
                            <div class="error">
                                <li>Ошибка: неверный логин/пароль</li>
                            </div>
                        </#if>
                        <a href="/registration">Зарегистрироваться</a>
                        <div class="col">
                            <button type="button" class="btn btn-my" onclick="test()" data-bs-toggle="modal"
                                    data-bs-target="#loadingModal">Войти
                            </button>
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
    <script>
        function test() {
            fetch('/auth', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({username: document.getElementById("username").value, password: document.getElementById("password").value})
            })
                .then(response => {
                    if (response.ok) {
                        response.json().then(function (data) {
                            document.cookie = "jwt=Bearer " + data.token;
                            window.location.replace("/");
                        })
                    }
                    if (response.status === 500){
                        window.location.replace("/login-error");
                    }
                });
        }
    </script>
</@c.page>



