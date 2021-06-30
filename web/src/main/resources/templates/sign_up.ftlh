<#import "layouts/base.ftlh" as base>
<#import "spring.ftl" as spring/>
<@base.mainMacro title="Регистрация" css=["error.css"]>
    <main>
        <div class="container">
            <section class="mt-5 mb-4">
                <div class="row">
                    <div class="col"></div>
                    <div class="col-8 align-self-center">
                        <div class="card wish-list mb-4">
                            <div class="card">
                                <div class="card-body">
                                    <h3 class="mb-4 text-black-80 mt-0 font-weight-bold">Регистрация</h3>
                                    <#if error??>
                                        <h4 class="alert alert-danger" id="error-message">${error}</h4>
                                    </#if>
                                    <@spring.bind "signUpForm"/>
                                    <form action="/signUp" autocomplete="off" method="post">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                        <div class="form-group">
                                            <span class="text-black-50">Email</span>
                                            <@spring.formInput "signUpForm.email" "class='form-control' required"/>
                                            <@spring.showErrors "<br>" "error"/>
                                        </div>
                                        <div class="form-group">
                                            <span class="text-black-50">Город</span>
                                            <@spring.formInput "signUpForm.city" "class='form-control' required"/>
                                            <@spring.showErrors "<br>" "error"/>
                                        </div>
                                        <div class="form-group">
                                            <span class="text-black-50">Имя</span>
                                            <@spring.formInput "signUpForm.firstName" "class='form-control' required"/>
                                            <@spring.showErrors "<br>" "error"/>
                                        </div>
                                        <div class="form-group">
                                            <span class="text-black-50">Пароль</span>
                                            <@spring.formPasswordInput "signUpForm.password" "class='form-control' required "/>
                                            <@spring.showErrors "<br>" "error"/>
                                        </div>
                                        <div class="form-group">
                                            <span class="text-black-50">Повторите пароль</span>
                                            <@spring.formPasswordInput "signUpForm.passwordAgain" "class='form-control' required "/>
                                        </div>
                                        <button class="btn btn-primary btn-lg btn-block waves-effect waves-light"
                                                id="submit" type="submit">Создать
                                        </button>
                                    </form>
                                    <hr>
                                    <p><a href="/signIn" class="text-secondary">Уже зарегистрированы ?</a></p>
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