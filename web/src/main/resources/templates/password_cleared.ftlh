<#import "layouts/base.ftlh" as base>
<@base.mainMacro title="Восстановление пароля" css=["error.css"] >
    <div class="container">
        <h1 class="text-center">Восстановление пароля</h1>
        <#if email??>
            <h4 class="alert alert-success" id="success-message">
                На ваш адрес <a href="mailto:${email}">${email}</a> отправлено письмо с инструкцией для воостановления пароля
            </h4>
        </#if>
    </div>
</@base.mainMacro>