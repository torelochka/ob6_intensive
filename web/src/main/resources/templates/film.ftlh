<#import "layouts/base.ftlh" as base>
<@base.mainMacro title="Фильмы" css=["seats.css"] scripts=["seats.js"]>
    <div class="container">
        <#if errorBooking??>
            <h4 class="alert alert-danger" id="error-message">${errorBooking}</h4>
        </#if>
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
            <h2>Описание</h2>
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
                        <a href="${seance.id}" class="btn btn-primary seance-button" data-toggle="modal"
                           data-target="#exampleModal">${seance.date}</a>
                    </div>
                </#list>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <form action="/booking/${film.id}" method="post" id="form">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Доступные места</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"
                                        onclick="closeTable()">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body table" id="modal-body">
                                <table id="modal-body-table">
                                </table>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                        onclick="closeTable()">Закрыть
                                </button>
                                <#if user?? && user.role == "ROLE_ADMIN">
                                <#--<#if user?? && false>-->
                                <#else>
                                    <button type="submit" class="btn btn-primary">Забронировать</button>
                                </#if>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        <#else>
            <h1 class="text-center">Фильм не найден</h1>
        </#if>
    </div>
</@base.mainMacro>