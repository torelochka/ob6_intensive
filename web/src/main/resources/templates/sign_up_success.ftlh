<#import "layouts/base.ftlh" as base>
<@base.mainMacro title="Подтверждение почты" css=["error.css"] >
    <div class="container">
        <h1 class="text-center">Подтверждение почты</h1>
        <#if email??>
            <h4 class="alert alert-success" id="success-message">
                На ваш адрес
                <a href="mailto:${email}">${email}</a>
                отправлено письмо с инструкцией для заверешния регистрации
            </h4>
        </#if>
    </div>
</@base.mainMacro>