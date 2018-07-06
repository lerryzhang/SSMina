<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String mac = request.getParameter("mac");
%>
<link href="layui/css/layui.css" rel="stylesheet" type="text/css" />

<body>
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend> 列表展示 </legend>
	</fieldset>
	<form class="layui-form" action="" id="form1" lay-filter="example">
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-block">
				<input type="text" name="username" lay-verify="title"
					autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">密码框</label>
			<div class="layui-input-block">
				<input type="password" name="password" autocomplete="off"
					class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">邮箱</label>
			<div class="layui-input-block">
				<input type="text" name="email" autocomplete="off"
					class="layui-input" lay-verify="email">
			</div>
		</div>


		<div class="layui-form-item">
			<label class="layui-form-label">手机</label>
			<div class="layui-input-block">
				<input type="text" name="tel" autocomplete="off" class="layui-input"
					>
			</div>
		</div>

	
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">描述</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入内容" class="layui-textarea" name="desc"></textarea>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
			</div>
		</div>
	</form>
	<script src="layui/layui.js"></script>
	<script src="jquery/jquery-2.0.3.min.js" type="text/javascript"></script>
	<script>
		layui.use('form', function() {
			var form = layui.form;
			form.on('submit(formDemo)', function(data) {

				$.ajax({
					type : "POST",
					dataType : "json",
					url : "user/add",
					data : $('#form1').serialize(),
					success : function(result) {
						if (result == "0") {
							layer.alert("新增用户成功", {
								title : "提示",
								icon : 6
							});
						} else {
							layer.alert("新增用户操作出现异常", {
								title : "提示",
								icon : 5
							});
						}
					},
					error : function() {
						layer.alert("控新增用户操作出现异常", {
							title : "提示",
							icon : 5
						});
					}
				});
				return false;//只此一句
			});

		});
	</script>
</body>
