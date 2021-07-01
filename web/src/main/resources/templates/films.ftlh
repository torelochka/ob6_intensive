<#import "layouts/base.ftlh" as base>
<#import "spring.ftl" as spring/>
<@base.mainMacro title="Фильмы" css=["error.css"]>
    <div class="container">
        <h1 class="text-center">Фильмы в прокате</h1>
        <@spring.bind "filmSearchForm"/>
        <form action="/films/search" class="input-group rounded my-4" autocomplete="off" method="post">
            <#if user?? && user.role == "ROLE_ADMIN">
                <a href="/admin/film" class="button btn-success btn mx-auto">Добавить Фильм</a>
            </#if>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <@spring.formInput "filmSearchForm.search" "class='form-control rounded mx-2' placeholder='Поиск' required"/>
            <button type="submit" class="btn btn-primary mx-auto">
                <i class="fas fa-search"></i>
            </button>
            <#if search?? && search == true>
                <a href="/films" class="btn btn-dark ml-2">Сбросить</a>
            </#if>
        </form>
        <div class="row">
            <#list films as film>
                <div class="col">
                    <div class="card" style="width: 18rem;">
                        <img src="/dist/img/${film.posterName}" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">${film.title}</h5>
                            <p class="card-text">${film.description}</p>
                            <a href="/film/${film.id}" class="btn btn-primary">Доступные сеансы</a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</@base.mainMacro>