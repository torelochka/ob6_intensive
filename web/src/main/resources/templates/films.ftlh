<#import "layouts/base.ftlh" as base>
<@base.mainMacro title="Sign in" css=["error.css"] >
    <div class="container">
        <h1 class="text-center">Фильмы в прокате</h1>
        <div class="row">
            <#list films as film>
                <div class="col">
                    <div class="card" style="width: 18rem;">
                        <img src="/dist/img/${film.poster}" class="card-img-top" alt="...">
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