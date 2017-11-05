<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Seckill</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css"	href="uikit-2.25.0/css/uikit.gradient.min.css">
	<link rel="stylesheet" type="text/css" href="uikit-2.25.0/css/components/notify.gradient.min.css">
</head>
<body>
<div
	style="width:800px;margin-top:10px;margin-left: auto;margin-right: auto;text-align: center;">
	<h2>Seckill</h2>
</div>
<div style="width:800px;margin-left: auto;margin-right: auto;">
	<fieldset class="uk-form">
		<legend>Resource Request Based on RESTful API</legend>
		<button class="uk-button uk-button-primary uk-button-large" id="btnList">List GET</button>
		<button class="uk-button uk-button-primary uk-button-large" id="btnDetail">Detail GET</button>
		<button class="uk-button uk-button-primary uk-button-large" id="btnExposer">Exposer POST</button>
		<button class="uk-button uk-button-danger uk-button-large" id="btnExecution">Execution POST</button>
		<button class="uk-button uk-button-primary uk-button-large" id="btnTime">Time GET</button>
	</fieldset>
</div>

<script type="text/javascript" src="jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="uikit-2.25.0/js/uikit.min.js"></script>
<script type="text/javascript" src="uikit-2.25.0/js/components/notify.min.js"></script>
<script type="text/javascript">
	(function(window,$){

		var dekota={
			
			url:'',

			init:function(){
				dekota.url='<%=basePath%>';
				$.UIkit.notify("initialization complete", {status:'info',timeout:500});
				$("#btnList").click(dekota.getList);
				$("#btnDetail").click(dekota.getDetail);
				$("#btnExposer").click(dekota.postExposer);
				$("#btnExecution").click(dekota.postExecution);
				$("#btnTime").click(dekota.getTime);
			},
			getList:function(){
				$.ajax({
					url: dekota.url + 'list',
					type: 'GET',
					dataType: 'json'
				}).done(function(data, status, xhr) {
					$.UIkit.notify("Get People Succeed", {status:'success',timeout:1000});
				}).fail(function(xhr, status, error) {
					$.UIkit.notify("Fail!", {status:'danger',timeout:2000});
				});
			},
			addPerson:function(){
				$.ajax({
					url: dekota.url + 'person',
					type: 'POST',
					dataType: 'json',
					data: {id: 1,name:'Zhang San',sex:'Male',age:23}
				}).done(function(data, status, xhr) {
					$.UIkit.notify(data.msg, {status:'success',timeout:1000});
				}).fail(function(xhr, status, error) {
					$.UIkit.notify("Fail！", {status:'danger',timeout:2000});
				});
			},
			delPerson:function(){
				$.ajax({
					url: dekota.url + 'person/109',
					type: 'DELETE',
					dataType: 'json'
				}).done(function(data, status, xhr) {
					$.UIkit.notify(data.msg, {status:'success',timeout:1000});
				}).fail(function(xhr, status, error) {
					$.UIkit.notify("Fail！", {status:'danger',timeout:2000});
				});
			},
			updatePerson:function(){
				$.ajax({
					url: dekota.url + 'person',
					type: 'POST',
					dataType: 'json',
					data: {_method:'PUT',id: 221,name:'Wang Wu',sex:'Male',age:23}
				}).done(function(data, status, xhr) {
					$.UIkit.notify(data.msg, {status:'success',timeout:1000});
				}).fail(function(xhr, status, error) {
					$.UIkit.notify("Fail！", {status:'danger',timeout:2000});
				});
			},
			listPerson:function(){
				$.ajax({
					url: dekota.url + 'person',
					type: 'POST',
					dataType: 'json',
					data: {_method:'PATCH',name: 'Zhang San'}
				}).done(function(data, status, xhr) {
					$.UIkit.notify("Search People Succeed", {status:'success',timeout:1000});
				}).fail(function(xhr, status, error) {
					$.UIkit.notify("Fail！", {status:'danger',timeout:2000});
				});
			}
		};
		window.dekota=(window.dekota)?window.dekota:dekota;
		$(function(){
			dekota.init();
		});
	})(window,jQuery);

</script>
</body>
</html>