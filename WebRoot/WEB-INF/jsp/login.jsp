<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>登录界面</title>
<link href="../css/default.css" rel="stylesheet" type="text/css" />
<link href="../layui/css/layui.css" rel="stylesheet" type="text/css" />
<!--必要样式-->
<link href="../css/styles.css" rel="stylesheet" type="text/css" />
<link href="../css/demo.css" rel="stylesheet" type="text/css" />
<link href="../css/loaders.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class='login'>
		<div class='login_title'>
			<span>管理员登录</span>
		</div>
		<div class='login_fields'>
			<div class='login_fields__user'>
				<div class='icon'>
					<img alt="" src='img/user_icon_copy.png'>
				</div>
				<input name="username" maxlength="16" type='text' autocomplete="off" />
				<div class='validation'>
					<img alt="" src='../img/tick.png'>
				</div>
			</div>
			<div class='login_fields__password'>
				<div class='icon'>
					<img alt="" src='../img/lock_icon_copy.png'>
				</div>
				<input name="password" maxlength="16" type='text' autocomplete="off">
				<div class='validation'>
					<img alt="" src='../img/tick.png'>
				</div>
			</div>

			<div class='login_fields__submit'>
				<button class="layui-btn" lay-submit lay-filter="formDemo" type="submit">登陆</button>
			</div>
		</div>
		<div class='success'></div>
		<div class='disclaimer'>
			<p>欢迎登陆后台管理平台</p>
		</div>
	</div>
	<div class='authent'>
		<div class="loader"
			style="height: 44px;width: 44px;margin-left: 28px;">
			<div class="loader-inner ball-clip-rotate-multiple">
				<div></div>
				<div></div>
				<div></div>
			</div>
		</div>
		<p>认证中...</p>
	</div>
	<div class="OverWindows"></div>



	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
	<script src="../jquery/jquery-2.0.3.min.js" type="text/javascript"></script>
	<script type="text/javascript" src='../js/stopExecutionOnTimeout.js?t=1'></script>
	<script type="text/javascript" src="../layui/layui.js"></script>
	<script type="text/javascript" src="../js/Particleground.js"></script>
	<script type="text/javascript" src="../js/Treatment.js"></script>
	<script type="text/javascript" src="../js/jquery.mockjax.js"></script>
	<script type="text/javascript">
	
	

	layui.use('form', function() {
		var form = layui.form;
		form.on('submit(formDemo)', function(data) {
		
		var username = $('input[name="username"]').val();
				var password = $('input[name="password"]').val();
				if (username == '') {
					ErroAlert('请输入您的账号');
				} else if (password == '') {
					ErroAlert('请输入密码');
				} else {
		
		

			$.ajax( {
				type : "POST",
				dataType : "json",
					url : "<%=basePath%>user/login",
						data : {
							password : password,
							username : username,
						},
						success : function(result) {
							if (result == "0") {
								
								
								window.location.href = "<%=basePath%>smallCell/index";

							} else {
							ErroAlert('用户名或密码不正确');
								
							}

						},
						error : function() {
							
							
                                
                                ErroAlert('登陆过程出现异常');
						}
					});
				}
				return false;//只此一句
			});

		});
	</script>

</body>
</html>
