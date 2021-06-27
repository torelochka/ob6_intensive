<#import "layouts/base.ftlh" as base>
<#import "spring.ftl" as spring/>
<@base.mainMacro title="Добавить сеанс" scripts=["flatpickr.js", "flatpickr_ru.js", "main.js"] css=["flatpickr.min.css"]>
    <main>
        <div class="container">
            <section class="mt-5 mb-4">
                <div class="row">
                    <div class="col"></div>
                    <div class="col-8 align-self-center">
                        <div class="card wish-list mb-4">
                            <div class="card">
                                <div class="card-body">
                                    <h3 class="mb-4 text-black-80 mt-0 font-weight-bold">Добавить сеанс</h3>
                                    <#if error??>
                                        <h4 class="alert alert-danger" id="error-message">${error}</h4>
                                    </#if>
                                    <@spring.bind "seanceForm"/>
                                    <form action="/admin/film/${filmId}/seance" autocomplete="off" method="post" id="form">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                        <div class="check-date">
                                            <label for="date-in">Дата:</label>
                                            <input type="text" class="date-input" id="date" name="date">
                                            <i class="far fa-calendar"></i>
                                        </div>
                                        <button class="btn btn-primary btn-lg btn-block waves-effect waves-light"
                                                id="submit">Создать
                                        </button>
                                    </form>
                                    <hr>
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