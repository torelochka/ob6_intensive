<#import "layouts/base.ftlh" as base>
<@base.mainMacro title="Фильмы" css=["error.css"] >
    <div class="container">
        <#if error??>
            <h4 class="alert alert-danger" id="error-message">${error}</h4>
        </#if>
        <#if film??>
            <h1 class="text-center">${film.title}</h1>
            <img src="/dist/img/${film.posterName}" class="card-img-top" alt="...">
            <#if user?? && user.role == "ROLE_ADMIN">
                <#if film.inRent>
                    <a href="/admin/film/${film.id}/rentOff" class="button btn-success btn">Снять с проката</a>
                    <a href="/admin/film/${film.id}/seance" class="button btn-success btn">Новый сеанс</a>
                <#else>
                    <a href="/admin/film/${film.id}/rentOn" class="button btn-success btn">Отправить в прокат</a>
                </#if>
            </#if>
            <h2>описание</h2>
            <p>${film.description}</p>
            <h2>Трейлер</h2>
            <iframe width="560" height="315" src="https://www.youtube.com/embed/${film.trailerUrl}"
                    title="YouTube video player" frameborder="0"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                    allowfullscreen></iframe>
            <h2>Доступные сеансы</h2>
            <div class="row">
                <#list seances as seance>
                    <div class="col">
                        <a href="/seance/${film.id}" class="btn btn-primary">${seance.date}</a>
                    </div>
                </#list>
            </div>
        <#else>
            <h1 class="text-center">Фильм не найден</h1>
        </#if>
    </div>
</@base.mainMacro>