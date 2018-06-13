<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String mac = request.getParameter("mac");
%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<link href="../layui/css/layui.css" rel="stylesheet" type="text/css" />


<body>

	<blockquote class="layui-elem-quote layui-text">
		方向：服务器22222 ->设备
	</blockquote>

	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>
			列表展示
			<input type="hidden" name="mac" value="<%=mac%>">
		</legend>
	</fieldset>
	<form class="layui-form" action="">



		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">
					设备MAC地址
				</label>
				<div class="layui-input-inline">
					${smtp.mac}
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">
					协议版本号
				</label>
				<div class="layui-input-inline">
					${smtp.version}
				</div>
			</div>
		</div>






		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">
					设备型号
				</label>
				<div class="layui-input-inline">
					${smtp.model}
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">
					固件版本
				</label>
				<div class="layui-input-inline">
					${smtp.fw}
				</div>
			</div>
		</div>




		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">
					启动类型
				</label>
				<div class="layui-input-inline">
					${smtp.startType}
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">
					REM扫描状态
				</label>
				<div class="layui-input-inline">
					${smtp.rem}
				</div>
			</div>
		</div>


		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">
					对应型号
				</label>
				<div class="layui-input-inline">
					${smtp.corrModel}
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">
					路由器固件版本
				</label>
				<div class="layui-input-inline">
					${smtp.routerFw}
				</div>
			</div>
		</div>


	</form>
	<!-- 更多表单结构排版请移步文档左侧【页面元素-表单】一项阅览 -->

	<script src="../layui/layui.js"></script>
	<script src="../jquery/jquery-2.0.3.min.js" type="text/javascript"></script>
	<script>
	layui.use('form', function() {
		var form = layui.form;
		form.on('submit(formDemo)', function(data) {
			$("input[type='checkbox']").each(function() {
				if ($(this).is(":checked")) {
					alert($(this).val() + $(this).prop("name"));
				}
			});

			return false;
		});
	});
</script>


</body>
