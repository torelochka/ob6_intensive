<#import "layouts/base.ftlh" as base>
<#import "spring.ftl" as spring/>
<@base.mainMacro title="Восстановление пароля" css=["error.css"] >
    <main>
        <div class="container">
            <section class="mt-5 mb-4">
                <div class="row">
                    <div class="col"></div>
                    <div class="col-8 align-self-center">
                        <div class="card wish-list mb-4">
                            <div class="card">
                                <div class="card-body">
                                    <h3 class="mb-4 text-black-80 mt-0 font-weight-bold">Восстановление пароля</h3>
                                    <#if error??>
                                        <h4 class="alert alert-danger" id="error-message">${error}</h4>
                                    </#if>
                                    <@spring.bind "forgotPasswordForm"/>
                                    <form action="/repass" autocomplete="off" method="post">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                        <@spring.formHiddenInput "forgotPasswordForm.email"/>
                                        <div class="form-group">
                                            <span class="text-black-50">Новый пароль:</span>
                                            <@spring.formPasswordInput "forgotPasswordForm.password" "class='form-control' required"/>
                                            <@spring.showErrors "<br>" "error"/>
                                        </div>
                                        <div class="form-group">
                                            <span class="text-black-50">Новый пароль еще раз</span>
                                            <@spring.formPasswordInput "forgotPasswordForm.passwordAgain" "class='form-control' required"/>
                                        </div>
                                        <button class="btn btn-primary btn-lg btn-block waves-effect waves-light" type="submit">Изменить
                                        </button>
                                    </form>
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