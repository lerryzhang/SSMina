<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String mac = request.getParameter("mac");
%>
<link href="layui/css/layui.css" rel="stylesheet" type="text/css" />


<body>

	<blockquote class="layui-elem-quote layui-text">方向：服务器
		->设备</blockquote>

	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend> 列表展示 </legend>
	</fieldset>

	<form class="layui-form" action="">
		<!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
		<div class="layui-form-item">

			<div class="layui-input-block">
				<input type="checkbox" name="c1" value="5001" title="参数选择算法">
			</div>
			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r1" value="0" checked="" title="频率次数优先">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r1" value="1" title="优先级优先">
					</div>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="c2" value="5002" title="参数自配置开关 ">
			</div>
			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r2" value="0" checked="" title="关闭">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r2" value="1" title="开启">
					</div>
				</div>
			</div>

		</div>
		<div class="layui-form-item">

			<div class="layui-input-block">
				<input type="checkbox" name="c3" value="5005" title="探针开关 ">
			</div>
			<div class="layui-input-block">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="radio" name="r3" value="0" checked="" title="关闭">
					</div>
					<div class="layui-input-inline">
						<input type="radio" name="r3" value="1" title="开启">
					</div>
				</div>
			</div>

		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="control" value="5015" title="白名单 ">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="password" placeholder="请输入IMSI"
						autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">字符串，格式为多组用逗号隔开的 15
					位 IMSI</div>

			</div>
		</div>


		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="checkbox" name="control" value="5015" title="黑名单 ">
			</div>


			<div class="layui-input-block">
				<div class="layui-input-inline">
					<input type="text" name="password" placeholder="请输入IMSI"
						autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">字符串，格式为多组用逗号隔开的 15
					位 IMSI</div>

			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">
					重置</button>
			</div>
		</div>
		<!-- 更多表单结构排版请移步文档左侧【页面元素-表单】一项阅览 -->
	</form>
	<script src="layui/layui.js"></script>
	<script src="jquery/jquery-2.0.3.min.js" type="text/javascript"></script>
	<script>
		layui.use('form', function() {
			var form = layui.form;
			form.on('submit(formDemo)', function(data) {
				$("input[type='checkbox']").each(function() {
					if ($(this).is(":checked")) {
						alert( $(this).val()+$(this).prop("name") );
					}
				});

				return false;
			});
		});
	</script>


</body>
