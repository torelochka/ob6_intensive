<#import "layouts/base.ftlh" as base>
<@base.mainMacro title="Почта" css=["error.css"] >
    <div class="container">
        <#if error??>
            <h4 class="alert alert-danger" id="error-message">${error}</h4>
        </#if>
        <#if success??>
            <h4 class="alert alert-success" id="success-message">${success}</h4>
        </#if>
        <h1 class="text-center">Потверждение почты</h1>
    </div>
</@base.mainMacro>