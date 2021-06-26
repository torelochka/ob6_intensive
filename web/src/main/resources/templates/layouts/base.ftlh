<#macro mainMacro title, css=[], scripts=[]>
	<!doctype html>
	<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport"
		      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<link href="/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
      <#list css as style>
				<link rel="stylesheet" href="css/${style}">
      </#list>
		<title>${title}</title>
	</head>
	<body>
  <#include "header.ftlh">
  <#nested>
  <#include "footer.ftlh">
	<script src="/js/fontawesome.js"></script>
	<script src="/js/jquery-3.5.1.min.js"></script>
	<script src="/js/bootstrap.bundle.js"></script>
  <#list scripts as script>
		<script src="js/${script}"></script>
  </#list>
	</body>
	</html>
</#macro>