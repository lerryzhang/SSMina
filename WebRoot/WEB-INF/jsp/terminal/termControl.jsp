<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String mac = request.getParameter("mac");
%>
<link href="../layui/css/layui.css" rel="stylesheet" type="text/css" />


<body>

	<blockquote class="layui-elem-quote layui-text">方向：服务器 
		设备 控制请求报文数据体包含如下元素： 序号 元素名称 1 设备MAC地址 2 控制命令</blockquote>

	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend> 列表展示 </legend>
	</fieldset>

	<form class="layui-form" id="form1" action="">
		<!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
		<div class="layui-form-item">

			<div class="layui-input-block">
				<input type="radio" name="control" value="4001" title="升级请求">
				<input type="hidden" name="mac" value="<%=mac%>">

			</div>

			<div class="layui-input-block">
				<div class="layui-inline">
					<label class="layui-form-label"> 用户名 </label>
					<div class="layui-input-inline">
						<input type="tel" name="username" autocomplete="off"
							class="layui-input">

					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label"> 密码 </label>
					<div class="layui-input-inline">
						<input type="password" name="password" autocomplete="off"
							class="layui-input">
					</div>
				</div>
			</div>



			<div class="layui-input-block">
				<div class="layui-inline">
					<label class="layui-form-label"> URL </label>
					<div class="layui-input-inline">
						<input type="tel" name="upgradeUrl" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label"> 版本名称 </label>
					<div class="layui-input-inline">
						<input type="text" name="version" autocomplete="off"
							class="layui-input">
					</div>
				</div>
			</div>
		</div>

		<div class="layui-form-item">

			<div class="layui-input-block">
				<input type="radio" name="control" value="4003" title="重启请求">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="radio" name="control" value="4005" title="恢复出厂设置请求">
			</div>
		</div>
		<div class="layui-form-item">

			<div class="layui-input-block">
				<input type="radio" name="control" value="4007" title="路由器升级请求">

			</div>


			<div class="layui-input-block">
				<div class="layui-inline">
					<label class="layui-form-label"> URL </label>
					<div class="layui-input-inline">
						<input type="text" name="routerUrl" autocomplete="off"
							class="layui-input">
					</div>
				</div>

			</div>

		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">
					立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">
					重置</button>
			</div>
		</div>
		<!-- 更多表单结构排版请移步文档左侧【页面元素-表单】一项阅览 -->
	</form>
	<script src="../layui/layui.js"></script>
	<script src="../jquery/jquery-2.0.3.min.js" type="text/javascript"></script>
	<script>
		layui.use('form', function() {
			var form = layui.form;
			form.on('submit(formDemo)', function(data) {

				$.ajax({
					type : "POST",
					dataType : "json",
					url : "smallCell/control",
					data : $('#form1').serialize(),
					success : function(result) {
						if (result == "0") {
							layer.alert("控制指令下发成功", {
								title : "提示",
								icon : 6
							});
						} else {
							layer.alert("控制指令下发操作出现异常", {
								title : "提示",
								icon : 5
							});
						}
					},
					error : function() {
						layer.alert("控制指令下发操作出现异常", {
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
