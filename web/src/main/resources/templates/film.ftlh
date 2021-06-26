<#import "layouts/base.ftlh" as base>
<@base.mainMacro title="Фильмы" css=["error.css"] >
    <div class="container">
        <#if film??>
            <h1 class="text-center">${film.title}</h1>
            <img src="/dist/img/${film.posterName}" class="card-img-top" alt="...">
            <#if user?? && user.role == "ROLE_ADMIN">
                <a class="button btn-success btn">Добавить Фильм</a>
            </#if>
        <#--todo добавить возможность снимать с проката-->
            <h2>описание</h2>
            <p>${film.description}</p>
            <h2>Трейлер</h2>
            <iframe width="560" height="315" src="https://www.youtube.com/embed/${film.trailerUrl}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
            <h2>Доступные сеансы</h2>
            <#--todo добавить список доступных сеансов-->
            <#else>
            <h1 class="text-center">Фильм не найден</h1>
        </#if>
    </div>
</@base.mainMacro>