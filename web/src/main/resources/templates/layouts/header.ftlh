<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand mb-0 h1" href="/">Ob6-intensive</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
		        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="/films">Фильмы</a>
				</li>
			</ul>
        <#if user??>
			<button class="btn btn-primary my-2 my-sm-0" onclick="location.href='/profile'">Личный кабинет</button>
			<button class="btn btn-primary my-2 my-sm-0 mx-3" onclick="location.href='/logout'">Выйти</button>
        <#else>
					<button class="btn btn-primary my-2 my-sm-0" onclick="location.href='/signIn'">Войти</button>
					<button class="btn btn-primary my-2 my-sm-0 mx-3" onclick="location.href='/signUp'">Создать</button>
        </#if>
		</div>
	</nav>
</header>