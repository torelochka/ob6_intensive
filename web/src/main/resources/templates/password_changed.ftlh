<#import "layouts/base.ftlh" as base>
<@base.mainMacro title="Восстановление пароля" css=["error.css"] >
    <div class="container">
        <h1 class="text-center">Восстановление пароля</h1>
        <#if error??>
            <h4 class="alert alert-danger" id="error-message">${error}</h4>
        </#if>
        <#if success??>
            <h4 class="alert alert-success" id="success-message">Пароль был успешно изменен!</h4>
        </#if>
    </div>
</@base.mainMacro>