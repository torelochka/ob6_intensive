<#import "layouts/base.ftlh" as base>
<#import "spring.ftl" as spring/>
<@base.mainMacro title="Личный кабинет" css=["error.css"] >
    <div class="container">
        <h1 class="text-center">Личный кабинет</h1>
        <div class="form-group">
            <span class="text-black-50 disabled">Email</span>
            <input class='form-control' required disabled value="${user.email}">
        </div>
        <@spring.bind "userDataForm"/>
        <form action="/updateProfile" autocomplete="off" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <@spring.formHiddenInput "userDataForm.email"/>
            <div class="form-group">
                <span class="text-black-50">Имя</span>
                <@spring.formInput "userDataForm.firstName" "class='form-control' required"/>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div class="form-group">
                <span class="text-black-50">Город</span>
                <@spring.formInput "userDataForm.city" "class='form-control' required"/>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div class="form-group">
                <span class="text-black-50">Пароль</span>
                <@spring.formPasswordInput "userDataForm.password" "class='form-control'"/>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <button class="btn btn-primary btn-lg btn-block waves-effect waves-light"
                    id="submit">Изменить
            </button>
        </form>
        <#if user?? && user.role == "ROLE_USER">
            <h3>Забронированные билеты</h3>
            <#if userDataForm??>
                <#list userDataForm.bookings as booking>
                    <ul class="list-group">
                        <li class="list-group-item mt-2 mb-2">
                            <h5 class="card-title">${booking.seance.film.title}</h5>
                            <p class="card-text">${booking.seance.date?datetime}</p>
                            <p class="card-text">Ряд №${booking.seat.row}</p>
                            <p class="card-text">Место №${booking.seat.column}</p>
                        </li>
                    </ul>
                </#list>
            <#else>
                <ul class="list-group">
                    <li class="list-group-item">
                        <h5 class="card-title">Ваша история бронированний пуста...</h5>
                    </li>
                </ul>
            </#if>
        </#if>
    </div>
</@base.mainMacro>