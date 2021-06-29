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
                                    <@spring.bind "emailForm"/>
                                    <form action="/repass/email" autocomplete="off" method="post">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                        <div class="form-group">
                                            <span class="text-black-50">Email</span>
                                            <@spring.formInput "emailForm.email" "class='form-control' required"/>
                                            <@spring.showErrors "<br>" "error"/>
                                        </div>
                                        <button class="btn btn-primary btn-lg btn-block waves-effect waves-light" type="submit">
                                            Сбросить пароль
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