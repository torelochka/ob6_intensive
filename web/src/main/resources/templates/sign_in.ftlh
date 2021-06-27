
<#import "layouts/base.ftlh" as base>
<@base.mainMacro title="Авторизация" css=["error.css"] >
    <main>
        <div class="container">
            <section class="mt-5 mb-4">
                <div class="row">
                    <div class="col"></div>
                    <div class="col-8 align-self-center">
                        <div class="card wish-list mb-4">
                            <div class="card">
                                <div class="card-body">
                                    <h3 class="mb-4 text-black-80 mt-0 font-weight-bold">Авторизация</h3>
                                    <#if error??>
                                        <h4 class="alert alert-danger" id="error-message">${error}</h4>
                                    </#if>
                                    <form autocomplete="off" action="/signIn" method="post" id="form">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                        <div class="form-group">
                                            <span class="text-black-50">Email</span>
                                            <input id="email-signin" type="text" data-verify="email"
                                                   class="form-control" name="email"
                                                   required>
                                        </div>
                                        <div class="form-group">
                                            <span class="text-black-50">Пароль</span>
                                            <input id="password-signin" type="password" class="form-control"
                                                   name="password" required>
                                        </div>
                                        <button id='submit'
                                                class="btn btn-primary btn-lg btn-block waves-effect waves-light"
                                                type="submit">
                                            Войти
                                        </button>
                                    </form>
                                    <hr>
                                    <#--<p><a href="/repass" class="text-secondary">Forgot password?</a></p>-->
                                    <p><a href="/signUp" class="text-secondary">Регистрация</a></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col"></div>
                </div>
            </section>
        </div>
    </main>
</@base.mainMacro>