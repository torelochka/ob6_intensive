<#import "layouts/base.ftlh" as base>
<#import "spring.ftl" as spring/>
<@base.mainMacro title="Добавить фильм">
    <main>
        <div class="container">
            <section class="mt-5 mb-4">
                <div class="row">
                    <div class="col"></div>
                    <div class="col-8 align-self-center">
                        <div class="card wish-list mb-4">
                            <div class="card">
                                <div class="card-body">
                                    <h3 class="mb-4 text-black-80 mt-0 font-weight-bold">Добавить фильм</h3>
                                    <#if error??>
                                        <h4 class="alert alert-danger" id="error-message">${error}</h4>
                                    </#if>
                                    <@spring.bind "filmForm"/>
                                    <form action="/admin/film" autocomplete="off" method="post" id="form" enctype="multipart/form-data">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                        <div class="form-group">
                                            <span class="text-black-50">Название</span>
                                            <@spring.formInput "filmForm.title" "class='form-control' required"/>
                                            <@spring.showErrors "<br>" "error"/>
                                        </div>
                                        <div class="form-group">
                                            <span class="text-black-50">Ссылка на трейлер в ютубе</span>
                                            <@spring.formInput "filmForm.trailerUrl" "class='form-control' required"/>
                                            <@spring.showErrors "<br>" "error"/>
                                        </div>
                                        <div class="form-group">
                                            <span class="text-black-50">Описание</span>
                                            <@spring.formTextarea "filmForm.description" "class='form-control' required"/>
                                            <@spring.showErrors "<br>" "error"/>
                                        </div>
                                        <div class="form-group">
                                            <label>Постер
                                                <input name="image" type="file" accept="image/*" required>
                                            </label>
                                        </div>
                                        <input type="submit" id="hide-submit" hidden>
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